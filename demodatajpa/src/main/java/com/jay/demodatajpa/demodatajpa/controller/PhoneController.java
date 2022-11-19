package com.jay.demodatajpa.demodatajpa.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jay.demodatajpa.demodatajpa.dto.PhoneDTO;
import com.jay.demodatajpa.demodatajpa.service.PhoneServiceImpl;

@RestController
@RequestMapping("/phones")
public class PhoneController {
	@Autowired
     private PhoneServiceImpl service;
     
	private ModelMapper mapper;
	
	@GetMapping
	public List<PhoneDTO> getAllPhones()
	{
		return service.getAllPhones();
	}
	@DeleteMapping("/{id}")
	private void deletePhone(@PathVariable("id") int id)
	{
		service.deletePhone(id);
	}
	
	@PostMapping(consumes = "application/json")
	public String addPhone(@RequestBody PhoneDTO dto)
	{
		service.addPhone(dto);
		return "data added successfully : + " + dto; 
	}
}
