package com.jay.demodatajpa.demodatajpa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jay.demodatajpa.demodatajpa.ExceptionHandle.ProcessorNotFoundException;
import com.jay.demodatajpa.demodatajpa.dto.ProcessDTO;
import com.jay.demodatajpa.demodatajpa.service.ProcessServImpl;

@RestController
@RequestMapping("/processors")
public class ProcessrController {
	@Autowired
	private ProcessServImpl service;
	
	@GetMapping
    public List<ProcessDTO> getAllProcessors()
    {
    	return service.getAllProcessors();
    }
	@GetMapping("/{no}")
	public ProcessDTO getProcessor(@PathVariable("no") int no) throws ProcessorNotFoundException
	{
		return service.getProcessor(no);
	}
	
	@PostMapping
	public ResponseEntity<String> addProcessor(@Valid @RequestBody ProcessDTO dto)
	{
		service.addProcessor(dto);
		return ResponseEntity.status(HttpStatus.OK)
				.body("Data added successfully : " + dto);
	}
}
