package com.std.smartcampus.basic.domain;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.std.smartcampus.common.exception.IdCardException;
import com.std.smartcampus.common.util.Validator;
import com.std.smartcampus.common.util.excel.Excel;

/**
 * 学生实体类
 * @ClassName: Student 
 * @Description: TODO
 * @author: Administrator
 * @date: 2017年3月26日 上午1:00:15
 */
@Entity
@Excel(sheet="学生")
@Table(name="STUDENT")
@EntityListeners(AuditingEntityListener.class)
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Excel(column="学号") private String sid;//学生ID
	@Excel(column="性别") private String sex;//性别
	@Excel(column="地址") private String address;//家庭住址
	@Excel(column="姓名") private String username;//姓名
	@Excel(column="密码", write=false) private String password;//密码
	@Excel(column="身份证号") private String idCard;//身份证号
	@Excel(column="出生日期") private Date birthday;//生日
	@Excel(column="创建时间") private Date createTime;//创建时间
	@Excel(column="修改时间") private Date lastModifiedTime;//创建时间
	@Excel(column="年龄") private Integer age;//年龄
	private Clas clas;//班级
	private Set<SCScore> scores=new HashSet<>();//成绩

	//时间戳类型,精确到秒
	@DateTimeFormat(iso=ISO.DATE_TIME)
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name="create_time")
	@CreatedDate
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	//时间戳类型,精确到秒
	@DateTimeFormat(iso=ISO.DATE_TIME)
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name="last_modified_time")
	@LastModifiedDate
	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	/**
	 * 
	 */
	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
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
	@Size(min=1,max=10,message="学号长度错误")
	@Column(name="id",length=10)
	public String getSid() {
		return this.sid;
	}
	
	public void setSid(String sid) {
		this.sid = sid;
	}
	@Column(name="age",length=3)
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	//与班级多对一,数据库列名class
	@ManyToOne
	@JoinColumn(name = "class")
	public Clas getClas() {
		return this.clas;
	}

	public void setClas(Clas clas) {
		this.clas = clas;
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
	@Column(name="address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@JsonIgnore
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.EAGER,mappedBy="student")
	public Set<SCScore> getScores() {
		return scores;
	}

	public void setScores(Set<SCScore> scores) {
		this.scores = scores;
	}

	/*  
	 * 
	 */
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sex=" + sex + ", address=" + address + ", username=" + username
				+ ", password=" + password + ", idCard=" + idCard + ", birthday=" + birthday + ", createTime="
				+ createTime + ", lastModifiedTime=" + lastModifiedTime + ", age=" + age + ", clas=" + clas + "]";
	}
	//忽略此字段不让其持久化
	@Transient 
	@JsonIgnore
	public Student getInstanceByIdCard() throws ParseException, IdCardException{
//		Assert.notNull(this.password, "不是第一次创建");
		Student student = this;
		if (Validator.isIDCard(student.idCard)) {
			String idCard = student.idCard;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date birthday = sdf.parse(idCard.substring(6, 14));
			Integer flag = Integer.valueOf(idCard.substring(16, 17));
			Calendar birthCal = Calendar.getInstance();
			birthCal.setTime(birthday);
			student.password = idCard.substring(idCard.length()-6,idCard.length());
			student.sex = (flag%2==0)?"女":"男";
			student.age = Calendar.getInstance().get(Calendar.YEAR)-birthCal.get(Calendar.YEAR);
			student.birthday = birthday;
		} else {
			throw new IdCardException(1000, "身份证号错误");
		}
		return student;
	}
}