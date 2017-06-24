package com.std.smartcampus.basic.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.std.smartcampus.basic.domain.Clas;
import com.std.smartcampus.basic.domain.Student;

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
 * 学生Dao层
 * @ClassName: StudentRepository 
 * @Description: TODO
 * @author: Administrator
 * @date: 2017年3月26日 上午1:09:51
 */

public interface StudentRepository extends BaseRepository<Student, String>{

	/**
	 * 根据ID查询创建时间
	 */
	@Query("SELECT s.createTime FROM Student s WHERE s.sid = :sid")
	Date findCreateTimeBySid(@Param("sid") String sid);
	
	

	/**
	 * 根据用户名和密码查找学生
	 * @Title: findByUsernameAndPassword 
	 * @Description: TODO
	 * @param username 用户名
	 * @param password 密码
	 * @return: Student
	 */
	Student findByUsernameAndPassword(String username, String password);

	/**
	 * 查找某班的所有学生
	 * @Title: findByClasIn 
	 * @Description: TODO
	 * @param Clas 某班
	 * @return
	 * @return: List<Student>
	 */
	List<Student> findByClasIn(Clas[] Clas);
	

	/**
	 * 根据学生学号修改学生姓名
	 * @Title: updateStudentUsername 
	 * @Description: TODO
	 * @param sid 学号
	 * @param username 修改之后的姓名
	 * @return 修改成功条数
	 * @return: Integer
	 */
	//@Modifying 可以修改或删除  JPQL不支持INSERT
	@Modifying    
	@Query("UPDATE Student s SET s.username = :username WHERE s.sid = :sid")
	Integer updateStudentUsername(@Param("sid") String sid, @Param("username") String username);

	
	/*
	 * 学习过程中测试的各种注解,已无用
	 */
//	//查询ID最大的Student
//	//@Query 注解可以自定义 JPQL 语句以实现更灵活的查询
//	@Query("SELECT s FROM Student s WHERE s.sid = (SELECT max(s2.sid) FROM Student s2)")
//	Student getMaxIdStudent();
//	
//	//@Query 传递参数 1	占位符方式
//	@Query("SELECT s FROM Student s WHERE s.name = ?1 AND s.age = ?2")
//	Student test1(String name, Integer age);
//	
//	//@Query 传递参数 2	命名参数方式
//	@Query("SELECT s FROM Student s WHERE s.name = :name AND s.age = :age")
//	Student test2(@Param("age") Integer age, @Param("name") String name);
//	
//	//@Query 传递参数 3	%允许在占位符上
//	@Query("SELECT s FROM Student s WHERE s.name = ?1 AND s.age = ?2")
//	Student test3(String name, Integer age);
//	
//	//@Query 传递参数 4	%允许在命名参数上
//	@Query("SELECT s FROM Student s WHERE s.name LIKE %:name% OR s.age LIKE %:age%")
//	Student test4(@Param("age") Integer age, @Param("name") String name);
//	
//	//@Query 传递参数 5	原生SQL语句
//	@Query(value="SELECT count(sid) FROM student", nativeQuery = true)
//	Long getCount();
//	
//	//	WHERE name LIKE ?% AND sid < ?
//	List<Student> getByNameStartingWithAndSidLessThan(String name, Integer sid);
//	List<Student> getByNameEndingWithAndSidLessThan(String name, Integer sid);
////	WHERE name LIKE ?% AND sid < ?
//	List<Student> getByNameInOrSidLessThan(List<String> names, Integer sid);
//	List<Student> getByNameEndingWithOrCreatetimeLessThan(String name, Date createtime);
}
