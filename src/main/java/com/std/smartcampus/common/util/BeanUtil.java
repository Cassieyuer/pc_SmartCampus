package com.std.smartcampus.common.util;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;

import com.std.smartcampus.Application;


/**
 * 通用工具类
 * @ClassName: Utils 
 * @author: Administrator
 * @date: 2017年3月26日 上午1:57:15
 */
public class BeanUtil {

	/**
	 * 生成随机数组
	 * @Title: getRandomArray 
	 * @param lon 随机数组长度
	 * @return 长度为lon的数组
	 * @return: int[]
	 *//*
	public static int[] getRandomArray(int lon) {
		int[] result = new int[lon];
		for (int i = 0; i < lon; i++) {
			result[i] = i;
		}
		for (int i = 0; i < lon; i++) {
			int random = (int) (lon * Math.random());
			int temp = result[i];
			result[i] = result[random];
			result[random] = temp;
		}
		return result;
	}
*/

	/**
	 * 简易IOC实现,通过反射使对象中各字段不为空
	 * @Title: getBean 
	 * @param o 传入的对象,部分属性可能为null
	 * @param clazz 传入的类的对象
	 * @param injectChild 是否注入非基本类型
	 * @return o 经过处理的的对象,属性不为null
	 * @return: Object
	 */
	private static Object getBean(Object o,Class<?> clazz, boolean injectChild){
		try {
			if (o==null) {
				o=clazz.newInstance();
			}
			Field[] fields = clazz.getDeclaredFields();//获取所有属性
			for (Field field : fields) {
				field.setAccessible(true); // 设置些属性是可以访问的
				if (ParameterizedType.class.isAssignableFrom(field.getGenericType().getClass())//不注入含有泛型的字段
						|| Modifier.isFinal(field.getModifiers()) //不注入final
						|| Modifier.isStatic(field.getModifiers())//不注入static
						|| Modifier.isInterface(field.getModifiers())//不注入接口
						|| Modifier.isAbstract(field.getModifiers())//不注入抽象类
						|| field.getType().isPrimitive() //不注入基本类型
						|| field.getType().isEnum() //不注入枚举
						|| field.getType().isArray() ) {//不注入数组
					continue;
				}
				if (injectChild && field.getType().getPackage().getName().contains(Application.class.getPackage().getName())){
					field.set(o, getBean(field.get(o),field.getType(), true));//递归注入
				} else if (field.getType().toString().endsWith("Integer")
						||field.getType().toString().endsWith("int")) {
					field.set(o, 0);
				} else if (field.getType().toString().endsWith("String")) {
					field.set(o, "");
				}  else {
					field.set(o, field.getType().newInstance());
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return o;
	}
	public static Object getBean(Class<?> clazz, boolean injectChild){
		Object obj = null;
		try {
			obj = getBean(clazz.newInstance(), clazz, injectChild);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static Object getBean(Object o, boolean injectChild){
		return getBean(o, o.getClass(), injectChild);
	}

	public static Object getBean(Object o){
		return getBean(o,false);//默认不注入非基本类型
	}
	public static Object getBean(Class<?> clazz){
		return getBean(clazz,false);//默认不注入非基本类型
	}
	
	
}
