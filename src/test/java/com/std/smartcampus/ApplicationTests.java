package com.std.smartcampus;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.std.smartcampus.basic.domain.Student;
import com.std.smartcampus.basic.domain.Teacher;
import com.std.smartcampus.basic.domain.User;
import com.std.smartcampus.basic.service.StudentService;
import com.std.smartcampus.basic.service.TeacherService;
import com.std.smartcampus.basic.service.UserService;
import com.std.smartcampus.common.util.excel.ExcelUtil;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private CreateIDCardNo cre;
	@Autowired
	private StudentService studentService;
	@Autowired
	private UserService userService;
	@Autowired
	private TeacherService teacherService;

	
    @Test
    public void contextLoader(){
    	System.out.println(cre.getRandomID());
    }
//    @Test
//    public void testSaveUser(){
//    	User user = new User();
//    	user.setId(2);
//    	user.setUsername("root");
//    	user.setPassword("1234567");
//    	userService.save(user);
//    }
    
    @Test
    public void base64Encode(){
    	List<User> students = userService.findAll();
    	userService.save(students);
    }
    @Test
    public void testSave(){
    	Student student = new Student();
    	for (int i = 0; i < 100; i++) {
    		student.setSid("Test"+(i+200));
    		student.setAddress("-----------------");
    		student.setUsername("Tom"+new Random().nextInt(1000));
    		student.setIdCard(cre.getRandomID());
    		studentService.save(student);
		}
    }
    @Test
    public void testDownLoad(){
    	List<Teacher> list = teacherService.findAll();
    	ExcelUtil.writeExcel("E:/temp.xls", list);
    }
    @Test
    public void find(){
    	List<Teacher> list = teacherService.findAll();
    	list.forEach(System.out::println);
    }
    @Test
    public void test(){
    	
     }
    
   
}
