package com.std.smartcampus.basic.model;

import java.util.Arrays;
import java.util.List;

import com.std.smartcampus.basic.domain.WeekCourse;
import com.std.smartcampus.basic.domain.WeekCourse.Week;
import com.std.smartcampus.common.util.BeanUtil;

/**
 * 课程表传输类
 * @author Administrator
 *
 */

public class DayCourse {
	
	public List<WeekCourse> list = Arrays.asList(
			(WeekCourse)BeanUtil.getBean(new WeekCourse(Week.MONDAY), true),
			(WeekCourse)BeanUtil.getBean(new WeekCourse(Week.TUESDAY), true),
			(WeekCourse)BeanUtil.getBean(new WeekCourse(Week.WEDNESDAY), true),
			(WeekCourse)BeanUtil.getBean(new WeekCourse(Week.THURSDAY), true),
			(WeekCourse)BeanUtil.getBean(new WeekCourse(Week.FRIDAY), true),
			(WeekCourse)BeanUtil.getBean(new WeekCourse(Week.SATURDAY), true),
			(WeekCourse)BeanUtil.getBean(new WeekCourse(Week.SUNDAY), true));
	
	public DayCourse(List<WeekCourse> weekCourses) {
		for (WeekCourse weekCourse : weekCourses) {
			add(weekCourse);
		}
	}
	public DayCourse() {}
	public void add(WeekCourse weekCourse) {
		switch (weekCourse.getWeek()) {
		case MONDAY:
			list.set(0, weekCourse);
			break;
		case TUESDAY:
			list.set(1, weekCourse);
			break;
		case WEDNESDAY:
			list.set(2, weekCourse);
			break;
		case THURSDAY:
			list.set(3, weekCourse);
			break;
		case FRIDAY:
			list.set(4, weekCourse);
			break;
		case SATURDAY:
			list.set(5, weekCourse);
			break;
		case SUNDAY:
			list.set(6, weekCourse);
			break;
		default:
			break;
		}
	}
	

}
