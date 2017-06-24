package com.std.smartcampus.common.handle;

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

/**
 * 处理 请求值
 * 
 * @Description:TODO
 * @author lh
 * @time:2015-8-31 上午11:24:08
 */
@ControllerAdvice
public class MyRequestBodyAdvice implements RequestBodyAdvice {

	/*  
	 * 
	 */
	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		// TODO 自动生成的方法存根
		return true;
	}

	/*  
	 * 
	 */
	@Override
	public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
			Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		// TODO 自动生成的方法存根
		return body;
	}

	/*  
	 * 
	 */
	@Override
	public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
		// TODO 自动生成的方法存根
		return inputMessage;
	}

	/*  
	 * 
	 */
	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		// TODO 自动生成的方法存根
		return body;
	}
	
	


}