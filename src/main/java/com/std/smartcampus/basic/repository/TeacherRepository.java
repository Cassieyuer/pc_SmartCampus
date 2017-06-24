package com.std.smartcampus.basic.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.std.smartcampus.basic.domain.Teacher;

/**
 * 教师Dao层
 * @ClassName: TeacherRepository 
 * @Description: TODO
 * @author: Administrator
 * @date: 2017年3月26日 上午1:12:34
 */
public interface TeacherRepository extends BaseRepository<Teacher, String>{
	
	/**
	 * 根据ID查询创建时间
	 */
	@Query("SELECT t.createtime FROM Teacher t WHERE t.tid = :tid")
	Date findCreatetimeByTid(@Param("tid") String tid);

	/**
	 * 根据用户名和密码查找教师
	 * @Title: findByUsernameAndPassword 
	 * @Description: TODO
	 * @param username 用户名 
	 * @param password 密码
	 * @return: Teacher 教师实体
	 */
	Teacher findByUsernameAndPassword(String username, String password);

}
