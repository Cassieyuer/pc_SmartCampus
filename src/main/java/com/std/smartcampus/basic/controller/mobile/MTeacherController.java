package com.std.smartcampus.basic.controller.mobile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.std.smartcampus.basic.domain.Teacher;
import com.std.smartcampus.basic.service.TeacherService;
import com.std.smartcampus.common.result.Result;
import com.std.smartcampus.common.util.ResultUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @ClassName: MobileTeacherController 
 * @Description: TODO
 * @author: Administrator
 * @date: 2017年3月26日 上午1:55:16
 */
@RestController
@RequestMapping("/teacher")
public class MTeacherController {
	
	@Autowired
	TeacherService teacherService;//教师业务
	
	@ApiOperation(value="保存一个教师",notes="")
	@ApiImplicitParam(name="teacher",value="教师信息",required = true,dataType="Teacher")
	@PostMapping("/save")
	public Result<Teacher> save(  Teacher teacher){
		teacherService.save(teacher);
		return ResultUtil.success(teacher);
	}
	
	@ApiOperation(value="删除一个教师",notes="")
	@ApiImplicitParam(name="tid",value="教师工号",required = true,dataType="String")
	@GetMapping("/delete")
	public Result<Teacher> delete( String tid){
		Teacher teacher = teacherService.findOne(tid);
		teacherService.delete(tid);
		return ResultUtil.success(teacher);
	}
	
	@ApiOperation(value="查询所有教师",notes="")
	@GetMapping("/query")
	public Result<List<Teacher>> query(){
		List<Teacher> list = teacherService.findAll();
		return ResultUtil.success(list);
	}
	
	@ApiOperation(value="教师登陆",notes="")
	@ApiImplicitParam(name="teacher",value="教师信息",required = true,dataType="Teacher")
	@PostMapping("/login")
	public Result<Teacher> login(  Teacher teacher){
		Teacher result = teacherService.findByNameAndPassword(teacher.getUsername(), teacher.getPassword());
		if (result != null) {
			return ResultUtil.success(result);
		}
		return ResultUtil.error(-1, "用户名或密码错误");
	}

}
