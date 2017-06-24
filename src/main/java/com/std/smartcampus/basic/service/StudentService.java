package com.std.smartcampus.basic.service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.smartcampus.basic.annotation.Base64;
import com.std.smartcampus.basic.domain.Clas;
import com.std.smartcampus.basic.domain.Student;
import com.std.smartcampus.basic.repository.StudentRepository;
import com.std.smartcampus.basic.service.base.BaseService;

@Service
@Transactional
public class StudentService extends BaseService<Student, String>{
	
	private StudentRepository studentRepository;//学生操作
	@Autowired
	public void setRepository(StudentRepository repository) {
		this.repository = repository;
		this.studentRepository = repository;
	}
	/**
	 * 保存一条学生数据到数据库
	 * @param student
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Base64
	public Student save(Student student) {
		Date createtime = studentRepository.findCreateTimeBySid(student.getSid());
		if (createtime == null) {
			createtime = new Date();
		}
		student.setCreateTime(createtime);
		return studentRepository.save(student);
	}
	/** 
	 * 
	 */
	@Base64
	public void save(List<Student> students) {
		studentRepository.save(students);
	}

	

	

	/**
	 * 根据用户名和密码查找学生
	 * @param username
	 * @param password
	 * @return
	 */
	@Base64
	public Student findByNameAndPassword(String username, String password) {
		return studentRepository.findByUsernameAndPassword(username, password);
	}

	/**
	 * 查询某某班的学生
	 * @param clas
	 * @return
	 */
	public List<Student> findByClasIn(Clas[] clas) {
		return studentRepository.findByClasIn(clas);
	}

	/**
	 * 根据ID修改学生姓名
	 * @param sid
	 * @param name
	 * @return
	 */
	public Integer updateStudentName(String sid, String name) {
		return studentRepository.updateStudentUsername(sid, name);
	}

	/** 
	 * 更新学生年龄
	 */
	public void updateStudentAge() {
		List<Student> students = studentRepository.findAll();
		students.forEach(student -> {
			Calendar birthday = Calendar.getInstance();
			birthday.setTime(student.getBirthday());
			student.setAge(LocalDate.now().getYear()-birthday.get(Calendar.YEAR));
		});
		studentRepository.save(students);
	}
	
	

}
