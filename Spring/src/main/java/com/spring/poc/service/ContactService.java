package com.spring.poc.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.internal.util.beans.BeanInfoHelper.ReturningBeanInfoDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import com.spring.poc.dao.ContactDAO;
import com.spring.poc.event.ContactEvent;
import com.spring.poc.model.Contact;

@Service("contactService")
public class ContactService implements ApplicationEventPublisherAware {

	private static List<Contact> contacts = new ArrayList<>();
	static {

		contacts = Arrays.asList(new Contact("A", "abc@gmai.com", "CA", "1122"),
				new Contact("D", "abc@gmai.com", "CA", "1122"), new Contact("B", "abc@gmai.com", "CA", "1122"),
				new Contact("C", "abc@gmai.com", "CA", "1122"));
	}

	public List<Contact> findAll() {
		return contacts;
	}

	private ApplicationEventPublisher appEventPublisher;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher arg0) {
		this.appEventPublisher = arg0;

	}

	@Autowired
	private ContactDAO contactDAO;

	public Contact findById(int id) {
		return contactDAO.get(id);
	}

	public List<Contact> getList() {
		return contactDAO.list();
	}

	public void insert(Contact contact) {
		contactDAO.saveOrUpdate(contact);
		String eventData = contact.getName() + contact.getTelephone();
		appEventPublisher.publishEvent(new ContactEvent(this, "ContactSaved", eventData));
	}

	public void update(Contact contact) {
		contactDAO.saveOrUpdate(contact);
	}

	public void delete(int contactId) {
		contactDAO.delete(contactId);
	}

}
