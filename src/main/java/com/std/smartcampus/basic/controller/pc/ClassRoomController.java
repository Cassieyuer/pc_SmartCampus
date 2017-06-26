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

import com.std.smartcampus.basic.domain.ClassRoom;
import com.std.smartcampus.basic.service.ClassRoomService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @ClassName: PCClassRoomController 
 *
 * @author: Administrator
 * @date: 2017年3月26日 上午1:40:34
 */

@Controller
@RequestMapping("/admin")
public class ClassRoomController {

	@Autowired
	private ClassRoomService classRoomService;

	@ApiOperation(value="查询所有教室",notes="")
	@ApiImplicitParam(name="model",value="向HTML传递的消息",required = true,dataType="Model")
	@GetMapping("/classroom-query")
	public String queryClassroom(@PageableDefault Pageable pageable, Model model){
		Page<ClassRoom> list = classRoomService.findAll(pageable);
		model.addAttribute("page", list);
		model.addAttribute("class_room_css", "active");
		return "campus/classroom/classroom_query_success";
	}

	@ApiOperation(value="打开添加教室界面",notes="")
	@GetMapping("/classroom-add")
	public String addClassroom(){
		return "campus/classroom/classroom_add";
	}


	@ApiOperation(value="保存或修改一个教室",notes="")
	@ApiImplicitParam(name="classRoom",value="课程信息",required = true,dataType="ClassRoom")
	@PostMapping({"/classroom-save","/classroom-update"})
	public String saveClassroom(@Valid ClassRoom classRoom){
		classRoomService.save(classRoom);
		return "redirect:classroom-query?page=0";
	}
	
	@ApiOperation(value="删除一个教室",notes="")
	@ApiImplicitParam(name="cid",value="教室编号",required = true,dataType="String")
	@GetMapping("/classroom-delete")
	public String deleteClassroom(Integer cid) {
		classRoomService.delete(cid);
		return "redirect:classroom-query?page=0";
	}

	@ApiOperation(value="打开修改教室信息界面",notes="")
	@ApiImplicitParams(value={
			@ApiImplicitParam(name="cid",value="教室编号",required = true,dataType="String"),
			@ApiImplicitParam(name="model",value="向HTML传递的消息",required = true,dataType="Model")
	})
	@GetMapping("/classroom-modify")
	public String modifyClassroom(Integer cid,Model model) {
		ClassRoom classRoom = classRoomService.findOne(cid);
		model.addAttribute("class_room", classRoom);
		return "campus/classroom/classroom_modify";
	}
}
