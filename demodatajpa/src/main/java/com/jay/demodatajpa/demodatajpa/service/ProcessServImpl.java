package com.jay.demodatajpa.demodatajpa.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jay.demodatajpa.demodatajpa.dto.ProcessDTO;
import com.jay.demodatajpa.demodatajpa.entities.Processr;
import com.jay.demodatajpa.demodatajpa.repo.ProcessServiceRepo;

@Service("processr")
public class ProcessServImpl {
	@Autowired
	private ProcessServiceRepo repo;
	ModelMapper mapper = new ModelMapper();
    public void insertProcessr(ProcessDTO dto)
    {
    	repo.save(dto.createProcessEntity(dto));
    	System.out.println("Data inserted successfully");
    }
    
    public List<ProcessDTO> getAllProcessors()
    {
    	return repo.findAll().stream()
//    			.map(processr -> processr.createProcessDTO(processr))
    			.map(p->mapper.map(p,ProcessDTO.class))
    			.collect(Collectors.toList());
    	
    }
    public ProcessDTO getProcessor(int no)
    {
    	Optional<Processr> prOpt =  repo.findById(no);
    		return mapper.map(prOpt.get(), ProcessDTO.class);
    }
    public void deleteProcess(int no)
    {
    	repo.deleteById(no);
    }
    public void updateCostById(int id,int newCost)
    {
    	repo.updateCostById(id,newCost);
    }
}
