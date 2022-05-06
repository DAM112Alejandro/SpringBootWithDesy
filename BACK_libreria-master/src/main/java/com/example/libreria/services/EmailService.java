package com.example.libreria.services;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
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
//	 @Scheduled( cron = "0 0/5 * * * ?")
//	public void sendEmailAuto() {
//		try {
//			MimeMessage mimeMessage=emailSender.createMimeMessage();
//			MimeMessageHelper mimeMessageHelper= new MimeMessageHelper(mimeMessage,false);
//		
//			mimeMessageHelper.setFrom("testossot@gmail.com");
//			mimeMessageHelper.setTo("testossot@gmail.com");
//			mimeMessageHelper.setSubject("Correo Automatico");
//			mimeMessageHelper.setText("");
//			emailSender.send(mimeMessage);
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	public void sendEmail() {
		try {
			MimeMessage mimeMessage=emailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper= new MimeMessageHelper(mimeMessage,false);
		
			mimeMessageHelper.setFrom("testossot@gmail.com");
			mimeMessageHelper.setTo("testossot@gmail.com");
			mimeMessageHelper.setSubject("Correo Automatico");
			mimeMessageHelper.setText("");
			emailSender.send(mimeMessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	}
	


