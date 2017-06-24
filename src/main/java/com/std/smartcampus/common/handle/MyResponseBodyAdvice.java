package com.std.smartcampus.common.handle;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.std.smartcampus.common.result.Result;

/**
 * 处理 返回值
 * 
 * @Description:TODO
 * @author lh
 * @time:2015-8-31 上午11:24:08
 */
@ControllerAdvice(annotations={ResponseBody.class, RestController.class})
public class MyResponseBodyAdvice implements ResponseBodyAdvice<Result<?>> {
	
	@Override
	public Result<?> beforeBodyWrite(Result<?> returnValue, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
					ServerHttpResponse response) {
		// 统一修改返回值/响应体
		String msg = returnValue.getMsg();
		System.out.println(msg);
		// 返回修改后的值
		return returnValue;
	}

//	public Result<?> beforeBodyWrite(Result<?> returnValue, MethodParameter methodParameter, MediaType mediaType, Class clas,
//			ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
//		// 通过 ServerHttpRequest的实现类ServletServerHttpRequest 获得HttpServletRequest
//		// ServletServerHttpRequest sshr=(ServletServerHttpRequest)
//		// serverHttpRequest;
//		// 此处获取到request 是为了取到在拦截器里面设置的一个对象 是我项目需要 大家可以忽略
//		// HttpServletRequest request= sshr.getServletRequest();
//		// 将返回值returnValue转成我需要的类型Message<?>方便统一修改其中的某个属性
//		Result<?> result = (Result<?>) returnValue;
//		// 统一修改返回值/响应体
//		String msg = result.getMsg();
//		result.setMsg("测试修改返回值:"+msg);
//		// 返回修改后的值
//		return result;
//	}

	@Override
	public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
		// 获取当前处理请求的controller的方法
		String methodName = methodParameter.getMethod().getName();
		// 不拦截/不需要处理返回值 的方法
		String method = "login"; // 如登录
		// 不拦截
		if (method.equals(methodName)) {
			return false;
		}
		return true;
	}



}