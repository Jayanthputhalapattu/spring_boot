package com.ms.camera.dto;

import java.util.ArrayList;
import java.util.List;

public class CameraDTO {
	private int id;
//	private double megaPixels;
//	private String manufacture;
//	private int cost;
	private int phoneId;
	private int camId;
	public int getId() {
		return id;
	}
	public int getPhoneId() {
		return phoneId;
	}
	public int getCamId() {
		return camId;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPhoneId(int phoneId) {
		this.phoneId = phoneId;
	}
	public void setCamId(int camId) {
		this.camId = camId;
	}
	@Override
	public String toString() {
		return "CameraDTO [id=" + id + ", phoneId=" + phoneId + ", camId=" + camId + "]";
	}
	public CameraDTO() {
		super();
	}
	
	 
	
}
