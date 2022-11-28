package com.ms.processor;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;

@SpringBootApplication
public class MicroserviceForProcessorApplication {

	public static void main(String[] args) {
//		SpringApplication.run(MicroserviceForProcessorApplication.class, args);
		AbstractApplicationContext context = (AbstractApplicationContext)SpringApplication.run(MicroserviceForProcessorApplication.class, args);
		
	}
	@Bean
   public ModelMapper modelMapper()
   {
	   return new ModelMapper();
   }
}
