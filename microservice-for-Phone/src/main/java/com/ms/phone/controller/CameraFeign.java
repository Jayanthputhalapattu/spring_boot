package com.ms.phone.controller;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="cameraMS",url="http://localhost:9000/")
public interface CameraFeign {
   
	@RequestMapping("/cameras/phones/{id}")
	List<Integer> getCameras(@PathVariable("id") int id);
	
 }
