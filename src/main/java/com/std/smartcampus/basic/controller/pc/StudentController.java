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

import com.std.smartcampus.basic.domain.Clas;
import com.std.smartcampus.basic.domain.Student;
import com.std.smartcampus.basic.service.ClasService;
import com.std.smartcampus.basic.service.StudentService;
import com.std.smartcampus.common.exception.IdCardException;
import com.std.smartcampus.common.util.excel.ExcelUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
/**
 * 电脑端学生控制层
 * @ClassName: PCStudentController 
 * @author: Administrator
 * @date: 2017年3月26日 上午1:37:35
 */
@Controller
@RequestMapping("/admin")
public class StudentController {

	@Autowired
	private StudentService studentService;
	@Autowired
	private ClasService clasService;
	
	@ApiOperation(value="打开添加学生界面",notes="")
	@ApiImplicitParam(name="model",value="向HTML传递的消息",required = true,dataType="Model")
	@GetMapping("/stu-add")
	public String addStudent(Model model) {
		List<Clas> list = clasService.findAll();
		model.addAttribute("class_list", list);
		return "campus/students/students_add";
	}
	
	@ApiOperation(value="保存或更新一位学生",notes="")
	@ApiImplicitParam(name="student",value="学生信息",required = true,dataType="Student")
	@PostMapping({"/stu-save","/stu-update"})
	public String saveStudent(@Valid Student student,Integer cid) throws IdCardException, ParseException {
		Clas clas = clasService.findOne(cid);
		student.setClas(clas);
		studentService.save(student);
		return "redirect:stu-query?page=0";
	}
	
	@ApiOperation(value="查询所有学生",notes="")
	@ApiImplicitParam(name="model",value="向HTML传递的消息",required = true,dataType="Model")
	@GetMapping("/stu-query")
	public String queryStudent(@PageableDefault Pageable pageable, Model model) {
		Page<Student> list = studentService.findAll(pageable);
		model.addAttribute("page", list);
		model.addAttribute("stu_css", "active");
		return "campus/students/students_query_success";
	}
	
	@ApiOperation(value="删除一位学生",notes="")
	@ApiImplicitParam(name="sid",value="学生学号",required = true,dataType="String")
	@GetMapping("/stu-delete")
	public String deleteStudent( String sid) {
		studentService.delete(sid);
		return "redirect:stu-query?page=0";
	}
	
	@ApiOperation(value="打开修改学生界面",notes="")
	@ApiImplicitParams(value={
			@ApiImplicitParam(name="sid",value="学生学号",required = true,dataType="String"),
			@ApiImplicitParam(name="model",value="向HTML传递的消息",required = true,dataType="Model")
	})
	@GetMapping("/stu-modify")
	public String modifyStudent(String sid ,Model model) {
		Student student = studentService.findOne(sid);
		List<Clas> list = clasService.findAll();
		model.addAttribute("class_list", list);
		model.addAttribute("student", student);
		return "campus/students/students_modify";
	}
	
	//文件下载相关代码
	@ApiOperation(value="以Excel形式下载所有学生",notes="")
	@ApiImplicitParam(name="path",value="文件下载路径",required = true,dataType="String")
    @GetMapping("/stu-download")
	@ResponseBody
    public void downloadFile(String path) throws IOException{
    	if (!path.endsWith(".xls") && !path.endsWith(".xlsx")) {
    		path += "students.xls";
		}
    	List<Student> students = studentService.findAll();
    	ExcelUtil.writeExcel(path, students);
    	System.out.println("path="+path);
    }
	
	
}
