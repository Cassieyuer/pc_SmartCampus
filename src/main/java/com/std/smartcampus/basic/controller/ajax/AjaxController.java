/**   
 * Copyright © 2017 公司名. All rights reserved.
 */
package com.std.smartcampus.basic.controller.ajax;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.std.smartcampus.basic.model.ConcurrentMapCacheManager;
import com.std.smartcampus.common.result.Result;
import com.std.smartcampus.common.util.ResultUtil;

/** 
 * @author: 李志鹏
 * @date: 2017年5月21日 下午11:09:07 
 */
@RestController
public class AjaxController {
	
	private final ConcurrentMapCacheManager cache_msg = ConcurrentMapCacheManager.getInstance();
	
	/**
	 * 清除缓存
	 */
	@PostMapping("/admin/clear_cache")
	public Result<String> clearCache() {
		cache_msg.clear();
		return ResultUtil.success("清除缓存成功");
	}
	
}
