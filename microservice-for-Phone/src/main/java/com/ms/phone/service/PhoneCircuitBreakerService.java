package com.ms.phone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ms.phone.controller.CameraFeign;
import com.ms.phone.controller.PhoneProcessFeign;
import com.ms.phone.dto.PhoneDTO;
import com.ms.phone.dto.ProcessDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.vavr.concurrent.Future;
@Service
public class PhoneCircuitBreakerService {
   

	
	@Autowired
    PhoneProcessFeign phoneprocessFeign;
    @Autowired
    CameraFeign cameraFeign;
  
	@CircuitBreaker(name="phoneService")
	public Future<ProcessDTO> getProcessDTO(int processorId)
	{
	 return Future.of(()->phoneprocessFeign.getSpecificProcessor(processorId));
	}
	@CircuitBreaker(name="phoneService")
	public Future<List<Integer>> getCameras(int imei)
	{
	  return Future.of(()->cameraFeign.getCameras(imei));
	}
}
