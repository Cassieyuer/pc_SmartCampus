package com.std.smartcampus.basic.domain;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.std.smartcampus.common.exception.IdCardException;
import com.std.smartcampus.common.util.Validator;
import com.std.smartcampus.common.util.excel.Excel;


/**
 * 教师实体类
 * @ClassName: Teacher 
 * @Description: TODO
 * @author: Administrator
 * @date: 2017年3月26日 上午12:59:25
 */
@Entity
@Excel(sheet="教师")
@Table(name="TEACHER")
@EntityListeners(AuditingEntityListener.class)
public class Teacher implements Serializable {
	private static final long serialVersionUID = 1L;

	@Excel(column="工号") private String tid;//教师ID
	@Excel(column="地址") private String address;//家庭住址
	@Excel(column="姓名") private String username;//姓名
	@Excel(column="密码", write=false) private String password;//密码
	@Excel(column="身份证号") private String idCard;//身份证号
	@Excel(column="生日") private Date birthday;//生日
	@Excel(column="创建时间") private Date createtime;//创建时间
	@Excel(column="修改时间") private Date lastmodifiedTime;//修改时间
	@Excel(column="年龄") private Integer age;//年龄
	@Excel(column="性别") private String sex;//性别

	//mvc-----sql
	//时间戳类型,精确到时分秒
	@DateTimeFormat(iso=ISO.DATE_TIME)
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name="create_time")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	//时间戳类型,精确到秒
	@DateTimeFormat(iso=ISO.DATE_TIME)
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name="last_modified_time")
	@LastModifiedDate
	public Date getLastmodifiedTime() {
		return lastmodifiedTime;
	}
	public void setLastmodifiedTime(Date lastmodifiedTime) {
		this.lastmodifiedTime = lastmodifiedTime;
	}

	//日期类型,精确到天
	@DateTimeFormat(iso=ISO.DATE)
	@Temporal(value = TemporalType.DATE)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Id
	@Column(name="id")
	public String getTid() {
		return this.tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	@Column(name="age",length=3)
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name="sex",length=2)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name="name",length=8)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name="password",length=16)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Pattern(regexp=Validator.REGEX_ID_CARD,message="请输入正确的身份证号")
	@Column(name="id_card",length=18)
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "Teacher [tid=" + tid + ", address=" + address + ", username=" + username + ", password=" + password
				+ ", idCard=" + idCard + ", birthday=" + birthday + ", createtime=" + createtime + ", age=" + age
				+ ", sex=" + sex + "]";
	}
	
	//忽略此字段不让其持久化
	@Transient 
	@JsonIgnore
	public Teacher getInstanceByIdCard() throws ParseException, IdCardException{
		Teacher teacher = this;
		System.out.println(teacher.idCard);
		if (Validator.isIDCard(teacher.idCard)) {
			String idCard = teacher.idCard;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date birthday = sdf.parse(idCard.substring(6, 14));
			Integer flag = Integer.valueOf(idCard.substring(16, 17));
			Calendar birthCal = Calendar.getInstance();
			birthCal.setTime(birthday);
			teacher.password = idCard.substring(idCard.length()-6,idCard.length());
			teacher.sex = (flag%2==0)?"女":"男";
			teacher.age = Calendar.getInstance().get(Calendar.YEAR)-birthCal.get(Calendar.YEAR);
			teacher.birthday = birthday;
		} else {
			throw new IdCardException(1000,"身份证号错误");
		}
		return teacher;
	}
	

}