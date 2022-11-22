package com.jay.demodatajpa.demodatajpa.service;


import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jay.demodatajpa.demodatajpa.ExceptionHandle.PhoneNotFoundException;
import com.jay.demodatajpa.demodatajpa.ExceptionHandle.ProcessorNotFoundException;
import com.jay.demodatajpa.demodatajpa.constants.AllConstants;
import com.jay.demodatajpa.demodatajpa.dto.PhoneDTO;
import com.jay.demodatajpa.demodatajpa.entities.Phone;
import com.jay.demodatajpa.demodatajpa.entities.Processr;
import com.jay.demodatajpa.demodatajpa.repo.PhoneRepo;
import com.jay.demodatajpa.demodatajpa.repo.ProcessServiceRepo;



@Service("phoneService")
@PropertySource("classpath:exceptionMessages.properties")
public class PhoneServiceImpl {
	@Autowired
	private PhoneRepo repo;
	
	@Autowired
	private ProcessServiceRepo prepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private Environment env;
//	private static Logger logger = LoggerFactory.logger(PhoneServiceImpl.class)
	public List<PhoneDTO> getAllPhones()
	{
	List<Phone> optList = repo.findAll();
	List<PhoneDTO> dto = optList.stream().map(p->mapper.map(p, PhoneDTO.class))
			.sorted((p1,p2)->p1.getPhoneName().compareTo(p2.getPhoneName()))
//			.sorted((p1,p2)->p1.getModelName().compareTo(p2.getModelName()))
			.collect(Collectors.toList());
    
	return dto; 
		
	}
	
		//Added exception Handling
    public PhoneDTO getPhone(int imei) throws PhoneNotFoundException
    {
    	Optional<Phone> phOpt = repo.findById(imei);
    	if (phOpt.isPresent())
    	{
    		return mapper.map(phOpt.get(), PhoneDTO.class);
    	}
    	else {
//    		System.out.println(env.getProperty("phone.not.found"));
    		System.out.println(AllConstants.PHONE_NOT_FOUND.toString());
    		throw new PhoneNotFoundException(
    				env.getProperty(AllConstants.PHONE_NOT_FOUND.toString()));
    	}
    
    	
    	
    }
    public void addPhone(PhoneDTO dto) 
    {
    	repo.saveAndFlush(mapper.map(dto, Phone.class));
        System.out.println("Data added succesfully check that in database");
    }
    public void deletePhone(int imei) throws PhoneNotFoundException
    {
    	Optional<Phone> phOpt = repo.findById(imei);
    	if (phOpt.isPresent())
    	{
    		repo.delete(phOpt.get());
    	}
    	else {
    		throw new PhoneNotFoundException(env.getProperty(AllConstants.PHONE_NOT_FOUND.toString()));
    	}
    	
    	
    	System.out.println("Data deleted Successfully");
    }
    public void updatePhone(int imei,String modelname)
    {
    	Optional<Phone> phOpt = repo.findById(imei);
    	Phone entity = phOpt.get();
    	entity.setModelName(modelname);
    	repo.save(entity);  
    	
    	System.out.println("Data updated successfully");
    	
    	
    	
    }
    
    public Page<Phone> findAll(Pageable page)
    {
    	return repo.findAll(page);
    }
    public List<Phone> findAll(Sort sort)
    {
    	return repo.findAll(sort);
    }
    
    public List<PhoneDTO> findByPhoneName(String phoneName)
    {
    	return repo.findByPhoneName(phoneName).stream()
    			.map(ph->mapper.map(ph, PhoneDTO.class))
    			.collect(Collectors.toList());
    }
    public void findByProcess(int no)
    {
    	repo.findbyproces(no).forEach(p->System.out.println(p));
    }
    
    public void updateProcessById(int id,int processId) throws PhoneNotFoundException,ProcessorNotFoundException
    {
    	Optional<Phone> phOptional = repo.findById(id);
    		
    	//Makwe  a call to the processor repo
    	Optional<Processr> prOptional  = prepo.findById(processId);
    	if (phOptional.isPresent())
    	{
    		if (prOptional.isPresent())
    		{
    			repo.updateProcessById(id, processId);
    			 System.out.println("Update success with : id " + id  + " processID :" + processId);;
    		}
    		else {
    			throw new ProcessorNotFoundException(env.getProperty(AllConstants.PROCESSOR_NOT_FOUND.toString()));
    		}
    		
    	}
    	else 
    	{
    		throw new PhoneNotFoundException(env.getProperty(AllConstants.PHONE_NOT_FOUND.toString()));
    	}
    
    	
       
    }
    public List<PhoneDTO> findByPhoneNameandProcess(String phoneName,int process)
    {
    	return repo.findByPhoneNameandProcess(phoneName, process).stream()
    			.map(p->mapper.map(p, PhoneDTO.class))
    			.collect(Collectors.toList());
    }
    
    public List<PhoneDTO> findPhonesByName(List<String> names)
    {
    	return repo.findPhonesByName(names).stream()
    			.map(ph->mapper.map(ph, PhoneDTO.class))
//    			.sorted((p1,p2)->p1.getPhoneName().compareTo(p2.getPhoneName()))
    			.collect(Collectors.toList());
    }
}
