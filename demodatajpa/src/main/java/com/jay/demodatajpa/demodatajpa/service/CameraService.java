package com.jay.demodatajpa.demodatajpa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jay.demodatajpa.demodatajpa.dto.CameraDTO;
import com.jay.demodatajpa.demodatajpa.entities.Camera;
import com.jay.demodatajpa.demodatajpa.repo.CameraRepo;

@Service("cameraservice")
public class CameraService {
	
	@Autowired
	private CameraRepo repo;
	@Autowired
	private ModelMapper mapper;
  public List<CameraDTO> getAllCams()
  {
	return  repo.findAll().stream()
	  .map(p -> mapper.map(p, CameraDTO.class))
	  .collect(Collectors.toList());
  }
  public void insertCamera(CameraDTO dto)
  {
	  repo.save(mapper.map(dto, Camera.class));
  }
  public void updateMegapixelsByid(int id,double mp)
  {
	     repo.updateMegaPixelsById(id, mp);
	     System.out.println("Update success with id : " + id + " Mp :" + mp);
  }
  public CameraDTO getCamera(int id)
  {
	  return mapper.map(repo.findById(id).get(),CameraDTO.class );
  }
  public void updateCameraMpById(int id , double mp)
  {
	  repo.updateMegaPixelsById(id, mp);
  }
}
