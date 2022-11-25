package com.ms.phone;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;

@SpringBootApplication
public class MicroserviceForPhoneApplication {

	public static void main(String[] args) {
//		SpringApplication.run(MicroserviceForPhoneApplication.class, args);
		
		AbstractApplicationContext context = (AbstractApplicationContext)SpringApplication.run(MicroserviceForPhoneApplication.class,args);
		
		
	}

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
}
