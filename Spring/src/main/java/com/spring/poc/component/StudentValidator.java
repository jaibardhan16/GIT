package com.spring.poc.component;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.spring.poc.model.Student;

@Component
public class StudentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Student.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Student student = (Student) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "studentName", "", "studentName is empty");
		if (student.getStudentName().length() < 2) {
			errors.rejectValue("studentName", "", "studentName length is less than 2");
		}
	}
}
