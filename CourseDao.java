package com.cst.sc.service.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import com.cst.sc.model.Course;


public interface CourseDao {
	public final static String FIELDS_NAMES = " course.course_id, title, dept_name, credits, prereq.prereq_id, CONCAT(time_slot.start_hr,':',time_slot.start_min, ' - ', time_slot.end_hr, ':', time_slot.end_min, '(', time_slot.`day`, ')') as time, section.building, section.room_number, section.sec_id, section.semester, section.year ";
	public final static String FIELDS_NAMES2 = " course.course_id, title, dept_name, credits, prereq.prereq_id, section.building, section.room_number, section.sec_id, section.semester, section.year";
	
	@Select({"select", FIELDS_NAMES, "from course, section natural left outer join time_slot natural left outer join prereq where course.course_id = section.course_id "})
	public List<Course> getAllCourse();
	
	@Select({"select", FIELDS_NAMES, "from course, section natural left outer join time_slot natural left outer join prereq where course.course_id = section.course_id and course.course_id=#{course_id} "})
	public List<Course> getAllCourseByID(String course_id);
	
	@Select({"select", FIELDS_NAMES2, "from takes natural left outer join course natural left outer join section natural left outer join prereq where course.course_id = section.course_id and course.course_id = takes.course_id and section.course_id = takes.course_id and takes.ID = #{ID} and takes.grade = ' ' "})
	public List<Course> CheckMyCourse(String ID);
	
}