package com.cst.sc.model;

public class SelectedCourse {
	private String ID;
	private String course_id;
	private String time;
	private String sec_id;
	private String semester;
	private String year;
	private String grade;
	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(String id) {
		ID = id;
	}
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
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
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
	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	@Override
	public String toString() {
        return "[id=" + ID + ", course_id=" + course_id + ", time=" + time + ", sec_id=" + sec_id + ", semester" + semester + ", year=" + year + ", grade=" + grade + "]";
    }
}
