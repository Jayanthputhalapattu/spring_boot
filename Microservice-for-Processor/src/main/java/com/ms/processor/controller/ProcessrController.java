package com.ms.processor.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ms.processor.dto.ProcessDTO;
import com.ms.processor.exceptionHandling.ProcessorNotFoundException;
import com.ms.processor.service.ProcessServImpl;


@RestController
@RequestMapping("/processors")
//@Validated
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
	@DeleteMapping("/{no}")
	public ResponseEntity<String> deleteProcessor(@PathVariable("no") int no) throws ProcessorNotFoundException
	{
		service.deleteProcess(no);
		return ResponseEntity.status(HttpStatus.OK)
				.body("Data deleted successfully with id :  " + no  );
	}
	@PutMapping("/{no}")
	public ResponseEntity<String> updateCostById(@PathVariable("no") int no,@RequestParam("cost") int cost)
	throws ProcessorNotFoundException
	{
		service.updateCostById(no, cost);
		return ResponseEntity.status(HttpStatus.OK)
				.body("Data updated successfully with id :  " + no + " cost : " + cost  );
	}
}
