package com.std.smartcampus.basic.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.smartcampus.basic.annotation.Cache;
import com.std.smartcampus.basic.domain.Course;
import com.std.smartcampus.basic.repository.CourseRepository;
import com.std.smartcampus.basic.service.base.BaseService;

@Service
@Transactional
@Cache(modifyClass=WeekCourseService.class)
public class CourseService extends BaseService<Course, Integer>{
	
	
	@SuppressWarnings("unused")
	private CourseRepository courseRepository;//课程操作
	@Autowired
	public void setRepository(CourseRepository repository){
		this.repository = repository;
		this.courseRepository = repository;
	}

	
}
