package com.std.smartcampus.basic.controller.pc;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.std.smartcampus.basic.domain.Teacher;
import com.std.smartcampus.basic.service.TeacherService;
import com.std.smartcampus.common.exception.IdCardException;
import com.std.smartcampus.common.util.excel.ExcelUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
/**
 * 教师控制层
 * @ClassName: PCTeacherController 
 * @Description: TODO
 * @author: Administrator
 * @date: 2017年3月26日 上午1:35:29
 */
@Controller
@RequestMapping("/admin")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	
	@ApiOperation(value="打开添加教师界面",notes="")
	@GetMapping(value="/teacher-add")
	public String addTeacher() {
		return "campus/teacher/teacher_add";
	}
	
	@ApiOperation(value="保存或更新一位教师",notes="")
	@ApiImplicitParam(name="teacher",value="教师信息",required = true,dataType="Teacher")
	@PostMapping(value={"/teacher-save","/teacher-update"})
	public String saveTeacher(@Valid  Teacher teacher) throws IdCardException, ParseException {
		teacherService.save(teacher);
		return "redirect:teacher-query?page=0";
	}
	
	@ApiOperation(value="查询所有教师信息",notes="")
	@ApiImplicitParam(name="model",value="向HTML传递的消息",required = true,dataType="Model")
	@GetMapping("/teacher-query")
	public String queryTeacher(@PageableDefault Pageable pageable, Model model) {
		Page<Teacher> list = teacherService.findAll(pageable);
		model.addAttribute("page", list);
		model.addAttribute("teacher_css", "active");
		return "campus/teacher/teacher_query_success";
	}
	
	@ApiOperation(value="删除一位教师",notes="")
	@ApiImplicitParam(name="tid",value="教师工号",required = true,dataType="String")
	@GetMapping("/teacher-delete")
	public String deleteTeacher( String tid) {
		teacherService.delete(tid);
		return "redirect:teacher-query?page=0";
	}
	
	@ApiOperation(value="打开修改教师信息界面",notes="")
	@GetMapping("/teacher-modify")
	public String modifyTeacher( String tid,Model model) {
		Teacher teacher = teacherService.findOne(tid);
		model.addAttribute("teacher", teacher);
		return "campus/teacher/teacher_modify";
	}
	
	//文件下载相关代码
	@ApiOperation(value="以Excel形式下载所有教师",notes="")
	@ApiImplicitParam(name="path",value="文件下载路径",required = true,dataType="String")
    @GetMapping("/teacher-download")
	@ResponseBody
    public void downloadFile(String path) throws IOException{
    	if (!path.endsWith(".xls") && !path.endsWith(".xlsx")) {
    		path += "/teachers.xls";
		}
    	List<Teacher> teachers = teacherService.findAll();
    	ExcelUtil.writeExcel(path, teachers);
    	System.out.println("path="+path);
    }
	
}
