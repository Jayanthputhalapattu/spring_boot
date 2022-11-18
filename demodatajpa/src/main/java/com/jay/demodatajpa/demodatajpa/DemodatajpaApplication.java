package com.jay.demodatajpa.demodatajpa;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.jay.demodatajpa.demodatajpa.dto.PhoneDTO;
import com.jay.demodatajpa.demodatajpa.dto.ProcessDTO;
import com.jay.demodatajpa.demodatajpa.entities.Phone;
import com.jay.demodatajpa.demodatajpa.entities.Processr;
import com.jay.demodatajpa.demodatajpa.repo.ProcessServiceRepo;
import com.jay.demodatajpa.demodatajpa.service.PhoneServiceImpl;
import com.jay.demodatajpa.demodatajpa.service.ProcessServImpl;

@SpringBootApplication
public class DemodatajpaApplication {

	public static void main(String[] args) {
		
		AbstractApplicationContext context = (AbstractApplicationContext)SpringApplication.run(DemodatajpaApplication.class, args);
	
	   PhoneServiceImpl service = (PhoneServiceImpl)context.getBean("phoneService");
	   
	   //Addding data to the database creating dtos
	   PhoneDTO dto1 = new PhoneDTO();
	   dto1.setImei(123);
	   dto1.setModelName("note 5 pro");
	   dto1.setPhoneName("Redmi");
	   service.addPhone(dto1);
	   PhoneDTO dto2 = new PhoneDTO();
	   dto2.setImei(1234);
	   dto2.setModelName("9 pro plus");
	   dto2.setPhoneName("Realme");
	   service.addPhone(dto2);
	   service.addPhone(new PhoneDTO(981,"Samsung","M54"));
	   service.addPhone(new PhoneDTO(909,"Samsung","M21"));
	   service.addPhone(new PhoneDTO(76, "IQOO", "9 se"));
	   service.addPhone(new PhoneDTO(9812,"Celkon","C7"));
	   service.addPhone(new PhoneDTO(878,"Infix","pro 10",new Processr(1, "Snapdragon 645", 2000)));
	   service.addPhone(new PhoneDTO(90,"Nokia","a 5",new Processr(1, "Snapdragon 645", 2000)));
//	   System.out.println(service.getPhone(123));
//	   service.getAllPhones().forEach(p->System.out.println(p));
	   
	   //				DELETE OPERATION
//	   service.deletePhone(981);
	   
	   			//Update operation 
	   service.updatePhone(1234, "9 pro");
	   
	   
	   			//Pagination and sorting
	   Pageable pageable  = PageRequest.of(0, 2);
	   //YOu can use Page instead of Iterable as well
	   Iterable<Phone> phPg = service.findAll(pageable);
	   for (Phone phn : phPg)
	   {
		   System.out.println("Page :" + phn);
	   }
	   List<Phone> phs = service.findAll(Sort.by(Sort.Direction.DESC,"imei"));
	   phs.forEach(p->System.out.println("Sort :" + p));
	   
	   			//Finding by phone name
	    service.findByPhoneName("Samsung").forEach(ph->System.out.println("phname : " + ph)) ;
	   
	   			
	  
	   
	   
	   			//RELATIONSHIPS
	   ProcessServImpl prService = (ProcessServImpl)context.getBean("processr");
	   prService.insertProcessr(new ProcessDTO(1, "Snapdragon 645", 2000));
	   prService.insertProcessr(new ProcessDTO(2,"Snapdragon 888",40000));
	   
	   			//Named Query and query operations
	   service.findByProcess(1);
	   			//Update operation
	   service.updateProcessById(878, 2);
	   
	   
	   
	   
	 //PRINTING AT END OF OPERATIONS
	   System.out.println("PRINTING AT END OF ALL OPERATIONS : ");
	   service.getAllPhones().forEach(p->System.out.println(p));
	   
	}
  
	
	
	
}
