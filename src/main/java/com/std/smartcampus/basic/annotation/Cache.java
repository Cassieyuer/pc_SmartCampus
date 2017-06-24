/**   
 * Copyright © 2017 公司名. All rights reserved.
 */
package com.std.smartcampus.basic.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * 缓存注解
 * @author: 李志鹏
 * @date: 2017年4月15日 下午3:09:55 
 */
@Retention(RUNTIME)
@Target(TYPE)
@Documented
public @interface Cache {
	//级联修改的缓存
	Class<?>[] modifyClass() default {};
}
