package com.jay.demodatajpa.demodatajpa.controller;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import com.jay.demodatajpa.demodatajpa.ExceptionHandle.PhoneNotFoundException;
import com.jay.demodatajpa.demodatajpa.dto.PhoneDTO;
import com.jay.demodatajpa.demodatajpa.entities.Phone;
import com.jay.demodatajpa.demodatajpa.entities.Processr;
import com.jay.demodatajpa.demodatajpa.service.PhoneServiceImpl;

@RestController
@RequestMapping("/phones")
public class PhoneController {
	@Autowired
     private PhoneServiceImpl service;
//     private static Logger logger = LoggerFactory.getLogger(PhoneController.class);
	private ModelMapper mapper;
	
	@GetMapping
	public ResponseEntity<List<PhoneDTO>>  getAllPhones()
	{
	return ResponseEntity.status(HttpStatus.OK).body(service.getAllPhones());
	}

//	public List<PhoneDTO> getAllPhones()
//	{
//		return service.getAllPhones();
//	}
	@DeleteMapping("/{id}")
	private void deletePhone(@PathVariable("id") int id)
	{
		service.deletePhone(id);
	}
	
	@PostMapping(consumes = "application/json")
	public String addPhone(@Valid @RequestBody PhoneDTO dto)
	{
		service.addPhone(dto);
		return "data added successfully : + " + dto; 
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<PhoneDTO> getPhone(@PathVariable("id") int imei) throws PhoneNotFoundException
	{
		return ResponseEntity.status(HttpStatus.OK)
				.body(service.getPhone(imei));
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
	{
		service.updateProcessById(id, processid);
		return ResponseEntity.status(HttpStatus.OK).body("update Success");
	}
}
