package com.spring.poc.advice;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.spring.poc.exception.CustomException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Unexpected error occured")
	public ModelAndView handleException(HttpServletRequest request, Exception ex) {
		String stackTrace = getStackTrace(ex);
		ModelAndView model = new ModelAndView("genric-error");
		model.addObject("msg", "Error captured");
		model.addObject("message", ex.getMessage());
		model.addObject("url", request.getRequestURL());
		model.addObject("stackTrace", stackTrace);
		return model;
	}
	
	@ExceptionHandler(CustomException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Business Error occured")
	public ModelAndView handleCustomException(HttpServletRequest request, Exception ex) {
		String stackTrace = getStackTrace(ex);
		ModelAndView model = new ModelAndView("error");
		model.addObject("msg", "Error captured");
		model.addObject("message", ex.getMessage());
		model.addObject("url", request.getRequestURL());
		model.addObject("stackTrace", stackTrace);
		return model;
	}

	private String getStackTrace(Exception ex) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		ex.printStackTrace(printWriter);
		String stackTrace = stringWriter.toString();
		return stackTrace;
	}
}
