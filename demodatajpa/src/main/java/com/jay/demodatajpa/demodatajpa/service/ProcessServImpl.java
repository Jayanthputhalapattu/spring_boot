package com.jay.demodatajpa.demodatajpa.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.jay.demodatajpa.demodatajpa.ExceptionHandle.ProcessorNotFoundException;
import com.jay.demodatajpa.demodatajpa.constants.AllConstants;
import com.jay.demodatajpa.demodatajpa.dto.ProcessDTO;
import com.jay.demodatajpa.demodatajpa.entities.Processr;
import com.jay.demodatajpa.demodatajpa.repo.ProcessServiceRepo;

@Service("processr")
@PropertySource("classpath:exceptionMessages.properties")
@Validated
public class ProcessServImpl {
	@Autowired
	private ProcessServiceRepo repo;
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private Environment env;
    public void insertProcessr(ProcessDTO dto)
    {
    	repo.save(mapper.map(dto, Processr.class));
    	System.out.println("Data inserted successfully");
    }
    
    public List<ProcessDTO> getAllProcessors()
    {
    	return repo.findAll().stream()
//    			.map(processr -> processr.createProcessDTO(processr))
    			.map(p->mapper.map(p,ProcessDTO.class))
    			.collect(Collectors.toList());
    	
    }
    public ProcessDTO getProcessor(int no) throws ProcessorNotFoundException
    {
    	Optional<Processr> prOpt =  repo.findById(no);
    	if (prOpt.isPresent())
    		return mapper.map(prOpt.get(), ProcessDTO.class);
    	else
    		throw new ProcessorNotFoundException(env.getProperty(AllConstants.PROCESSOR_NOT_FOUND.toString()));
    }
    public void deleteProcess(int no)
    {
    	repo.deleteById(no);
    }
    public void updateCostById(int id,int newCost)
    {
    	repo.updateCostById(id,newCost);
    }
    public void addProcessor(ProcessDTO dto)
    {
    	repo.save(mapper.map(dto, Processr.class));
    }
}
