package com.std.smartcampus.basic.interceptor;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证管理员是否登陆拦截器
 *
 * @author   李志鹏
 */

public class LoginInterceptor implements HandlerInterceptor {

    //在请求处理之前进行调用（Controller方法调用之前）
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//    	System.out.println("在请求处理之前进行调用（Controller方法调用之前）");
    	return true;
    }

    //请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//    	System.out.println("在请求处理之前进行调用（请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    	String name = (String) httpServletRequest.getSession().getAttribute("username");
        if (name == null) {
            modelAndView.setViewName("user/login");//拦截器处理。如果未登录不能访问页面
        }
    }

    //在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//    	System.out.println("在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }
}