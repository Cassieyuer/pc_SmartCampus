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
 * 班级实体类
 * @ClassName: Clas 
 *
 * @author: Administrator
 * @date: 2017年3月26日 上午1:01:39
 */
//数据库表名称class
@Table(name = "CLASS")
@Entity
public class Clas {
	
	
	private Integer cid;//主键
	private String name;//班级名称
	
	@Id
	@GeneratedValue
	@Min(1)
	@Max(value=999999, message="班级编号最多6位")
	@Column(name="id",length=6)
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	@NotEmpty(message="班级名称不能为空")
	@Column(name="name",length=8)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/*  
	 * 
	 */
	@Override
	public String toString() {
		return "Clas [cid=" + cid + ", name=" + name + "]";
	}
	
}
