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

import com.std.smartcampus.basic.domain.Clas;
import com.std.smartcampus.basic.service.ClasService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
/**
 * 
 * @author: 李志鹏
 * @date: 2017年4月13日 下午11:48:35
 */
@Controller
@RequestMapping("/admin")
public class ClasController {

	@Autowired
	private ClasService clasService;
	
	@ApiOperation(value="查询所有教室",notes="")
	@ApiImplicitParam(name="model",value="向HTML传递的消息",required = true,dataType="Model")
	@GetMapping("/class-query")
	public String queryClas(@PageableDefault Pageable pageable, Model model){
		Page<Clas> list = clasService.findAll(pageable);
		model.addAttribute("page", list);
		model.addAttribute("class_css", "active");
		return "campus/class/class_query_success";
	}
	
	@ApiOperation(value="打开添加教室界面",notes="")
	@GetMapping("/class-add")
	public String addClas(){
		return "campus/class/class_add";
	}
	
	@ApiOperation(value="保存或修改一个教室",notes="")
	@ApiImplicitParam(name="clas",value="课程信息",required = true,dataType="Clas")
	@PostMapping({"/class-save","/class-update"})
	public String saveClas(@Valid Clas clas){
		clasService.save(clas);
		return "redirect:class-query?page=0";
	}
	
	@ApiOperation(value="删除一个教室",notes="")
	@ApiImplicitParam(name="cid",value="教室编号",required = true,dataType="String")
	@GetMapping("/class-delete")
	public String deleteClas(Integer cid) {
		clasService.delete(cid);
		return "redirect:class-query?page=0";
	}
	@ApiOperation(value="打开修改教室信息界面",notes="")
	@ApiImplicitParams(value={
			@ApiImplicitParam(name="cid",value="教室编号",required = true,dataType="String"),
			@ApiImplicitParam(name="model",value="向HTML传递的消息",required = true,dataType="Model")
	})
	@GetMapping("/class-modify")
	public String modifyClas(Integer cid,Model model) {
		Clas clas = clasService.findOne(cid);
		model.addAttribute("clas", clas);
		return "campus/class/class_modify";
	}
}
