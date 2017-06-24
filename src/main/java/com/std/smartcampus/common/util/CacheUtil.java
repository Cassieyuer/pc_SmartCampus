/**
 * Copyright © 2017 公司名. All rights reserved.
 */
package com.std.smartcampus.common.util;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.std.smartcampus.basic.annotation.Cache;

/**
 * @author: 李志鹏
 * @date: 2017年4月15日 下午3:14:09
 */
public class CacheUtil {
	
	/**
	 * 反射获取相关联需要修改的缓存
	 */
	public static Map<String, List<String>> create(String packageName){
		Map<String, List<String>> result = new LinkedHashMap<>();
		List<Class<?>> classList = findClass(packageName);
		classList.forEach(clazz -> {
			if (clazz.getAnnotation(Cache.class) != null) {
				Cache cache = clazz.getAnnotation(Cache.class);
				Class<?>[] modifys = cache.modifyClass();
				List<String> list = Stream.of(modifys).map(c->c.getName()).collect(toList());
				result.put(clazz.getName(), list);
			}
		});
		return result;
	}

	/**
	 * 从类名称拿到Class对象
	 * @param packageName
	 * @return
	 */
	private static List<Class<?>> findClass(String packageName) {
		List<Class<?>> classList = ClassUtil.findPackageClass(packageName).parallelStream().map(clazzName -> {
			Class<?> clazz=null;
			try {
				clazz=Class.forName(clazzName);
			} catch (ClassNotFoundException e) {
			}
			return clazz;
		}).collect(toList());
		return classList;
	}
	
}
