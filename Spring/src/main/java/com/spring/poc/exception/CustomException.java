package com.spring.poc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED, reason = "Custom business exception") // 404
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = -3332292346834265371L;

	public CustomException(int id) {
		super("EmployeeNotFoundException with id=" + id);
	}
}