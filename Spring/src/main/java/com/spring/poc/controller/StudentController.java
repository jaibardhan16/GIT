package com.spring.poc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.poc.component.StudentValidator;
import com.spring.poc.model.Student;

@Controller
public class StudentController {

	@Autowired
	private StudentValidator studentValidator;

	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		binder.addValidators(studentValidator);
	}

	// -----------------------------------------------------------------------------------------------------------
	// ModelAndView
	// -----------------------------------------------------------------------------------------------------------

	

	@RequestMapping(value = "/studentForm", method = RequestMethod.GET)
	public ModelAndView getStudentForm() {
		ModelAndView model = new ModelAndView("studentForm");
		return model;
	}

	@RequestMapping(value = "/studentStr", method = RequestMethod.GET)
	public String getStudentForm(String name) {
		return "studentForm";
	}

	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public String getStudentForm(String name, Model model) {
		model.addAttribute("studentName", "JaiBardhan");
		model.addAttribute("studentHobby", "ABC");
		return "studentSuccess";

	}
	// -----------------------------------------------------------------------------------------------------------
	// @RequestParam
	// -----------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/admissionForm1", method = RequestMethod.GET)
	public ModelAndView getAdmissionForm1() {
		ModelAndView model = new ModelAndView("studentForm1");
		return model;
	}

	@RequestMapping(value = "/submitAdmissionForm1", method = RequestMethod.POST)
	public ModelAndView submitAdmissionForm1(@RequestParam("studentName") String studentName,
			@RequestParam("studentHobby") String studentHobby) {
		ModelAndView model = new ModelAndView("studentSuccess");
		model.addObject("headerMessage", "Sucessfully submitted form using request Parameters.. ");
		model.addObject("studentName", studentName);
		model.addObject("studentHobby", studentHobby);
		return model;
	}

	@RequestMapping(value = "/admissionForm2", method = RequestMethod.GET)
	public ModelAndView getAdmissionForm2() {
		ModelAndView model = new ModelAndView("studentForm2");
		return model;
	}

	@RequestMapping(value = "/submitAdmissionForm2", method = RequestMethod.POST)
	public ModelAndView submitAdmissionForm2(Map<String, String> paramMap) {
		ModelAndView model = new ModelAndView("studentSuccess");
		model.addObject("headerMessage", "Sucessfully submitted form using request Param Map.. ");
		model.addObject("studentName", paramMap.get("studentName"));
		model.addObject("studentHobby", paramMap.get("studentHobby"));
		return model;
	}

	@RequestMapping(value = "/admissionForm3", method = RequestMethod.GET)
	public ModelAndView getAdmissionForm3() {
		ModelAndView model = new ModelAndView("studentForm3");
		return model;
	}

	@RequestMapping(value = "/submitAdmissionForm3", method = RequestMethod.POST)
	public ModelAndView submitAdmissionForm3(@ModelAttribute("student1") @Valid Student student1,BindingResult result) {
		
		if(result.hasErrors()) {
	    	return new ModelAndView("studentForm3");
	    }
		ModelAndView model = new ModelAndView("AdmissionSuccess");
		model.addObject("headerMessage", "Sucessfully submitted form using Model Attribute.. ");
		return model;
	}

	// -----------------------------------------------------------------------------------------------------------
	// @PathVariable
	// -----------------------------------------------------------------------------------------------------------

	@RequestMapping("profiles/{userName}")
	public String handleRequest1(@PathVariable String userName, Model model) {
		model.addAttribute("msg", "user profile name using @PathVariable: " + userName);
		return "page";
	}

	@RequestMapping("{id}/posts/{postId}")
	public String handleRequest2(@PathVariable("id") String userId, @PathVariable("postId") String postId,
			Model model) {
		model.addAttribute("msg", "User profile using @PathVariables  user id : " + userId + ", post id: " + postId);
		return "page";
	}

	@RequestMapping("{id}/messages/{msgId}")
	public String handleRequest3(@PathVariable Map<String, String> varsMap, Model model) {
		model.addAttribute("msg", "User profile using @PathVariable Map " + varsMap.toString());
		return "page";
	}

	// -----------------------------------------------------------------------------------------------------------

	@ExceptionHandler(Exception.class)
	public ModelAndView handleGenericException(HttpServletRequest request, Exception ex) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exception", ex);
		modelAndView.addObject("url", request.getRequestURL());
		modelAndView.setViewName("error");
		return modelAndView;
	}

}
