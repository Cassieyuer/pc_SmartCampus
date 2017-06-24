/**   
 * Copyright © 2017 公司名. All rights reserved.
 */
package com.std.smartcampus.baidumap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.std.smartcampus.baidumap.controller.BaiduController;

/** 
 * @author: 李志鹏
 * @date: 2017年6月19日 下午3:25:14 
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BaiduControllerTest {
	
	@Autowired
	private BaiduController baiduController;
	
	@Test
	public void getLocationFromAddress(){
		String location = baiduController.getLocationFromAddress("河北省邯郸市曲周县");
		System.out.println(location);
	}

}
