package com.std.smartcampus.basic.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.std.smartcampus.basic.domain.pk.WeekCoursePK;


/**
 * 课程表实体类
 * @ClassName: WeekCourse 
 * @Description: TODO
 * @author: Administrator
 * @date: 2017年3月26日 上午1:00:38
 */
@Entity
@Table(name = "WEEK_COURSE", uniqueConstraints = {
		  @UniqueConstraint(columnNames = {"week", "time", "clas"}),
	      @UniqueConstraint(columnNames = {"week", "time", "teacher"}),
	      @UniqueConstraint(columnNames = {"week", "time", "location"})
	})
@IdClass(WeekCoursePK.class)
public class WeekCourse {

	private Teacher teacher;//教师ID
	private Integer clas;//班级ID
	private Course course;//课程ID
	private ClassRoom location;//教室ID
	private Week week;//周几
	private Integer time;//节次
	private String className;//班级名字
	
	public WeekCourse() {}
	
	public WeekCourse(Teacher teacher, Integer clas, Course course, ClassRoom location, Week week, Integer time, String className) {
		this.teacher = teacher;
		this.clas = clas;
		this.course = course;
		this.location = location;
		this.week = week;
		this.time = time;
		this.className = className;
	}
	public WeekCourse(Week week) {
		this.week = week;
	}

	public WeekCourse(Integer clas, Week week, Integer time) {
		this(null, clas, null, null, week, time, null);
	}

	//与教师多对一
	@ManyToOne
	@JoinColumn(name = "teacher")
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	//与班级多对一
	@Id
	@JoinColumn(name = "class")
	public Integer getClas() {
		return clas;
	}
	public void setClas(Integer clas) {
		this.clas = clas;
	}
	//与课程多对一
	@ManyToOne
	@JoinColumn(name="course")
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	//与教室多对一
	@ManyToOne
	@JoinColumn(name="location")
	public ClassRoom getLocation() {
		return location;
	}
	public void setLocation(ClassRoom location) {
		this.location = location;
	}
	//设置数据库中显示字符串而不是数字
	@Id
	@Enumerated(EnumType.STRING)
	@Column(name="week",length=10)
	public Week getWeek() {
		return week;
	}
	public void setWeek(Week week) {
		this.week = week;
	}
	@Id
	@Column(name="time",length=2)
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	//忽略此字段不让其持久化
	@Transient 
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	/*  
	 * 
	 */
	@Override
	public String toString() {
		return "WeekCourse [teacher=" + teacher + ", clas=" + clas + ", course=" + course + ", location=" + location
				+ ", week=" + week + ", time=" + time + ", className=" + className + "]";
	}
	
	public enum Week {
		MONDAY, 
		TUESDAY, 
		WEDNESDAY, 
		THURSDAY, 
		FRIDAY, 
		SATURDAY,
		SUNDAY
	}
	public static class Time {
		public static Integer I = 0;
		public static Integer II = 1;
		public static Integer III = 2;
		public static Integer IV = 3;
		public static Integer V = 4;
		public static Integer VI = 5;
		public static Integer VII = 6;
		public static Integer VIII = 7;
	}

}
