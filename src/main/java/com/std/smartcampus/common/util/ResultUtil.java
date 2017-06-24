package com.std.smartcampus.common.util;

import com.std.smartcampus.common.result.Result;

/**
 * Created by 廖师兄
 * 2017-01-21 13:39
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ResultUtil {

    
	public static <T> Result<T> success(T object) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static <T> Result<T> success() {
        return success((T)null);
    }



	/** 
	 * @Title: error 
	 * @param code
	 * @param message
	 * @return: Result<?>
	 */
	public static<T> Result<T> error(Integer code, String message, T data) {
		Result result = new Result();
        result.setCode(code);
        result.setMsg(message);
        result.setData(data);
        return result;
	}
	public static<T> Result<T> error(Integer code, String message) {
		return error(code, message, null);
	}
	
	public static void main(String[] args) {
		System.out.println(((Double)(double)1).getClass());
	}
}
