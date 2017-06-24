/**   
 * Copyright © 2017 公司名. All rights reserved.
 */
package com.std.smartcampus.common.util;

import java.util.Base64;

import org.springframework.util.Base64Utils;




/** 
 * @author: 李志鹏
 * @date: 2017年5月5日 上午10:12:00 
 */
public class Base64Util {
	
	public static String jdk8Decode(String src) {
		return new String(Base64.getDecoder().decode(src));
	}
	public static String jdk8Encode(String src) {
		return new String(Base64.getEncoder().encode(src.getBytes()));
	}
	public static String springDecode(String src) {
		return new String(Base64Utils.decode(src.getBytes()));
	}
	public static String springEncode(String src) {
		return new String(Base64Utils.encode(src.getBytes()));
	}
	
}
