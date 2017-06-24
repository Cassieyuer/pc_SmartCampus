/**   
 * Copyright © 2017 公司名. All rights reserved.
 */
package com.std.smartcampus.basic.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/** 
 * @author: 李志鹏
 * @date: 2017年4月13日 上午12:36:41 
 */
@Entity
@Table(name="S_C_SCORE")
public class SCScore {
	
	private Integer id;
	private Student student;
	private Course course;
	private Float score;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.MERGE})
	@JoinColumn(name="sid")
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	@ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.MERGE})
	@JoinColumn(name="cid")
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	@Column(name="score",length=3)
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}

	/*  
	 * 
	 */
	@Override
	public String toString() {
		return "SCScore [id=" + id + ", student=" + student + ", course=" + course + ", score=" + score + "]";
	}
	
	

}
