package com.std.smartcampus.basic.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.std.smartcampus.basic.domain.User;

/**
 * Repository 是空接口,即是一个空接口
 * 若继承他,则会被识别成一个IOC Bean
 * 可以自动实现与数据库相关的方法
 * 也可以通过注解方式
 * @RepositoryDefinition(domainClass=Clas.class,idClass=Integer.class)
 * 实现此功能
 *
 */

/**
 * 在 Repository接口中
 * 1.不是随便声明的,需要符合一定功能
 * 2.查询方法以get|read|find开头
 * 3.涉及条件查询时,各属性名以 Or|AND|连接,各属性名并以大写开头
 * 4.级联属性  中间用下划线隔开
 *
 */
/**
 * 管理员Dao层
 * @ClassName: AdminRepository 
 *
 * @author: Administrator
 * @date: 2017年3月26日 上午1:02:14
 */
public interface UserRepository extends BaseRepository<User, Integer>{

	
	/**
	 * 根据用户名和密码查找管理员
	 * @Title: findByUsernameAndPassword 
	 *
	 * @param username 用户名 
	 * @param password 密码
	 * @return
	 * @return: Admin
	 */
	User findByUsernameAndPassword(String username, String password);



	/**
	 * 
	 * @Title: updateAdminPassword 
	 *
	 * @param username
	 * @param password
	 * @return
	 * @return: Integer
	 */
	@Modifying
	@Query("UPDATE User u SET u.password = :password WHERE u.username = :username")
	Integer updateUserPassword(String username, String password);

	/**
	 * 
	 * @Title: findByUsername 
	 *
	 * @param username
	 * @return
	 * @return: Admin
	 */
	User findByUsername(String username);
}
