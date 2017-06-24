package com.std.smartcampus.basic.controller.mobile;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.std.smartcampus.basic.domain.User;
import com.std.smartcampus.basic.service.UserService;
import com.std.smartcampus.common.result.Result;
import com.std.smartcampus.common.util.ResultUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
public class MUserController {
	
	@Autowired
	UserService userService;//管理员业务
	
	@ApiOperation(value="管理员登陆",notes="")
	@ApiImplicitParam(name="user",value="管理员信息",required = true,dataType="User")
	@PostMapping("/login")
	public Result<User> login(@Valid User user){
		User canLogin = userService.findByNameAndPassword(user.getUsername(), user.getPassword());
		if (canLogin==null) {
			return ResultUtil.error(-1, "用户名或密码错误");
		}
		return  ResultUtil.success(canLogin);
	}
	

	@ApiOperation(value="保存管理员",notes="")
	@ApiImplicitParam(name="user",value="管理员信息",required = true,dataType="User")
	@GetMapping("/save")
	public Result<User> save(  User user){
		userService.save(user);
		return ResultUtil.success(user);
	}

	
	@ApiOperation(value="查询所有管理员",notes="")
	@GetMapping("/query")
	public Result<List<User>> query(){
		List<User> list = userService.findAll();
		return ResultUtil.success(list);
	}

	@ApiOperation(value="删除一条管理员信息",notes="")
	@ApiImplicitParam(name="sid",value="管理员编号",required = true,dataType="String")
	@GetMapping("/delete")
	public Result<User> delete(String id){
		User user = userService.findOne(Integer.valueOf(id));
		userService.delete(Integer.valueOf(id));
		return ResultUtil.success(user);
	}
	

	@ApiOperation(value="管理员修改密码",notes="")
	@ApiImplicitParams(value={
			@ApiImplicitParam(name="username",value="用户名",required = true,dataType="String"),
			@ApiImplicitParam(name="password",value="密码",required = true,dataType="String")
	})
	@GetMapping("/update/{username}&{password}")
	public Result<User> updateUserName(@PathVariable String username,@PathVariable String password){
		userService.updateUserPassword(username, password);
		return ResultUtil.success();
	}

}
