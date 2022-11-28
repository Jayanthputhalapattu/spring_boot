package com.ms.phone.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ManyToOne;



public class CameraDTO {
	private int id;
	private double megaPixels;
	private String manufacture;
	private int cost;
	private List<PhoneDTO> phones = new ArrayList<>();
	public CameraDTO() {
		super();
	}
	public CameraDTO(int id, double megaPixels, String manufacture, int cost) {
		super();
		this.id = id;
		this.megaPixels = megaPixels;
		this.manufacture = manufacture;
		this.cost = cost;
	
	}
	
	public CameraDTO(int id, double megaPixels, String manufacture, int cost, List<PhoneDTO> phones) {
		super();
		this.id = id;
		this.megaPixels = megaPixels;
		this.manufacture = manufacture;
		this.cost = cost;
		this.phones = phones;
	}
	@Override
	public String toString() {
		return "CameraDTO [id=" + id + ", megaPixels=" + megaPixels + ", manufacture=" + manufacture + ", cost=" + cost
				+ ", phones=" + phones + "]";
	}
	public int getId() {
		return id;
	}
	public double getMegaPixels() {
		return megaPixels;
	}
	public String getManufacture() {
		return manufacture;
	}
	public int getCost() {
		return cost;
	}
	public List<PhoneDTO> getPhones() {
		return phones;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setMegaPixels(double megaPixels) {
		this.megaPixels = megaPixels;
	}
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public void setPhones(List<PhoneDTO> phones) {
		this.phones = phones;
	}
	public void addPhone(PhoneDTO dto)
	{
		phones.add(dto);
	}
	
	
}
