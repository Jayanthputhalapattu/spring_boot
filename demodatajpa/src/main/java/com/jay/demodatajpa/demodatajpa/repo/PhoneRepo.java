package com.jay.demodatajpa.demodatajpa.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jay.demodatajpa.demodatajpa.entities.Phone;


//@Repository
public interface PhoneRepo extends JpaRepository<Phone, Integer>{
    List<Phone> findByPhoneName(String phoneName);
    
//    @Transactional
    @Query("select p from Phone p where p.process.no = ?1")
    List<Phone> findbyproces(int no);
}
