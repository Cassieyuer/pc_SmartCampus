/**   
 * Copyright © 2017 公司名. All rights reserved.
 */
package com.std.smartcampus.baidumap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.std.smartcampus.baidumap.service.BaiduService;

/** 
 * @author: 李志鹏
 * @date: 2017年6月19日 下午3:03:32 
 */
@Controller
@RequestMapping("/baidumap")
public class BaiduController {

	@Autowired
	private BaiduService baiduService;
	
	@PostMapping("/location")
	@ResponseBody
	public String getLocationFromAddress(String address){
		return baiduService.getLocationFromAddress(address);
	}
	@GetMapping({"/index","/",""})
	public String index(){
		return "baidumap/index";
	}
}
