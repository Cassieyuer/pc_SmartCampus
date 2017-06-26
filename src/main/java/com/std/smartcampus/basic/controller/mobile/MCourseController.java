package com.std.smartcampus.basic.controller.mobile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.std.smartcampus.basic.domain.Course;
import com.std.smartcampus.basic.service.CourseService;
import com.std.smartcampus.common.result.Result;
import com.std.smartcampus.common.util.ResultUtil;

import io.swagger.annotations.ApiOperation;
/**
 * 
 * @ClassName: MobileCourseController 
 *
 * @author: Administrator
 * @date: 2017年3月26日 上午1:51:37
 */
@RestController
@RequestMapping("/course")
public class MCourseController {
	
	@Autowired
	CourseService courseService;//课程业务
	
	@ApiOperation(value="查询所有课程",notes="")
	@GetMapping("/query")
	public Result<List<Course>> findAll(){
		List<Course> list = courseService.findAll();
		return ResultUtil.success(list);
	}
	
//	@ApiOperation(value="保存一条课程信息",notes="")
//	@ApiImplicitParam(name="course", value="课程编号", required=true, dataType="Course")
//	@GetMapping("/save")
//	public Result<Course> save( Course course){
//		courseService.save(course);
//		return ResultUtil.success(course);
//	}

}
