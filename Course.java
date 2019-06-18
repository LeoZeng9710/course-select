package com.cst.sc.model;

import java.io.Serializable;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;


public class Course implements Serializable, Cloneable  {
	private String course_id;
	private String title;
	private String dept_name;
	private int credits;
	private String prereq_id;
	private String time;  //hr+":"+min + "-" + hr+ ":" + min
	private String building;
	private String room_number;
	private String sec_id;
	private String semester;
	private String year;
	

	/**
	 * @return the course_id
	 */
	public String getCourse_id() {
		return course_id;
	}
	/**
	 * @param course_id the course_id to set
	 */
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the dept_name
	 */
	public String getDept_name() {
		return dept_name;
	}
	/**
	 * @param dept_name the dept_name to set
	 */
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	/**
	 * @return the credit
	 */
	public int getCredits() {
		return credits;
	}
	/**
	 * @param credit the credit to set
	 */
	public void setCredits(int credit) {
		this.credits = credit;
	}
	/**
	 * @return the prereq
	 */
	public String getPrereq_id() {
		return prereq_id;
	}
	/**
	 * @param prereq the prereq to set
	 */
	public void setPrereq(String prereq) {
		this.prereq_id = prereq;
	}

	/**
	 * @return the start_hr
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param start_hr the start_hr to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the building
	 */
	public String getBuilding() {
		return building;
	}
	/**
	 * @param building the building to set
	 */
	public void setBuilding(String building) {
		this.building = building;
	}
	/**
	 * @return the room_number
	 */
	public String getRoom_number() {
		return room_number;
	}
	/**
	 * @param room_number the room_number to set
	 */
	public void setRoom_number(String room_number) {
		this.room_number = room_number;
	}
	

	/**
	 * @return the sec_id
	 */
	public String getSec_id() {
		return sec_id;
	}
	/**
	 * @param sec_id the sec_id to set
	 */
	public void setSec_id(String sec_id) {
		this.sec_id = sec_id;
	}
	
	/**
	 * @return the semester
	 */
	public String getSemester() {
		return semester;
	}
	/**
	 * @param semester the semester to set
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	@Override
	public Course clone() throws CloneNotSupportedException {
		return (Course) super.clone();
	}
	
	@Override
	public String toString() {
        return "[id=" + course_id + ", name=" + title + ", dept=" + dept_name + ", credits=" + credits + ", prereq=" +prereq_id + ", time=" + time + ", building=" + building + ", room_number=" + room_number +"]";
    }

	
}