package com.jay.demodatajpa.demodatajpa.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.jay.demodatajpa.demodatajpa.entities.Processr;

public interface ProcessServiceRepo extends JpaRepository<Processr, Integer>{
   
	@Query("update processor p set p.cost = ?2 where p.no = ?1")
	@Modifying
	@Transactional
	public void updateCostById(int no,int newCost);
	
}
