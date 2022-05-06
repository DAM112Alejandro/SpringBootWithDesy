package com.example.libreria.resource;


import javax.mail.MessagingException;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.libreria.Jobs.Job1;
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
	@EventListener(ApplicationReadyEvent.class)
	public void d() {
			
		try {
		
		
		JobDetail job1 =JobBuilder.newJob(Job1.class).withIdentity("job1","group1").build();
		
		
		Trigger t = TriggerBuilder.newTrigger().withIdentity("cronTrigger1", "group1")
				.startNow()
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0/5 * * * ?"))
				.build();
		Scheduler scheduler= new StdSchedulerFactory().getScheduler();
		
		scheduler.start();
			
			scheduler.scheduleJob(job1, t);
			
			
			
			
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		}
	}
	
	


