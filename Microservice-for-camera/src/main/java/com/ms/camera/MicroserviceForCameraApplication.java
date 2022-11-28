package com.ms.camera;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MicroserviceForCameraApplication {

	public static void main(String[] args) {
//		SpringApplication.run(MicroserviceForCameraApplication.class, args);
		AbstractApplicationContext context = (AbstractApplicationContext)SpringApplication.run(MicroserviceForCameraApplication.class, args);
		
	}

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
}
