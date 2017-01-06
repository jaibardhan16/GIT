package com.spring.poc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.poc.model.Contact;
import com.spring.poc.service.ContactService;

@Controller
public class ContactController {

	@Autowired
	private ContactService contactService;

	@RequestMapping(value = "/api/contacts/", method = RequestMethod.GET)
	@ResponseBody
	public List<Contact> findAll() {
		return contactService.findAll();
	}
	
	
	
	@RequestMapping(value = "/home")
	public ModelAndView listContact(ModelAndView model) throws IOException {
		List<Contact> listContact = contactService.getList();
		model.addObject("listContact", listContact);
		model.setViewName("home");
		return model;
	}

	@RequestMapping(value = "/newContact", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		Contact newContact = new Contact();
		model.addObject("contact", newContact);
		model.setViewName("ContactForm");
		return model;
	}

	@RequestMapping(value = "/saveContact", method = RequestMethod.POST)
	public ModelAndView saveContact(@ModelAttribute Contact contact) {
		contactService.insert(contact);
		return new ModelAndView("redirect:/home");
	}

	@RequestMapping(value = "/deleteContact", method = RequestMethod.PUT)
	public ModelAndView deleteContact(HttpServletRequest request) {
		int contactId = Integer.parseInt(request.getParameter("id"));
		contactService.delete(contactId);
		return new ModelAndView("redirect:/home");
	}

	@RequestMapping(value = "/editContact", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		int contactId = Integer.parseInt(request.getParameter("id"));
		Contact contact = contactService.findById(contactId);
		ModelAndView model = new ModelAndView("ContactForm");
		model.addObject("contact", contact);
		return model;
	}
}
