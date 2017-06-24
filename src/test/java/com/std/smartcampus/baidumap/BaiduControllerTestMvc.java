/**   
 * Copyright © 2017 公司名. All rights reserved.
 */
package com.std.smartcampus.baidumap;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/** 
 * @author: 李志鹏
 * @date: 2017年6月19日 下午3:25:14 
 */
@WebMvcTest
@RunWith(SpringRunner.class)
public class BaiduControllerTestMvc {
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void getLocationFromAddress() throws Exception {
		String address = "河北省邯郸市曲周县";   
        String uri = "/baidumap/location?address=" + address;  
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON))  
                .andReturn();  
        int status = mvcResult.getResponse().getStatus();  
        String content = mvcResult.getResponse().getContentAsString();  
  
        assertEquals(200, status); 
	}

}
