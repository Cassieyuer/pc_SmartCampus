/**   
 * Copyright © 2017 公司名. All rights reserved.
 */
package com.std.smartcampus.basic.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Base64加密
 * @author: 李志鹏
 * @date: 2017年5月5日 上午11:45:39 
 */
@Retention(RUNTIME)
@Target(METHOD)
@Documented
public @interface Base64 {

}
