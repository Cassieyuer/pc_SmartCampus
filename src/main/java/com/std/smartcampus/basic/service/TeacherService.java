package com.std.smartcampus.basic.service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.smartcampus.basic.annotation.Base64;
import com.std.smartcampus.basic.annotation.Cache;
import com.std.smartcampus.basic.domain.Teacher;
import com.std.smartcampus.basic.repository.TeacherRepository;
import com.std.smartcampus.basic.service.base.BaseService;

@Service
@Transactional
@Cache(modifyClass=WeekCourseService.class)
public class TeacherService extends BaseService<Teacher, String>{

	TeacherRepository teacherRepository;//教师操作
	@Autowired
	public void setRepository(TeacherRepository repository) {
		this.repository = repository;
		this.teacherRepository = repository;
	}
	
	/**
	 * 保存一条教师数据到数据库
	 * @param teacher
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Base64
	public Teacher save(Teacher teacher) {
		Date createtime = teacherRepository.findCreatetimeByTid(teacher.getTid());
		if (createtime == null) {
			createtime = new Date();
		}
		teacher.setCreatetime(createtime);
		return teacherRepository.save(teacher);
	}

	/** 
	 * 
	 */
	@Base64
	public void save(List<Teacher> entities) {
		teacherRepository.save(entities);
	}


	/**
	 * 根据教师姓名和密码查找教师
	 * @param username
	 * @param password
	 * @return
	 */
	@Base64
	public Teacher findByNameAndPassword(String username, String password) {
		return teacherRepository.findByUsernameAndPassword(username, password);
	}


	/** 
	 * 更新教师年龄
	 */
	public void updateTeacherAge() {
		List<Teacher> teachers = teacherRepository.findAll();
		teachers.forEach(teacher -> {
			Calendar birthday = Calendar.getInstance();
			birthday.setTime(teacher.getBirthday());
			teacher.setAge(LocalDate.now().getYear()-birthday.get(Calendar.YEAR));
		});
		teacherRepository.save(teachers);
	}


}
