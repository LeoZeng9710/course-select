package com.cst.sc;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.cst.sc.model.Course;
import com.cst.sc.model.SelectedCourse;
import com.cst.sc.model.Student;
import com.cst.sc.model.Takes;
import com.cst.sc.service.Dao.SelectedCourseDao;
import com.cst.sc.service.Dao.CourseDao;
import com.cst.sc.service.Dao.StudentDao;
import com.cst.sc.service.database.Config;
import com.cst.sc.LoginView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.Route;


@Route("selected")
public class MainView extends VerticalLayout {
	private Student student;
	private Grid<Course> grid = new Grid<>();
	private TextField filterText1 = new TextField("");
	private SqlSession session;
	private CourseDao courseDao;
	private List<Course> course;
	private SelectedCourseDao selectedcourseDao;
	private Takes course_each;
	private Takes take;
	private List<SelectedCourse> course_each_list;
	private Dialog dialog = new Dialog();
	private StudentDao studentDao;
	private NativeButton confirmButton, cancelButton, okButton;
	private Label page_title, infoDisplay, nameDisplay, MajorDisplay, CreditDisplay1, CreditDisplay2;
	boolean diffFun = true;

    public MainView() {
    	String username = LoginView.getStudent().getId();
    	session = Config.getSqlSession();
    	studentDao = session.getMapper(StudentDao.class);
    	student = studentDao.getAllStudentInfo(username);
        VerticalLayout line = new VerticalLayout();
        page_title = new Label("Course Select System");
        infoDisplay = new Label("Hello,");
        nameDisplay = new Label(student.getName());
        MajorDisplay = new Label("  "+student.getMajor());
        CreditDisplay1 = new Label("Credit: ");
        CreditDisplay2 = new Label("" + student.getCredit());
        Button btn1 = new Button("My Courses");
        btn1.addClickListener(e -> diffFun = CheckMyCourse());
        Button btn2 = new Button("Select Course");
        btn2.addClickListener(e -> diffFun = UpdateCourseList());
        Button btn3 = new Button("Logout");
        btn3.addClickListener(e -> {
        	btn3.getUI().ifPresent(ui -> ui.navigate(""));
        });
        

    	
        HorizontalLayout sub_layer = new HorizontalLayout(infoDisplay, nameDisplay, MajorDisplay, CreditDisplay1, CreditDisplay2, btn1, btn2, btn3);
       
        page_title.getStyle().set("color", "red");
        page_title.getStyle().set("font-size", "25px");
        grid.setSizeFull();
        
        grid.setSizeFull();
        grid.addColumn(Course::getCourse_id).setHeader("Course ID");
        grid.addColumn(Course::getTitle).setHeader("Title");
        grid.addColumn(Course::getDept_name).setHeader("Department");
        grid.addColumn(Course::getCredits).setHeader("Credit");
        grid.addColumn(Course::getPrereq_id).setHeader("Prerequisite Courses");
        grid.addColumn(Course::getTime).setHeader("Time");
        grid.addColumn(Course::getBuilding).setHeader("Building");
        grid.addColumn(Course::getRoom_number).setHeader("Room Number");      
        grid.addColumn(new NativeButtonRenderer<>("Select", item ->{
        	if(diffFun){
    	    		dialog.add(new Label("Information Verification\r\n"+"course_id: " + ((Course)item).getCourse_id() + "\r\ntitle: " + ((Course)item).getTitle() + "\r\nTime: " + ((Course)item).getTime()+ "\r\nBuilding: " + ((Course)item).getBuilding()+((Course)item).getRoom_number() + "\r\n"));
    	       		dialog.setCloseOnEsc(false);
    	       		dialog.setCloseOnOutsideClick(false);
    	       		
    	       		confirmButton = new NativeButton("Confirm", event -> {
    	       				boolean valid = checkIfChoose((Course)item);
    	       				dialog.removeAll();
    	       				if(valid){
    	       					dialog.add(new Label("Select Successful"));      					
    	       		       		okButton = new NativeButton("OK", event1 ->{
    	       		       			dialog.removeAll();
    	       		       			dialog.close();   	   
    	       		       		});
    	   		       			dialog.add(okButton);
    	       				}else{
    	       					dialog.add(new Label("Select Fail")); 					
    	       					okButton = new NativeButton("OK", event1 ->{
    	       		       			dialog.removeAll();
    	       		       			dialog.close();
    	       		       		});
    	       					dialog.add(okButton);
    	       				}
    	       		});
    	       		cancelButton = new NativeButton("Cancel", event -> {
    	       			dialog.removeAll();
    	       			dialog.close();
    	       		});
    	       		dialog.add(confirmButton, cancelButton);
    	    	   
    	    	   dialog.open();
    	   
        	}else{
        		dialog.add(new Label("Information Verification\r\n"+"course_id: " + ((Course)item).getCourse_id() + "\r\ntitle: " + ((Course)item).getTitle() + "\r\nTime: " + ((Course)item).getTime()+ "\r\nBuilding: " + ((Course)item).getBuilding()+((Course)item).getRoom_number() + "\r\n" + "\nDo you want to really remove this course?"));
	       		dialog.setCloseOnEsc(false);
	       		dialog.setCloseOnOutsideClick(false);
	       		
	       		confirmButton = new NativeButton("Yes", event -> {
       				boolean valid = RemoveCourse((Course)item);
       				dialog.removeAll();
       				if(valid){
       					dialog.add(new Label("delete successful"));      					
       		       		okButton = new NativeButton("OK", event1 ->{
       		       			dialog.removeAll();
       		       			dialog.close();
       		       		});
   		       			dialog.add(okButton);
       				}else{
       					dialog.add(new Label("delete fail")); 					
       					okButton = new NativeButton("OK", event1 ->{
       		       			dialog.removeAll();
       		       			dialog.close();
       		       		});
       					dialog.add(okButton);
       				}
	       		});
	       		cancelButton = new NativeButton("No", event -> {
	       			dialog.removeAll();
	       			dialog.close();
	       		});
	       		dialog.add(confirmButton, cancelButton);
	       		dialog.open();
        	}
        })).setHeader("Select/Unselect");
      
       
        line.add(page_title, sub_layer, grid);
        line.getStyle().set("background-color", "lightgreen");
        line.setSizeFull();
        add(line);
        setAlignItems(Alignment.CENTER);
		setHeight("100vh");
    }
    
    public boolean UpdateCourseList(){
    	session = Config.getSqlSession();
    	courseDao = session.getMapper(CourseDao.class);
    	course = courseDao.getAllCourse();
    	grid.setItems(course);
    	return true;
    }
    
    public void UpdateByID(){
    	session = Config.getSqlSession();
    	courseDao = session.getMapper(CourseDao.class);
    	course = courseDao.getAllCourseByID(filterText1.getValue());
    }
    
    public boolean checkIfChoose(Course courseTmp){
    	session = Config.getSqlSession();
    	selectedcourseDao = session.getMapper(SelectedCourseDao.class);
    	studentDao = session.getMapper(StudentDao.class);
    	System.out.println(student.getId());
    	course_each = selectedcourseDao.Varify(student.getId(), courseTmp.getCourse_id());
    	take = new Takes();
    	
    	if(course_each == null){
    		if(courseTmp.getPrereq_id() != null){
    			course_each = selectedcourseDao.Varify(student.getId(), courseTmp.getPrereq_id());
    			if(course_each != null){
    				course_each_list = selectedcourseDao.VarifyTime(student.getId());
    				if(course_each_list != null){
    					for(int i = 0; i < course_each_list.size(); i++){
        					if(courseTmp.getTime().equals(course_each_list.get(i).getTime())){   //时间冲突
        						System.out.println("time collision,fail");
        						return false;
        					}
        				}
    				}
    				if(student.getCredit() < courseTmp.getCredits()){
    					System.out.println("Not enough credit,fail");
    					return false;
    				}
    				
    				take.setID(student.getId());
    				take.setCourse_id(courseTmp.getCourse_id());
    				take.setSec_id(courseTmp.getSec_id());
    				take.setSemester(courseTmp.getSemester());
    				take.setYear(courseTmp.getYear());
    				take.setGrade(" ");
    				selectedcourseDao.insertCourse(take);
    				session.commit();
    				course_each = selectedcourseDao.Varify(student.getId(), courseTmp.getCourse_id());
    				System.out.println(course_each);
    				
    				student.setCredit(student.getCredit()-courseTmp.getCredits());
    				studentDao.updateStudent(student);
    				student = studentDao.getAllStudentInfo(student.getId());
    				System.out.println(student);
    				session.commit();
    				System.out.println(student.getCredit());
    				System.out.println("Prerequisite course has already learnt,successful");
    			}else{
    				System.out.println("Prerequisite has not learnt,fail");
    				return false;
    			}
    		}else{
    			course_each_list = selectedcourseDao.VarifyTime(student.getId());
				if(course_each_list != null){
					for(int i = 0; i < course_each_list.size(); i++){
    					if(courseTmp.getTime().equals(course_each_list.get(i).getTime())){   //时间冲突
    						System.out.println("Time conflict,fail");
    						return false;
    					}
    					System.out.println(course_each_list.get(i));
    				}
				}
				if(student.getCredit() < courseTmp.getCredits()){
					System.out.println("Not enough credit,fail");
					return false;
				}
				
				take.setID(student.getId());
				take.setCourse_id(courseTmp.getCourse_id());
				take.setSec_id(courseTmp.getSec_id());
				take.setSemester(courseTmp.getSemester());
				take.setYear(courseTmp.getYear());
				take.setGrade(" ");
				selectedcourseDao.insertCourse(take);
				session.commit();
				course_each = selectedcourseDao.Varify(student.getId(), courseTmp.getCourse_id());
				System.out.println(course_each);
				
				student.setCredit(student.getCredit()-courseTmp.getCredits());
				studentDao.updateStudent(student);
				student = studentDao.getAllStudentInfo(student.getId());
				session.commit();
				System.out.println(student);
				System.out.println("Not prerequisite course exist, successful");
    		}
    	}else{
    		System.out.println("The selected course has already been selected.");
    		return false;
    	}	
    	
    	CreditDisplay2.setText("" + student.getCredit());
    	return true;
    }
    
    public boolean CheckMyCourse(){
    	session = Config.getSqlSession();
    	courseDao = session.getMapper(CourseDao.class); 
    	course = courseDao.CheckMyCourse(student.getId());	
    	grid.setItems(course);
    	return false;
    }
    
    public boolean RemoveCourse(Course courseTmp){
    	session = Config.getSqlSession();
    	courseDao = session.getMapper(CourseDao.class);
    	selectedcourseDao = session.getMapper(SelectedCourseDao.class);
    	studentDao = session.getMapper(StudentDao.class);
    	student.setCredit(student.getCredit() + courseTmp.getCredits());
    	studentDao.updateStudent(student);
    	session.commit();
    	try{
    		selectedcourseDao.deleteCourse(student.getId(), courseTmp.getCourse_id());
    		session.commit();
        	CreditDisplay2.setText("" + student.getCredit());
        	course = courseDao.CheckMyCourse(student.getId());
        	grid.setItems(course);
    	}catch (NullPointerException e){
    		System.out.println("Null Pointer/No this course");
    		return false;
    	}

    	return true;
    }
    
    public void setDiffFun(boolean diff){
    	this.diffFun = diff;
    }
    
    public boolean getDiffFun(){
    	return diffFun;
    }

    
}
