package com.jay.demodatajpa.demodatajpa.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jay.demodatajpa.demodatajpa.dto.CameraDTO;
import com.jay.demodatajpa.demodatajpa.entities.Camera;
import com.jay.demodatajpa.demodatajpa.service.CameraService;



@RestController
@RequestMapping("/cameras")
public class CamerController {
  
	@Autowired
	private CameraService service;
	
	@Autowired
	private ModelMapper mapper;
	@GetMapping
	public List<CameraDTO> getAllcams()
	{
		return service.getAllCams();
	}
	@PostMapping
	public String insertCamera(@RequestBody CameraDTO camera)
	{
		System.out.println(mapper.map(camera,Camera.class));
		service.insertCamera(camera);
		return "data inserted successfully : " + camera ;
	}
	@GetMapping("/{id}")
	public ResponseEntity<CameraDTO> getCamera(@PathVariable("id") int id)
	{
	return ResponseEntity.status(HttpStatus.OK).body(service.getCamera(id));
	}
	@PutMapping("/{id}")
	public ResponseEntity<String> updateCameraMegaPixelsById(@PathVariable int id,@RequestParam("mp") double mp)
	{
		service.updateCameraMpById(id, mp);
		return ResponseEntity.status(HttpStatus.OK)
				.body("Update Success with id : " + id + "  Mp : " + mp);
	}
}
