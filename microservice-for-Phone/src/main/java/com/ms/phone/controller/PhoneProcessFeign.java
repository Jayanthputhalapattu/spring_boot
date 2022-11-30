package com.ms.phone.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ms.phone.dto.ProcessDTO;



@FeignClient(name = "processorMS",url = "http://localhost:9000")
public interface PhoneProcessFeign {
		@RequestMapping("/processors/{id}")
		public ProcessDTO getSpecificProcessor(@PathVariable("id") int processId);
		
		
}
