package com.std.smartcampus.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Ajax示例,
 * @author Administrator
 *
 */

@Controller
public class IndexController {
	@GetMapping(value="/")
	public String home(){
		return "index";
	}
	@GetMapping(value="/test/{path}")
	public String test(@PathVariable String path){
		return path;
	}
}
