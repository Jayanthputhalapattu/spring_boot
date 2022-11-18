package com.jay.demodatajpa.demodatajpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jay.demodatajpa.demodatajpa.dto.ProcessDTO;
import com.jay.demodatajpa.demodatajpa.repo.ProcessServiceRepo;

@Service("processr")
public class ProcessServImpl {
	@Autowired
	private ProcessServiceRepo repo;
    public void insertProcessr(ProcessDTO dto)
    {
    	repo.save(dto.createProcessEntity(dto));
    	System.out.println("Data inserted successfully");
    }
    
    
}
