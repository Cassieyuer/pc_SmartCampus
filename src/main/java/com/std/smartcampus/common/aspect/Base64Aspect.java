/**   
 * Copyright © 2017 公司名. All rights reserved.
 */
package com.std.smartcampus.common.aspect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.std.smartcampus.basic.annotation.Base64;
import com.std.smartcampus.common.util.Base64Util;

/** 
 * Base64加密信息
 * @author: 李志鹏
 * @date: 2017年5月5日 上午11:12:20 
 */
@Aspect
@Order(4)
@Component
public class Base64Aspect {

	private static final String base64_login = "execution(public * com.std.smartcampus.*.service..*.*Password(..))";
	private static final String base64_save = "execution(public * com.std.smartcampus.*.service..*.save*(..))";
	
	/**
	 * 对登陆时输入的密码Base64加密
	 */
	@Around(base64_login)
    public Object dobase64_login(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();  
		Method method = methodSignature.getMethod(); 
		Object[] args = joinPoint.getArgs();
		if (null != method.getDeclaredAnnotation(Base64.class)) {
			String[] parameterNames = methodSignature.getParameterNames();
			ArrayList<String> names = new ArrayList<>(Arrays.asList(parameterNames));
			int indexOf = names.indexOf("password");
			args[indexOf] = Base64Util.jdk8Encode((String)args[indexOf]);//加密数据
		}
        Object object = joinPoint.proceed(args);
        return object;
    }
	/**
	 * 对保存时实体的密码Base64加密
	 */
	@Around(base64_save)
	public Object dobase64_save(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();  
		Method method = methodSignature.getMethod(); 
		Object[] args = joinPoint.getArgs();
		if (null != method.getDeclaredAnnotation(Base64.class)) {
			base64EncodeParams(args);
		}
		Object object = joinPoint.proceed(args);
		return object;
	}
	/** 
	 * 遍历参数
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	private void base64EncodeParams(Object[] args)
			throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
		for (Object arg : args) {
			if (arg instanceof List || arg instanceof Set) {
				Method size = arg.getClass().getMethod("size");
				for (int i = 0, len = (int) size.invoke(arg); i < len; i++) {
					Method getEntity = arg.getClass().getMethod("get",int.class);
					Object entity = getEntity.invoke(arg, i);
					base64EncodeEntity(entity);
				}
			} else {
				base64EncodeEntity(arg);
			}
		}
	}
	/**
	 * 加密实体信息
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * 
	 */
	private void base64EncodeEntity(Object arg) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException  {
		try {
			Method getInstanceByIdCard = arg.getClass().getMethod("getInstanceByIdCard");
			getInstanceByIdCard.invoke(arg);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
		} finally {
			Field password = arg.getClass().getDeclaredField("password");
			password.setAccessible(true);
			password.set(arg, Base64Util.jdk8Encode((String) password.get(arg)));
		}
	}

	

}
