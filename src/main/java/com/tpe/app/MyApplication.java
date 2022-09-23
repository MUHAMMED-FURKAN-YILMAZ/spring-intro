package com.tpe.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import com.tpe.AppConfiguration;
import com.tpe.domain.Message;
import com.tpe.service.MessageService;

public class MyApplication {

	public static void main(String[] args) {
		
		String serviceName="mailService";
		if(args.length>0) {
			serviceName=args[0];
		}
		
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AppConfiguration.class);
		
		//No qualifying bean of type 'com.tpe.service.MessageService' 
		//available: expected single matching bean but found 3: mailService,SMSService,whatsAppService
		MessageService messageService= context.getBean(serviceName,MessageService.class);
		
		// getBean() default olarak singleton olarak tek obje olusturur.. @Scope("singleton")
		MessageService messageService2= context.getBean(serviceName,MessageService.class);
		if(messageService==messageService2) {
			System.out.println("Same object reference");
		}else {
			System.out.println("Different object reference");
		}
		
	
		
		Message message=new Message();
		message.setMessage("Your order was sent");
		
		messageService.sendMessage(message);
		
		
		// ------------
		System.out.println("-------------------------------------------------");

		String[] beanList = context.getBeanDefinitionNames();
		int beanDefinitionCount = context.getBeanDefinitionCount();
		
		System.out.println("Used Bean Count in the application: "+beanDefinitionCount);
		
		for (String beanName : beanList) {
			System.out.println(beanName);
		}

		// --------------------
		
		context.close();

	}

}
