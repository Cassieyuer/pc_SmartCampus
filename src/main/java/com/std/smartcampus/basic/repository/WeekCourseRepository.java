package com.std.smartcampus.basic.repository;

import java.util.List;

import com.std.smartcampus.basic.domain.Course;
import com.std.smartcampus.basic.domain.WeekCourse;
import com.std.smartcampus.basic.domain.WeekCourse.Week;
import com.std.smartcampus.basic.domain.pk.WeekCoursePK;

/**
 * 课程表Dao层
 * @ClassName: WeekCourseRepository 
 * @Description: TODO
 * @author: Administrator
 * @date: 2017年3月26日 上午1:13:56
 */
public interface WeekCourseRepository extends BaseRepository<WeekCourse, WeekCoursePK>{

	/**
	 * 查询某班的课程表
	 * @Title: findByClas 
	 * @Description: TODO
	 * @param classId 班级编号
	 * @return classID班的课程表
	 * @return: List<WeekCourse>
	 */
	List<WeekCourse> findByClas(Integer classId);
	

	/**
	 * 查询某老师一周所上的课
	 * @Title: findByTeacher_Tid 
	 * @Description: TODO
	 * @param tid 教师工号
	 * @return 工号为 tid 的教师的课程表
	 * @return: List<WeekCourse>
	 */
	List<WeekCourse> findByTeacher_Tid(String tid);

	/**
	 * 查询某门课什么时候上
	 * @Title: findByCourse 
	 * @Description: TODO
	 * @param course 要查询的课程
	 * @return course一周上课情况
	 * @return: List<WeekCourse>
	 */
	List<WeekCourse> findByCourse(Course course);

	/**
	 * 查询某教室何时上什么课
	 * @Title: findBylocation_Id 
	 * @Description: TODO
	 * @param lid 教室编号
	 * @return 编号为lid的教室一周上课情况
	 * @return: List<WeekCourse>
	 */
	List<WeekCourse> findBylocation_Id(Integer lid);
	

	/**
	 * 根据主键查询课程信息
	 * @Title: findByClasAndWeekAndTime 
	 * @Description: TODO
	 * @param classId 班级编号
	 * @param week 星期几
	 * @param time 第几节课
	 * @return 编号 为classId的班级在某天谋一节课信息
	 * @return: WeekCourse
	 */
	WeekCourse findByClasAndWeekAndTime(Integer classId, Week week, Integer time);
	
	
	/**
	 * 同一时间教师只能在一个位置给一个班上课
	 * @Title: findByTeacher_TidAndWeekAndTime 
	 * @Description: TODO
	 * @param tid 教师工号
	 * @param week 星期几
	 * @param time 第几节课
	 * @return 工号为 tid 的教师在特定时间上课信息
	 * @return: WeekCourse
	 */
	WeekCourse findByTeacher_TidAndWeekAndTime(Integer tid, Week week, Integer time);

	/**
	 * 同一教室在同一时间只能有一个老师给一个班上课
	 * @Title: findByLocation_IdAndWeekAndTime 
	   * @Description: TODO
	 * @param lid 教室编号
	 * @param week 星期几
	 * @param time 第几节课
	 * @return 编号为 tid 的教室在特定时间上课信息
	 * @return: WeekCourse
	 */
	WeekCourse findByLocation_IdAndWeekAndTime(Integer lid, Week week, Integer time);

}
