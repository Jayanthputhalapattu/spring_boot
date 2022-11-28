package com.ms.processor.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ms.processor.constants.AllConstants;
import com.ms.processor.dto.ProcessDTO;
import com.ms.processor.entity.Processr;
import com.ms.processor.exceptionHandling.ProcessorNotFoundException;
import com.ms.processor.repo.ProcessServiceRepo;



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
    public void deleteProcess(int no) throws ProcessorNotFoundException
    {
    	Optional<Processr> prOptional = repo.findById(no);
    	if (prOptional.isPresent())
    	{
    		repo.delete(prOptional.get());
    	}
    	else {
    		throw new ProcessorNotFoundException(
        			env.getProperty(AllConstants.PROCESSOR_NOT_FOUND.toString()));
    	}

    }
    public void updateCostById(int id,int newCost) throws ProcessorNotFoundException
    {
    	Optional<Processr> prOptional = repo.findById(id);
    	if (prOptional.isPresent())
    	{
    		repo.updateCostById(id,newCost);
    	}
    	else	
    		throw new ProcessorNotFoundException(env.getProperty(AllConstants.PROCESSOR_NOT_FOUND.toString()));
    	
    }
    public void addProcessor(ProcessDTO dto)
    {
    	repo.save(mapper.map(dto, Processr.class));
    }
}
