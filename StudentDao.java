package com.cst.sc.service.Dao;

import com.cst.sc.model.Student;


import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;

public interface StudentDao {
	public final static String FIELDS_NAMES = " ID, name, dept_name, tot_cred, credit, password ";
	
	@Select({"select", FIELDS_NAMES, "from student where ID=#{ID} "})
	public Student getAllStudentInfo(@Param("ID")String ID);
	

	@Update({"update student set", " credit=#{credit} where ID=#{ID} "})
	public void updateStudent(Student student);
}

