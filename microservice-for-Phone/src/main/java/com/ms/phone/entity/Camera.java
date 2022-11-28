package com.ms.phone.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Camera {
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private double megaPixels;
	private String manufacture;
	private int cost;
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name="cameras_fixed",joinColumns = {@JoinColumn(name = "camera_id")}, inverseJoinColumns = {@JoinColumn(name = "phone_id")} )
//	private List<Integer> phones = new ArrayList<>();
	public Camera() {
		super();
	}
	public Camera(int id, double megaPixels, String manufacture, int cost) {
		super();
		this.id = id;
		this.megaPixels = megaPixels;
		this.manufacture = manufacture;
		this.cost = cost;
//		this.phone = phone;
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
//	public List<Phone> getPhone() {
//		return phones;
//	}
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
//	public void setPhone(List<Phone> phones) {
//		this.phones = phones;
//	}
	@Override
	public String toString() {
		return "Camera [id=" + id + ", megaPixels=" + megaPixels + ", manufacture=" + manufacture + ", cost=" + cost
				+ "]";
	}
//	public void addPhone(Phone ph) {
//		// TODO Auto-generated method stub
//		phones.add(ph);
//	}

   
}
