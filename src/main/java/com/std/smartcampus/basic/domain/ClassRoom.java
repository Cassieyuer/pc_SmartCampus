package com.std.smartcampus.basic.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 教室实体类
 * @ClassName: ClassRoom 
 *
 * @author: Administrator
 * @date: 2017年3月26日 上午1:01:25
 */
@Entity
@Table( name="CLASS_ROOM")
public class ClassRoom {
	private Integer id;
	private String location;
	
	@Id
	@GeneratedValue
	@Min(1)
	@Max(value=999999, message="教室编号最多6位")
	@Column(name="id",length=6)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@NotEmpty(message="教室名称不能为空")
	@Column(name="location",length=16)
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	/*  
	 * 
	 */
	@Override
	public String toString() {
		return "ClassRoom [id=" + id + ", location=" + location + "]";
	}
}
