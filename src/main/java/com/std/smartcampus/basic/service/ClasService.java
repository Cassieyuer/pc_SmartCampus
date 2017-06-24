package com.std.smartcampus.basic.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.smartcampus.basic.annotation.Cache;
import com.std.smartcampus.basic.domain.Clas;
import com.std.smartcampus.basic.repository.ClasRepository;
import com.std.smartcampus.basic.service.base.BaseService;

/**
 * @author Administrator
 */
@Service
@Transactional
@Cache(modifyClass=WeekCourseService.class)
public class ClasService extends BaseService<Clas, Integer>{

	private ClasRepository clasRepository;
	
	@Autowired
	public void setRepository(ClasRepository repository) {
		this.repository = repository;
		this.clasRepository = repository;
	}

	/**
	 * 根据名称查询班级
	 * @param cname 班级名称
	 */
	public Clas findByName(String cname) {
		return clasRepository.findByName(cname);
	}

}
