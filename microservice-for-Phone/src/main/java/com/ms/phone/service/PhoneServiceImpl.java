package com.ms.phone.service;


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
import org.springframework.web.client.RestTemplate;

import com.ms.phone.constants.AllConstants;
import com.ms.phone.dto.PhoneDTO;
import com.ms.phone.dto.ProcessDTO;
import com.ms.phone.entity.Phone;
import com.ms.phone.exceptionHanlding.PhoneNotFoundException;
import com.ms.phone.repo.PhoneRepo;


@Service("phoneService")
@PropertySource("classpath:exceptionMessages.properties")
public class PhoneServiceImpl {
	@Autowired
	private PhoneRepo repo;
	
//	@Autowired
//	private ProcessServiceRepo prepo;
//	
//	@Autowired
//	private CameraRepo camrepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private Environment env;
//	private static Logger logger = LoggerFactory.logger(PhoneServiceImpl.class)
	public PhoneDTO getProcessorAndCamera(PhoneDTO dto, int processorId)
	{
		ProcessDTO processdto = new RestTemplate()
				.getForObject("http://localhost:8020/processors/" +processorId, ProcessDTO.class);
		dto.setProcess(processdto);
		
		List<Integer> cameras = new RestTemplate().getForObject("http://localhost:8030/cameras/phones/" + dto.getImei(),List.class);
		dto.setCameras(cameras);
		return dto;
	}
	
	public List<PhoneDTO> getAllPhones()
	{
	List<Phone> optList = repo.findAll();
	List<PhoneDTO> dto = optList.stream().map(p->{
		
		return getProcessorAndCamera(Phone.createPhoneDTO(p), p.getProcessorId());
	})
//			.sorted((p1,p2)->p1.getPhoneName().compareTo(p2.getPhoneName()))
			.sorted((p1,p2)->p1.getModelName().compareTo(p2.getModelName()))
			.collect(Collectors.toList());
    
	return dto; 
		
	}
	
		//Added exception Handling
    public PhoneDTO getPhone(int imei) throws PhoneNotFoundException
    {
    	Optional<Phone> phOpt = repo.findById(imei);
    	if (phOpt.isPresent())
    	{
//    		return mapper.map(phOpt.get(), PhoneDTO.class);
    		PhoneDTO dto = Phone.createPhoneDTO(phOpt.get());
    		
    		return getProcessorAndCamera(dto, dto.getProcess().getNo());
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
//    	repo.saveAndFlush(mapper.map(dto, Phone.class));
    	Phone ph = PhoneDTO.createPhoneEntity(dto);
    	repo.save(ph);
        System.out.println("Data added succesfully check that in database : " + dto);
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
    
    public void updateProcessById(int id,int processId) throws PhoneNotFoundException
    {
    	Optional<Phone> phOptional = repo.findById(id);
    		
    	//Makwe  a call to the processor repo
//    	Optional<Processr> prOptional  = prepo.findById(processId);
    	ProcessDTO processfto = getProcessorAndCamera(Phone.createPhoneDTO(phOptional.get()), processId).getProcess();
    	if (phOptional.isPresent())
    	{
    		if (processfto!=null)
    		{
    			repo.updateProcessById(id, processId);
    			 System.out.println("Update success with : id " + id  + " processID :" + processId);;
    		}
    		
    	}
    	else 
    	{
    		throw new PhoneNotFoundException(env.getProperty(AllConstants.PHONE_NOT_FOUND.toString()));
    	}
    
    	
       
    }
    public List<PhoneDTO> findByPhoneNameandProcess(String phoneName,int processId)
    {
    	return repo.findByPhoneNameandProcess(phoneName, processId).stream()
    			.map(p->{
    				return getProcessorAndCamera(Phone.createPhoneDTO(p), processId);
    			})
    			.collect(Collectors.toList());
    }
    
    public List<PhoneDTO> findPhonesByName(List<String> names)
    {
    	return repo.findPhonesByName(names).stream()
    			.map(ph->Phone.createPhoneDTO(ph))
//    			.sorted((p1,p2)->p1.getPhoneName().compareTo(p2.getPhoneName()))
    			.collect(Collectors.toList());
    }
//    public void updateCameraById(int phid,int camid)
//    {
//    	
//    	Phone ph = repo.findById(phid).get();
//    	Camera cam = camrepo.findById(camid).get();
//        
//    	ph.addCamera(cam);
//    	cam.addPhone(ph);
//    	repo.saveAndFlush(ph);
//    	camrepo.saveAndFlush(cam);
//    
//    }
    
}
