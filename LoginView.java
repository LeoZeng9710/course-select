package com.cst.sc;


import org.apache.ibatis.session.SqlSession;

import com.cst.sc.model.Student;
import com.cst.sc.model.User;
import com.cst.sc.service.Dao.StudentDao;
import com.cst.sc.service.database.Config;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;


@Route("")
public class LoginView extends VerticalLayout{
	private static final long serialVersionUID = 1L;
	private SqlSession session;
	private static StudentDao studentDao;  
	private TextField username = new TextField("Student ID: ");
	private PasswordField password = new PasswordField("Password: ");
	private User user;
	private static Student student;
	private Binder <Student> binder = new Binder<>(Student.class);
	private Dialog dialog = new Dialog();
	private boolean IsLogin = false;
	
	public LoginView(){
		VerticalLayout line = new VerticalLayout();
		Label login_title = new Label("University Selection Course System");
		Button confirm = new Button("Login");
		binder.bindInstanceFields(this);
		
		confirm.addClickListener( e-> {
		     CheckLogin(username.getValue(),password.getValue(), confirm);
			 if(student == null){
				 dialog.setVisible(true);
			 }else if(!(student.getPassword().equals(user.getPassword()))){
				 dialog.setVisible(true);
			 }else{
				 dialog.setVisible(false);
				 System.out.print("Jump");
			 }
			 confirm.addClickListener(event -> dialog.open());
		});
		
		login_title.getStyle().set("color", "red");
		login_title.getStyle().set("font-size", "80px");
		line.add(login_title,username, password, confirm);
		dialog.add(new Label("Your account/password is wrong, try again."));
		dialog.setWidth("200px");
		dialog.setHeight("150px");
		line.getStyle().set("background-color", "lightgreen");
		line.setAlignItems(Alignment.CENTER);
		line.setSizeFull();
		add(line);
		setAlignItems(Alignment.CENTER);
		setHeight("100vh");
	}
	
	public void CheckLogin(String username, String password, Button a){
		user = new User(username, password);
		session = Config.getSqlSession();
		studentDao = session.getMapper(StudentDao.class);
		student = studentDao.getAllStudentInfo(user.getUsername());
		
		System.out.println(student);
		if(student!=null){
			if(student.getPassword().equals(user.getPassword())){
				setIsLogin(false);
				a.getUI().ifPresent(ui -> ui.navigate("selected"));
				System.out.println(IsLogin);
			}else{
				setIsLogin(true);
				System.out.println(IsLogin);
			}
		}else{				
			setIsLogin(true);
			System.out.println(IsLogin);
		}
	}
	
	public void setIsLogin(boolean Login){
		this.IsLogin = Login;
	}
	
	public static Student getStudent(){
		return student;
	}
	
	public static StudentDao getStudentDao(){
		return studentDao;
	}
}