package com.tpe.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tpe.domain.Message;
import com.tpe.repository.Repository;

@Component
//@Scope("singleton") default scope singleton
@Scope("prototype") // new object for each request ... eger prototype ise ioc data'yi initialize ediyor ancak 
//destroy islemini YONETMEZ.. Bean Life Cycle singleton beanleri initialize edip destroy eder.
@Qualifier("mailService")
public class MailService implements MessageService {
	
	//Field injection
	
	
	@PostConstruct
	public void init() {
		System.out.println("Initializig");
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("Destroying");
	}
	
	
	
	@Autowired//otomatik olarak basla
	@Qualifier("fileRepository")
	private Repository repository; 
	
	
	@Value("${app.email}")
	private String email;
	

	@Override
	public void sendMessage(Message message) {
		System.out.println("I am a mail service, I am sending a message:"+message.getMessage());
		repository.saveMessage(message);
	}
	
	
	

}
