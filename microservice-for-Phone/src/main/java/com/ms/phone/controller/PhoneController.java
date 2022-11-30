package com.ms.phone.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.*;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;

import com.ms.phone.LoadBalancerConfig;
import com.ms.phone.dto.PhoneDTO;
import com.ms.phone.dto.ProcessDTO;
import com.ms.phone.exceptionHanlding.PhoneNotFoundException;
import com.ms.phone.service.PhoneCircuitBreakerService;
import com.ms.phone.service.PhoneServiceImpl;
import io.github.resilience4j.*;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.vavr.concurrent.Future;

@RestController
@RequestMapping("/phones")
@OpenAPIDefinition(info = @Info(title = "Phone controller apis"))
@CrossOrigin
@EnableAutoConfiguration
//@LoadBalancerClient(name="MyloadBalancer", configuration=LoadBalancerConfig.class)
//@Validated
public class PhoneController {
	@Autowired
	private PhoneServiceImpl service;
	private static Logger logger = LoggerFactory.getLogger(PhoneController.class);
	
    @Autowired
    PhoneProcessFeign phoneprocessFeign;
    
    @Autowired
    CameraFeign cameraFeign;
    
    @Autowired
    PhoneCircuitBreakerService phoneCircuitBreakerService;
	@GetMapping
	@ApiResponse(description="Get all phones from the resp",responseCode = "900")
	public ResponseEntity<List<PhoneDTO>>  getAllPhones()
	{
		

		List<PhoneDTO> listOfPhonedtos = service.getAllPhones().stream()
		.map(phonedto->{
			Future<ProcessDTO> pdto = phoneCircuitBreakerService.getProcessDTO(phonedto.getProcess().getNo());
			
			Future<List<Integer>> cdto = phoneCircuitBreakerService.getCameras(phonedto.getImei());
			phonedto.setCameras(cdto.get());
			phonedto.setProcess(pdto.get());
			return phonedto;
		}).toList();
		return ResponseEntity.status(HttpStatus.OK)
				.body(listOfPhonedtos);
	}
	@DeleteMapping("/{id}")
	public void deletePhone(@PathVariable("id") int id) throws PhoneNotFoundException
	{
		service.deletePhone(id);
		
	}
	
	@PostMapping(consumes = "application/json")
	public String addPhone(@Valid @RequestBody PhoneDTO dto)
	{
		service.addPhone(dto);
		return "data added successfully : + " + dto; 
	}
	
//	@CircuitBreaker(name="phoneService" ,fallbackMethod = "phoneDetailsFallBack")
	@GetMapping("/{id}")
	public ResponseEntity<PhoneDTO> getPhone(@PathVariable("id") int imei) throws PhoneNotFoundException
	{
		PhoneDTO dto = service.getPhone(imei);
		
		Future<ProcessDTO> processdto = phoneCircuitBreakerService.getProcessDTO(dto.getProcess().getNo());
		
		
		Future<List<Integer>> cameras = phoneCircuitBreakerService.getCameras(imei);
		dto.setCameras(cameras.get());
		dto.setProcess(processdto.get());
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(dto);
	}
	
	@GetMapping("/name")
	public ResponseEntity<List<PhoneDTO>> getPhone(@RequestParam("phoneName") String phoneName,@RequestParam("process") int process)
	{
		return ResponseEntity.status(HttpStatus.OK).body(service.findByPhoneNameandProcess(phoneName,process));
	}
	
	@GetMapping("/query/{phonequery}")
	public ResponseEntity<List<PhoneDTO>> getPhonesByNames(@MatrixVariable(pathVar = "phonequery")Map<String,List<String>> map)
	{
	
	  
		System.out.println(map.values().iterator().next());
		return ResponseEntity.status(HttpStatus.OK)
				.body(service.findPhonesByName(map.values().iterator().next()));
	}
	@PutMapping("/{id}/{processid}")
	public ResponseEntity<String> updateProcessById(@PathVariable("id") int id,@PathVariable("processid") int processid)
			throws PhoneNotFoundException
	{
		service.updateProcessById(id, processid);
		return ResponseEntity.status(HttpStatus.OK).body("update Success");
	}
	
//	@PutMapping("/{phoneid}/camera/{cameraid}")
//	public ResponseEntity<String> addCameraById(@PathVariable("phoneid") int phoneid,@PathVariable("cameraid") int cameraid)
//	{
////		service.updateCameraById(phoneid, cameraid);
//		return ResponseEntity.status(HttpStatus.OK)
//				.body("Data updated succesfully ,phoneid : "  + phoneid + " , cameraid : " + cameraid);
//	}
	
	public ResponseEntity<PhoneDTO> phoneDetailsFallBack(int imei,Throwable throwable)
	
	{
	  	logger.info("=====> IN FALL BACK =====>");
	  	System.out.println("Hello fallback");
	  	return ResponseEntity.status(HttpStatus.OK)
	  			.body(new PhoneDTO());
	}
}
