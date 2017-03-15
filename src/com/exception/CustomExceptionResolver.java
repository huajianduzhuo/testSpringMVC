package com.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理器，实现HandlerExceptionResolver接口
 * @author yujin
 *
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		//handler就是处理器适配器要执行handler对象（只有method方法）
		ex.printStackTrace();
		String message = null;
		if(ex instanceof CustomException){
			message = ex.getMessage();
		}else{
			message = "未知错误";
		}
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("message", message);
		return mav;
	}

}
