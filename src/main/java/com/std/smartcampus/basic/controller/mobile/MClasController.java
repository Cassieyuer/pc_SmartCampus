package com.std.smartcampus.basic.controller.mobile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.std.smartcampus.basic.domain.Clas;
import com.std.smartcampus.basic.service.ClasService;
import com.std.smartcampus.common.result.Result;
import com.std.smartcampus.common.util.ResultUtil;

import io.swagger.annotations.ApiOperation;
/**
 * 
 * @ClassName: MobileClasController 
 * @Description: TODO
 * @author: Administrator
 * @date: 2017年3月26日 上午1:50:12
 */
@RestController
@RequestMapping("/class")
public class MClasController {
	
	@Autowired
	ClasService clasService;//班级业务
	
//	@ApiOperation(value="保存一个班级",notes="")
//	@ApiImplicitParam(name="clas", value="班级信息", required=true, dataType="Clas")
//	@GetMapping("/save")
//	public Result<Clas> save(  Clas clas){
//		clasService.save(clas);
//		return ResultUtil.success(clas);
//	}

	@ApiOperation(value="查询所有班级",notes="")
	@GetMapping("/query")
	public Result<List<Clas>> query(){
		List<Clas> list = clasService.findAll();
		return ResultUtil.success(list);
	}

	
//	@ApiOperation(value="删除班级",notes="")
//	@ApiImplicitParam(name="cid",value="班级编号",required = true,dataType="String")
//	@GetMapping("/delete")
//	public Result<Clas> delete( String cid){
//		Clas clas = clasService.findById(Integer.valueOf(cid));
//		clasService.delete(Integer.valueOf(cid));
//		return ResultUtil.success(clas);
//	}

}
