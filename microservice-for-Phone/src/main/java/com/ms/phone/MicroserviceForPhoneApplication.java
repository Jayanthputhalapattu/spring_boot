package com.ms.phone;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
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
//	@Bean @LoadBalanced
//	public RestTemplate template()
//	{
//		return new RestTemplate();
//	}
}
