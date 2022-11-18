package com.jay.demodatajpa.demodatajpa.service;


import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jay.demodatajpa.demodatajpa.dto.PhoneDTO;
import com.jay.demodatajpa.demodatajpa.entities.Phone;
import com.jay.demodatajpa.demodatajpa.repo.PhoneRepo;



@Service("phoneService")
public class PhoneServiceImpl {
	@Autowired
	private PhoneRepo repo;
	
	public List<PhoneDTO> getAllPhones()
	{
	List<Phone> optList = repo.findAll();
	List<PhoneDTO> dto = optList.stream().map(p->p.createPhoneDTO(p))
			.sorted((p1,p2)->p1.getPhoneName().compareTo(p2.getPhoneName()))
//			.sorted((p1,p2)->p1.getModelName().compareTo(p2.getModelName()))
			.collect(Collectors.toList());
    
	return dto; 
		
	}
    public PhoneDTO getPhone(int imei)
    {
    	Optional<Phone> phOpt = repo.findById(imei);
    	Phone ph = phOpt.get();
    	PhoneDTO dto = ph.createPhoneDTO(ph);
    	
    	return dto;
    	
    	
    }
    public void addPhone(PhoneDTO dto)
    {
    	repo.saveAndFlush(dto.createPhoneEntity(dto));
        System.out.println("Data added succesfully check that in database");
    }
    public void deletePhone(int imei)
    {
    	repo.deleteById(imei);
    	
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
    			.map(ph->ph.createPhoneDTO(ph))
    			.collect(Collectors.toList());
    }
    public void findByProcess(int no)
    {
    	repo.findbyproces(no).forEach(p->System.out.println(p));
    }
    
    public void updateProcessById(int id,int processId)
    {
    	repo.updateProcessById(id, processId);
        System.out.println("Update success with : id " + id  + " processID :" + processId);;
    }
}
