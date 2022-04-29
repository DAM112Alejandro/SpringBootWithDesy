package com.example.libreria.resource;

import javax.mail.MessagingException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.libreria.model.Email;
import com.example.libreria.services.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@RestController
@RequestMapping("/email")
public class EmailResource {
	@Autowired
	ObjectMapper objetMapper;
	
	@Autowired
	private EmailService emailService;

	public EmailResource(EmailService emailService) {
		this.emailService = emailService;
	}

	@PostMapping("/enviar")
	public void sendEmail(@RequestParam(value = "email") String email,
			@RequestParam(value = "file", required = false) MultipartFile file)
			throws MessagingException, UnsupportedClassVersionError {
		Email correo = null;
		try {
			correo = objetMapper.readValue(email, Email.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		emailService.sendSimpleMail(correo, file);
	}

}
