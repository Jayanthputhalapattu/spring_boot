package com.ms.camera.repo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ms.camera.entity.Camera;


@Repository
public interface CameraRepo extends JpaRepository<Camera,Integer>{


	public Optional<List<Camera>> getByPhoneId(Integer phoneId);
	
	
}
