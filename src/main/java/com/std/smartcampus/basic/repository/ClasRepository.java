package com.std.smartcampus.basic.repository;

import com.std.smartcampus.basic.domain.Clas;
/**
 * 班级Dao层
 * @ClassName: ClasRepository 
 *
 * @author: Administrator
 * @date: 2017年3月26日 上午1:04:20
 */
public interface ClasRepository extends BaseRepository<Clas, Integer>{

	/**
	 * 根据名称查询班级
	 * @Title: findByName 
	 *
	 * @param name 班级名字
	 * @return 班级实体类
	 * @return: Clas
	 */
	Clas findByName(String name);
}
