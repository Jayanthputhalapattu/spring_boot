package com.ms.camera.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "phone_id")
	private int phoneId;
	@Column(name = "cam_id")
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
		return "Camera [id=" + id + ", phoneId=" + phoneId + ", camId=" + camId + "]";
	}
	public Camera(int id, int phoneId, int camId) {
		super();
		this.id = id;
		this.phoneId = phoneId;
		this.camId = camId;
	}
	public Camera() {
		super();
	}
    
	
	

}
