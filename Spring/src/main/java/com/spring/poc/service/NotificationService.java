package com.spring.poc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("notificationService")
public class NotificationService {

	private final JavaMailSender javaMailSender;

	@Autowired
	NotificationService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendNotification() {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo("Jai.Ruwari@rsystem.com");
		mailMessage.setReplyTo("jaibardhan16@gmail.com");
		mailMessage.setFrom("jaibardhan16@gmail.com");
		mailMessage.setSubject("Test Mail Subject ");
		mailMessage.setText("Spring Boot [...]");
		javaMailSender.send(mailMessage);
	}

}
