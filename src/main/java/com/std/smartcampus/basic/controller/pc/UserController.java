package com.std.smartcampus.basic.controller.pc;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.std.smartcampus.basic.domain.User;
import com.std.smartcampus.basic.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
/**
 * 
 * @ClassName: PCAdminController 
 * @Description: TODO
 * @author: Administrator
 * @date: 2017年3月26日 上午1:46:56
 */
@Controller
@RequestMapping("/admin")
public class UserController {

	@Autowired
	private UserService userService;
	
	@ApiOperation(value="管理员登陆界面",notes="")
	@GetMapping({"/",""})
	public String welcome(HttpSession session ) {
		session.removeAttribute("username");
		return "user/login";
	}
	
	@ApiOperation(value="退出管理员登陆",notes="")
	@GetMapping("/logout")
	public String logout() {
		return "user/login";
	}
	
	@ApiOperation(value="管理员登陆",notes="")
	@ApiImplicitParams(value={
			@ApiImplicitParam(name="admin",value="管理员信息",required = true,dataType="Admin"),
			@ApiImplicitParam(name="model",value="向HTML传递的消息",required = true,dataType="Model")
	})
	@PostMapping("/login")
	public String login(@Valid User user, HttpSession session, Model model) {
		User admin = userService.findByNameAndPassword(user.getUsername(), user.getPassword());
		if (admin == null) {
			model.addAttribute("error", "登陆失败,账号或密码错误!!!");
			return "user/login";
		}else {
			session.setAttribute("username", admin.getUsername());
			return "user/login_success";
		}
	}
	
	@ApiOperation(value="管理员注册界面",notes="")
	@GetMapping("/user-add")
	public String addUser() {
		return "user/user_add";
	}
	
	@ApiOperation(value="保存一个管理员",notes="")
	@ApiImplicitParam(name="admin",value="管理员信息",required = true,dataType="Admin")
	@PostMapping("/user-save")
	public String saveUser(@Valid User user) {
		userService.save(user);
		return "redirect:/admin/";
	}
}
