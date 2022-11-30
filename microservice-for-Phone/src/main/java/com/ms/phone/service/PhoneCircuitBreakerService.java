package com.ms.phone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ms.phone.dto.PhoneDTO;
import com.ms.phone.dto.ProcessDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.vavr.concurrent.Future;
@Service
public class PhoneCircuitBreakerService {
   
	@Autowired
	RestTemplate template;
	@CircuitBreaker(name="phoneService")
	public Future<ProcessDTO> getProcessDTO(int processorId)
	{
	  ProcessDTO processdto = 	template.getForObject("http://processorMS/processors/" +processorId, ProcessDTO.class);
//	 System.out.println(processdto);
	 return Future.of(()->processdto);
	}
	@CircuitBreaker(name="phoneService")
	public Future<List<Integer>> getCameras(int imei)
	{
		@SuppressWarnings("unchecked")
	  List<Integer> cameras =  template.getForObject( "http://cameraMS/cameras/phones/" + imei,List.class);
//	  System.out.println("http://processorMS");
		return Future.of(()->cameras);
	}
}
