package com.ms.phone.repo;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ms.phone.entity.Phone;


@Repository
public interface PhoneRepo extends JpaRepository<Phone, Integer>{
    List<Phone> findByPhoneName(String phoneName);
    
//    @Transactional
    @Query("select p from Phone p where p.processorId = ?1")
    List<Phone> findbyproces(int no);
    
    @Query("update Phone p set p.processorId = ?2 where p.id = ?1")
    @Modifying
    @Transactional
    public void updateProcessById(int id,int process_id);
    
    @Query("select p from Phone p where p.phoneName = ?1 and p.processorId = ?2")
    public List<Phone> findByPhoneNameandProcess(String phoneName,int no);
    
    
    //whenever you are passing the collection framework then use IN variable
    @Query("SELECT p FROM Phone p WHERE p.phoneName IN (?1)")
    public List<Phone> findPhonesByName(List<String> names);
    
  
   
    
}
