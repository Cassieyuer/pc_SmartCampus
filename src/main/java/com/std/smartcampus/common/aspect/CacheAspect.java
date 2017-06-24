/**   
 * Copyright © 2017 公司名. All rights reserved.
 */
package com.std.smartcampus.common.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.std.smartcampus.basic.model.ConcurrentMapCacheManager;

/** 
 * @author: 李志鹏
 * @date: 2017年4月13日 下午8:14:14 
 */
@Aspect
@Order(5)
@Component
public class CacheAspect {
	
	//只读保存缓存
	private static final String read_only_cache =	
			"execution(public * com.std.smartcampus.*.service..*.find*(..)) or "
		+ 	"execution(public * com.std.smartcampus.*.service..*.get*(..)) or "
		+ 	"execution(public * com.std.smartcampus.*.service..*.count(..)) or "
		+ 	"execution(public * com.std.smartcampus.*.service..*.exists(..))";
	//更新删除相关缓存
	private static final String modify_cache = 
			"execution(public * com.std.smartcampus.*.service..*.save*(..)) or "
		+ 	"execution(public * com.std.smartcampus.*.service..*.update*(..)) or "
		+ 	"execution(public * com.std.smartcampus.*.service..*.delete*(..)) or "
		+ 	"execution(public * com.std.smartcampus.*.service..*.flush*(..))";
	
	private static final ConcurrentMapCacheManager cache_msg = ConcurrentMapCacheManager.getInstance();
	
	@Around(read_only_cache)
	public Object readOnlyAround(ProceedingJoinPoint joinPoint) throws Throwable {
		String key = joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature().getName()+"("+Arrays.asList(joinPoint.getArgs())+")";
		Object value = null;
		if (null == cache_msg.get(key)) {
			value = joinPoint.proceed();
			cache_msg.put(key, value);//保存到缓存
		} else {
			value = cache_msg.get(key);
		} 
		return value;	
	}
	@Around(modify_cache)
	public Object modifyAround(ProceedingJoinPoint joinPoint) throws Throwable {
		String clazz = joinPoint.getTarget().getClass().getName();
		cache_msg.forEach((k,v)->{
			if (k.startsWith(clazz)) {
				cache_msg.remove(k, v, clazz);//删除缓存
			}
		});
		return joinPoint.proceed();	
	}
}
