package com.ms.camera.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ms.camera.entity.Camera;


@Repository
public interface CameraRepo extends JpaRepository<Camera,Integer>{

	@Query("update Camera c set c.megaPixels = ?2 where c.id = ?1")
	@Transactional
	@Modifying
	public void updateMegaPixelsById(int id,double mp);
	
	
	
}
