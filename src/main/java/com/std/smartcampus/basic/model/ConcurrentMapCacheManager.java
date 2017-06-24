/**   
 * Copyright © 2017 公司名. All rights reserved.
 */
package com.std.smartcampus.basic.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

import com.std.smartcampus.Application;
import com.std.smartcampus.common.util.CacheUtil;

/** 
 * @author: 李志鹏
 * @date: 2017年4月14日 下午1:29:48 
 */
public class ConcurrentMapCacheManager{

	private final String CACHE_PACKAGE_PATH = Application.class.getPackage().getName() + ".*." + "service";

	private ConcurrentHashMap<String, Object> data = new ConcurrentHashMap<>();
	private Map<String, List<String>> cascade;

	private ConcurrentMapCacheManager() {
		cascade = CacheUtil.create(CACHE_PACKAGE_PATH);//初始化级联缓存信息
	}
	/****************单例模式****************/
	private static class Holder{
		private static ConcurrentMapCacheManager instance  = new ConcurrentMapCacheManager();
	}
	public static ConcurrentMapCacheManager getInstance() {
		return Holder.instance;
	}
	/****************单例模式****************/
	/** 
	 * 
	 */
	public Object get(String key) {
		return data.get(key);
	}
	/** 
	 * 
	 */
	public void put(String key, Object value) {
		if (value != null && key != null) {
			data.put(key, value);
		}
	}
	/** 
	 * 
	 */
	public void forEach(BiConsumer<String, Object> action) {
		data.forEach(action);
	}
	/** 
	 * 连带删除相关联的缓存
	 */
	public void remove(String key, Object value, String clazz) {
		data.remove(key, value);
		data.forEach((k,v) -> cascade.getOrDefault(clazz,new ArrayList<>()).forEach(c->{
            if (k.startsWith(c)) {
                data.remove(k,v);
            }
        }));
	}
	/** 
	 * 清空缓存
	 */
	public void clear() {
		data.clear();
	}
}
