/**   
 * Copyright © 2017 公司名. All rights reserved.
 */
package com.std.smartcampus.common.util.excel;

import java.lang.reflect.Field;

/** 
 * @author: 李志鹏
 * @date: 2017年5月8日 下午8:12:49 
 */
public class Assert {
	/**
	 * 该字段是否写入Excel
	 */
	public static boolean isCanWriteExcel(Field field){
		Excel excel = field.getAnnotation(Excel.class);
		if (excel == null) {
			return false;
		}
		return excel.write();
	}
}
