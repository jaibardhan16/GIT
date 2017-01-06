package com.spring.poc.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.poc.model.Contact;
import com.spring.poc.service.ContactService;

@Controller
public class RestController {
	private Logger LOGGER = Logger.getLogger(this.getClass());
	@Autowired
	private ContactService contactService;

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<Contact>> findAll() {
		List<Contact> contacts = contactService.findAll();
		LOGGER.debug("Fetching contacts from DB...." + contacts.size());
		if (contacts.isEmpty()) {
			return new ResponseEntity<List<Contact>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Contact>>(HttpStatus.OK);
	}

}
