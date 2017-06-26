package com.std.smartcampus.basic.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 课程实体类
 * @ClassName: Course 
 *
 * @author: Administrator
 * @date: 2017年3月26日 上午1:01:11
 */
@Entity
@Table(name="COURSE")
@NamedQuery(name="Course.findAll", query="SELECT c FROM Course c")
public class Course {
	
	
	private Integer cid;//课程ID
	private String name;//课程名称
	private Integer credit;//学分
	private Set<SCScore> scores=new HashSet<>();//成绩
	@Id
	@GeneratedValue
	@Min(1)
	@Max(value=999999, message="课程编号最多6位")
	@Column(name="id",length=6)
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	@NotEmpty(message="课程名称不能为空")
	@Column(name="name",length=16)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Min(value=1,message="学分最小为1")
	@Max(value=100,message="学分最大为100")
	@Column(name="credit",length=3)
	public Integer getCredit() {
		return credit;
	}
	public void setCredit(Integer credit) {
		this.credit = credit;
	}
	/*  
	 * 
	 */
	@Override
	public String toString() {
		return "Course [cid=" + cid + ", name=" + name + ", credit=" + credit + "]";
	}
	@JsonIgnore
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.EAGER,mappedBy="course")
	public Set<SCScore> getScores() {
		return scores;
	}
	public void setScores(Set<SCScore> scores) {
		this.scores = scores;
	}
	

}
