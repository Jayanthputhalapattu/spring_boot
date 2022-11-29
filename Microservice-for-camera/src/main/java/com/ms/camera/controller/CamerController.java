package com.ms.camera.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ms.camera.dto.CameraDTO;
import com.ms.camera.entity.Camera;
import com.ms.camera.service.CameraService;




@RestController
@RequestMapping("/cameras")
@CrossOrigin
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
//	@PutMapping("/{id}")
//	public ResponseEntity<String> updateCameraMegaPixelsById(@PathVariable int id,@RequestParam("mp") double mp)
//	{
//		service.updateCameraMpById(id, mp);
//		return ResponseEntity.status(HttpStatus.OK)
//				.body("Update Success with id : " + id + "  Mp : " + mp);
//	}
	@GetMapping("/phones/{phoneId}")
	public ResponseEntity<List<Integer>> getByPhoneId(@PathVariable("phoneId") int phoneId)
	{
		if(phoneId==1)
		{
			throw new RuntimeException();
		}
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(service.geByPhoneId(phoneId)
						.stream()
						.map(c->
						{
							CameraDTO camdto = 	mapper.map(c, CameraDTO.class);
							return camdto.getCamId();
						})
						
						.collect(Collectors.toList()));
		
	}
}
