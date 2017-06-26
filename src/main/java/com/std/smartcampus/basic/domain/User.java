package com.std.smartcampus.basic.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 管理员实体类
 * @ClassName: Admin 
 *
 * @author: Administrator
 * @date: 2017年3月26日 上午1:01:52
 */
@Entity
@Table(name="user")
public class User {
	
	private Integer id;//用户id
	private String username;//用户名
	private String password;//密码
	@Id
	@GeneratedValue
	@Column(name="id",length=2)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@NotEmpty(message="用户名不能为空")
	@Column(name="name", length=16, unique=true)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Size(min=6,max=16,message="密码必须为6——16位")
	@Column(name="password",length=16)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	/*  
	 * 
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
}
