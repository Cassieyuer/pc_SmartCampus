package com.std.smartcampus.common.handle;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mongodb.MongoTimeoutException;
import com.std.smartcampus.common.exception.ErrorInfo;
import com.std.smartcampus.common.exception.ServiceException;

/**
 * 
 */
@ControllerAdvice(annotations = Controller.class)
public class CommonExceptionAdvice {

	public CommonExceptionAdvice() {

		map = new HashMap<>();
		/**
		 * 400
		 */
		map.put(MissingServletRequestParameterException.class, "请求参数缺失");
		map.put(HttpMessageNotReadableException.class, "could_not_read_json");
		map.put(ValidationException.class, "ValidationException");

		map.put(HttpRequestMethodNotSupportedException.class, "HttpRequestMethodNotSupportedException");
		map.put(HttpMediaTypeNotSupportedException.class, "HttpMediaTypeNotSupportedException");
		/**
		 * 500
		 */
		map.put(DataIntegrityViolationException.class, "操作数据库出现异常：字段重复、有外键关联等,请检查输入的数据是否与其它数据冲突");
		map.put(ServiceException.class, "发生系统异常，无法继续进行！");
		map.put(Exception.class, "发生异常，无法继续进行！");
		map.put(MongoTimeoutException.class, "Mongodb数据库连接超时,请确认Mongodb数据库服务已打开");
	}

	private Map<Class<? extends Exception>, String> map;

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ MissingServletRequestParameterException.class, HttpMessageNotReadableException.class,
			ValidationException.class })
	public String systemExceptionHandler(Model model, HttpServletRequest req, Exception e) {
		ErrorInfo<String> er = new ErrorInfo<>();
		er.setCode(ErrorInfo.ERROR);
		er.setMessage(e.getMessage());
		er.setUrl(req.getRequestURL().toString());
		er.setParams(req.getQueryString());
		System.out.println(e.getClass());
		er.setData(map.get(e.getClass()));
		model.addAttribute("errorInfo", er);
		return "errors/default";
	}

	@ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
	@ExceptionHandler({MongoTimeoutException.class})
	public String MongoTimeoutException(Model model, HttpServletRequest req, MongoTimeoutException e) {
		ErrorInfo<String> er = new ErrorInfo<>();
		er.setCode(ErrorInfo.ERROR);
		er.setMessage(e.getMessage());
		er.setUrl(req.getRequestURL().toString());
		er.setParams(req.getQueryString());
		System.out.println(e.getClass());
		er.setData(map.get(e.getClass()));
		model.addAttribute("errorInfo", er);
		return "errors/default";
	}

	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler({ DataIntegrityViolationException.class, ServiceException.class})
	public String defaultExceptionHandler(Model model, HttpServletRequest req, Exception e) {
		ErrorInfo<String> er = new ErrorInfo<>();
		er.setCode(ErrorInfo.ERROR);
		er.setMessage(e.getMessage());
		er.setUrl(req.getRequestURL().toString());
		er.setParams(req.getQueryString());
		System.out.println(e.getClass());
		er.setData(map.get(e.getClass()));
		model.addAttribute("errorInfo", er);
		return "errors/default";
	}

	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public String handleMethodArgumentNotValidException(Model model, HttpServletRequest req,
			MethodArgumentNotValidException e) {
		BindingResult result = e.getBindingResult();
		FieldError error = result.getFieldError();
		String field = error.getField();
		String code = error.getDefaultMessage();
		String message = String.format("%s:%s", field, code);
		ErrorInfo<String> er = new ErrorInfo<>();
		er.setCode(ErrorInfo.ERROR);
		er.setMessage(e.getMessage());
		er.setUrl(req.getRequestURL().toString());
		er.setParams(req.getQueryString());
		er.setData(message);
		model.addAttribute("errorInfo", er);
		return "errors/default";
	}

	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public String handleBindException(Model model, HttpServletRequest req, BindException e) {
		BindingResult result = e.getBindingResult();
		FieldError error = result.getFieldError();
		String field = error.getField();
		String code = error.getDefaultMessage();
		String message = String.format("%s:%s", field, code);
		ErrorInfo<String> er = new ErrorInfo<>();
		er.setCode(ErrorInfo.ERROR);
		er.setMessage(e.getMessage());
		er.setUrl(req.getRequestURL().toString());
		er.setParams(req.getQueryString());
		er.setData(message);
		model.addAttribute("errorInfo", er);
		return "errors/default";
	}

	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public String handleServiceException(Model model, HttpServletRequest req, ConstraintViolationException e) {
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		ConstraintViolation<?> violation = violations.iterator().next();
		String message = violation.getMessage();
		ErrorInfo<String> er = new ErrorInfo<>();
		er.setCode(ErrorInfo.ERROR);
		er.setMessage(e.getMessage());
		er.setUrl(req.getRequestURL().toString());
		er.setParams(req.getQueryString());
		er.setData(message);
		model.addAttribute("errorInfo", er);
		return "errors/default";
	}

	/**
	 * 405 - Method Not Allowed
	 */
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public String handleHttpRequestMethodNotSupportedException(Model model, HttpServletRequest req,
			HttpRequestMethodNotSupportedException e) {
		ErrorInfo<String> er = new ErrorInfo<>();
		er.setCode(ErrorInfo.ERROR);
		er.setMessage(e.getMessage());
		er.setUrl(req.getRequestURL().toString());
		er.setParams(req.getQueryString());
		er.setData("request_method_not_supported");
		model.addAttribute("errorInfo", er);
		return "errors/default";
	}

	/**
	 * 4-10005 - Unsupported Media Type
	 */
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public String handleHttpMediaTypeNotSupportedException(Model model, HttpServletRequest req, Exception e) {

		ErrorInfo<String> er = new ErrorInfo<>();
		er.setCode(ErrorInfo.ERROR);
		er.setMessage(e.getMessage());
		er.setUrl(req.getRequestURL().toString());
		er.setParams(req.getQueryString());
		er.setData("content_type_not_supported");
		model.addAttribute("errorInfo", er);
		return "errors/default";
	}

	
}