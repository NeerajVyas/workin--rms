package com.casestudy.rms.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Provide Email Services.
 */
@Service
public class EmailServiceImpl {

	public static final Logger LOGGER = Logger.getLogger(EmailServiceImpl.class);

	@Autowired
	private JavaMailSender sender;

	/**
	 * Gets Java Mail Sender.
	 * 
	 * @return Java Mail Sender
	 */
	public JavaMailSender getSender() {
		return sender;
	}

	/**
	 * Sets Java Mail Sender.
	 * 
	 * @param sender - Java Mail Sender
	 */
	public void setSender(JavaMailSender sender) {
		this.sender = sender;
	}

	/**
	 * Method sends email .
	 * 
	 * @param to      - Recipient Email Id
	 * @param subject - Subject of Email
	 * @param text    - Email Body
	 * @throws MailException - Mail Exception
	 */
	@Async
	public void sendSimpleMessage(String to, String subject, String text) {
		LOGGER.info("EmailServiceImpl :: sendSimpleMessage");
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("mailer.rms.2019@gmail.com");
		mail.setTo(to);
		mail.setText(text);
		mail.setSubject(subject);
		try {
			sender.send(mail);
		} catch (Exception e) {
			LOGGER.error("EmailServiceImpl :: sendSimpleMessage");
			LOGGER.error("Invalid Email Address");
		}

	}

}