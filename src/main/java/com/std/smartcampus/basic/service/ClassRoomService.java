package com.std.smartcampus.basic.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.smartcampus.basic.annotation.Cache;
import com.std.smartcampus.basic.domain.ClassRoom;
import com.std.smartcampus.basic.repository.ClassRoomRepository;
import com.std.smartcampus.basic.service.base.BaseService;

@Service
@Transactional
@Cache(modifyClass=WeekCourseService.class)
public class ClassRoomService extends BaseService<ClassRoom, Integer>{
	
	
	@SuppressWarnings("unused")
	private ClassRoomRepository classRoomRepository;//课程操作
	
	
	@Autowired
	public void setRepository(ClassRoomRepository repository) {
		this.repository = repository;
		this.classRoomRepository = repository;
	}

	
}
