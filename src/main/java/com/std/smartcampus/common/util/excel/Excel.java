/**   
 * Copyright © 2017 公司名. All rights reserved.
 */
package com.std.smartcampus.common.util.excel;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/** 
 * @author: 李志鹏
 * @date: 2017年5月8日 下午9:19:05 
 */
@Retention(RUNTIME)
@Target({FIELD,TYPE})
@Documented
public @interface Excel {
	String column() default "";//列名
	String sheet() default "";//sheet名
	boolean write() default true;//是否写入Excel文件      
}
