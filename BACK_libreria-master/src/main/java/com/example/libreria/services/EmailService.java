package com.example.libreria.services;


import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.libreria.model.Email;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Service
public class EmailService {
	@Autowired
	private JavaMailSender emailSender;
	
	public void sendSimpleMail(Email email, MultipartFile file) {
		try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            
            mimeMessageHelper.setFrom("testossot@gmail.com");
            mimeMessageHelper.setTo(email.getEmail());
            mimeMessageHelper.setSubject(email.getAsunto());
            mimeMessageHelper.setText(email.getMensaje());
            if(file!=null) {
            byte[] fichBytes = file.getBytes();
            
            mimeMessageHelper.addAttachment(file.getOriginalFilename(), new ByteArrayResource(fichBytes));
            }
            emailSender.send(mimeMessage);
            System.out.println("Email sending complete to "+email.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
		
	}
	


