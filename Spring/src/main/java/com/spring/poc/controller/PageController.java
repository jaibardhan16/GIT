package com.spring.poc.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getIndex() {
		return new ModelAndView("boot");
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminPage() {
		return new ModelAndView("admin", "msg", "Congratulation ");
	}

	@RequestMapping(value = "/dba", method = RequestMethod.GET)
	public ModelAndView dbaPage() {
		return new ModelAndView("dba", "msg", "Congratulation ");
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accessDeniedPage() {
		return new ModelAndView("403", "msg", "You are not authorize. ");
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/loginError", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		model.addAttribute("error", "true");
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logoutPage() {
		return new ModelAndView("login");
	}
	
	@Secured({"ROLE_ADMIN","ROLE_DBA"})
	@RequestMapping(value = "/secure", method = RequestMethod.GET)
	public ModelAndView securePage() {
		return new ModelAndView("login");
	}
}
