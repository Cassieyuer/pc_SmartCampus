package com.std.smartcampus.basic.controller.mobile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.std.smartcampus.basic.domain.Clas;
import com.std.smartcampus.basic.domain.Student;
import com.std.smartcampus.basic.service.StudentService;
import com.std.smartcampus.common.result.Result;
import com.std.smartcampus.common.util.ResultUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @RestController 返回json数据 ,而不是视图
 * @GetMapping ("/student") 本类的跟URL为 http://127.0.0.1:8080/student
 *
 */
/**
 * 
 * @ClassName: MobileStudentController 
 *
 * @author: Administrator
 * @date: 2017年3月26日 上午1:52:56
 */
@RestController
@RequestMapping("/student")
public class MStudentController {
	
	
	@Autowired
	StudentService studentService;//学生业务
	
	@ApiOperation(value="保存一条学生数据到数据库",notes="")
	@ApiImplicitParam(name="student",value="学生信息",required = true,dataType="Student")
	@PostMapping("/save")
	public Result<Student> save(  Student student){
		Student data = studentService.save(student);
		return ResultUtil.success(data);
	}


	@ApiOperation(value="删除学生",notes="")
	@ApiImplicitParam(name="sid",value="学生学号",required = true,dataType="String")
	@GetMapping("/delete")
	public Result<Student> delete( String sid){
		Student Student = studentService.findOne(sid);
		studentService.delete(sid);
		return ResultUtil.success(Student);
	}

	
	@ApiOperation(value="查询所有学生",notes="")
	@GetMapping("/query")
	public Result<List<Student>> query(){
		List<Student> list = studentService.findAll();
		return ResultUtil.success(list);
	}
	// http://localhost:8080/student/page?page=5&size=20&sort=age,desc&sort=username
	@ApiOperation(value="查询所有学生",notes="")
	@GetMapping(value = "/page")
	public Result<Page<Student>> getEntryByPageable(@PageableDefault Pageable pageable) {
	    return ResultUtil.success(studentService.findAll(pageable));
	}

	
	@ApiOperation(value="查找指定班级的学生",notes="")
	@ApiImplicitParam(name="clas",value="学生信息",required = true,dataType="clas")
	@GetMapping("/find")
	public Result<List<Student>> find(  Clas clas){
		List<Student> list = studentService.findByClasIn(new Clas[]{clas});
		return ResultUtil.success(list);
	}

	
	@ApiOperation(value="根据学号修改姓名",notes="")
	@ApiImplicitParams(value={
			@ApiImplicitParam(name="sid",value="学生学号",required = true,dataType="String"),
			@ApiImplicitParam(name="name",value="学生姓名",required = true,dataType="String")
	})
	@GetMapping("/update/{sid}&{name}")
	public Result<Student> updateStudentName(@PathVariable String sid,@PathVariable String name){
		studentService.updateStudentName(sid,name);
		return ResultUtil.success();
	}
	@ApiOperation(value="学生登陆",notes="")
	@ApiImplicitParam(name="student",value="学生信息",required = true,dataType="Student")
	@PostMapping("/login")
	public Result<Student> login(  Student student){
		Student result = studentService.findByNameAndPassword(student.getUsername(), student.getPassword());
		if (result != null) {
			return ResultUtil.success(result);
		}
		return ResultUtil.error(-1, "用户名或密码错误");
	}
}
