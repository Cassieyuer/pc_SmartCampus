package com.std.smartcampus.basic.controller.pc;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.std.smartcampus.basic.domain.Course;
import com.std.smartcampus.basic.service.CourseService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
/**
 * 课程控制层
 * @ClassName: PCCourseController 
 *
 * @author: Administrator
 * @date: 2017年3月26日 上午1:39:55
 */
@Controller
@RequestMapping("/admin")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@ApiOperation(value="查询所有课程",notes="")
	@ApiImplicitParam(name="model",value="向HTML传递的消息",required = true,dataType="Model")
	@GetMapping("/course-query")
	public String queryCourse(@PageableDefault Pageable pageable, Model model){
		Page<Course> list = courseService.findAll(pageable);
		model.addAttribute("page", list);
		model.addAttribute("course_css", "active");
		return "campus/course/course_query_success";
	}
	
	@ApiOperation(value="去往添加课程页面",notes="")
	@GetMapping("/course-add")
	public String addCourse(){
		return "campus/course/course_add";
	}

	@ApiOperation(value="保存或更新一条课程信息",notes="")
	@ApiImplicitParam(name="course",value="课程信息",required = true,dataType="Course")
	@PostMapping({"/course-save","/course-update"})
	public String saveCourse(@Valid Course course){
		courseService.save(course);
		return "redirect:course-query?page=0";
	}
	
	@ApiOperation(value="删除一条课程信息",notes="")
	@ApiImplicitParam(name="cid",value="课程编号",required = true,dataType="String")
	@GetMapping("/course-delete")
	public String deleteCourse(Integer cid) {
		courseService.delete(cid);
		return "redirect:course-query?page=0";
	}
	
	@ApiOperation(value="打开修改课程信息界面",notes="")
	@ApiImplicitParams(value={
			@ApiImplicitParam(name="cid",value="课程编号",required = true,dataType="String"),
			@ApiImplicitParam(name="model",value="向HTML传递的消息",required = true,dataType="Model")
	})
	@GetMapping("/course-modify")
	public String modifyCourse(Integer cid,Model model) {
		Course course = courseService.findOne(cid);
		model.addAttribute("course", course);
		return "campus/course/course_modify";
	}
}
