package com.cst.sc.service.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cst.sc.model.SelectedCourse;
import com.cst.sc.model.Takes;

public interface SelectedCourseDao {
	public final static String FIELDS_NAMES = " ID, course_id, CONCAT(time_slot.start_hr,':',time_slot.start_min, ' - ', time_slot.end_hr, ':', time_slot.end_min, '(', time_slot.`day`, ')') as time, sec_id, semester, year, grade ";
	
	@Select({"select ID, course_id, sec_id, semester, year, grade from takes natural left outer join section where ID=#{ID} and takes.course_id = section.course_id and course_id=#{course_id} "})
	public Takes Varify(@Param("ID") String ID, @Param("course_id")String course_id);
	
	@Select({"select", FIELDS_NAMES, "from takes natural left outer join section natural left outer join time_slot where ID=#{ID} and takes.course_id = section.course_id and isnull(takes.grade) "})
	public List<SelectedCourse> VarifyTime(@Param("ID")String ID);
	
	@Insert({"insert into takes values", "(#{ID}, #{course_id}, #{sec_id}, #{semester}, #{year}, #{grade}) "})
	public void insertCourse(Takes takes);
	
	@Delete("delete from takes where ID=#{ID} and course_id=#{course_id}")
	public void deleteCourse(@Param("ID") String ID, @Param("course_id")String course_id);

}
