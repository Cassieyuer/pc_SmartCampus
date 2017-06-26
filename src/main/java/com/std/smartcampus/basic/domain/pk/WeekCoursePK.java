package com.std.smartcampus.basic.domain.pk;

import java.io.Serializable;

import com.std.smartcampus.basic.domain.WeekCourse.Week;

/**
 * 课程表主键
 * @ClassName: WeekCoursePK 
 *
 * @author: Administrator
 * @date: 2017年3月26日 上午1:00:55
 */
public class WeekCoursePK implements Serializable{
	
	/** 
	 * 
	 */
	public WeekCoursePK(Integer clas, Week week, Integer time) {
		super();
		this.clas = clas;
		this.week = week;
		this.time = time;
	}
	public WeekCoursePK() {
	}
	private static final long serialVersionUID = 8509622799980001711L;
	private Integer clas;//班级ID
	private Week week;//周几
	private Integer time;//节次
	
	public Integer getClas() {
		return clas;
	}
	public void setClas(Integer clas) {
		this.clas = clas;
	}
	public Week getWeek() {
		return week;
	}
	public void setWeek(Week week) {
		this.week = week;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clas == null) ? 0 : clas.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((week == null) ? 0 : week.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeekCoursePK other = (WeekCoursePK) obj;
		if (clas == null) {
			if (other.clas != null)
				return false;
		} else if (!clas.equals(other.clas))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (week != other.week)
			return false;
		return true;
	}
    
}
