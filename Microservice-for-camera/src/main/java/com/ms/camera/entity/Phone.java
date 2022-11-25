package com.ms.camera.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Phone {
	@Id
	
    private int imei;
//	@Column(name = "phone_number")
	@NotEmpty(message ="{phone.not.empty}")
    private String phoneName;
	@Column(name = "model_name")
    private String modelName;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Processr process;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "phones",cascade = CascadeType.ALL)
	   private List<Camera> cameras = new ArrayList<>();
	public Processr getProcess() {
		return process;
	}
	public void setProcess(Processr processDTO) {
		this.process = processDTO;
	}
	public Phone(int imei, String phoneName, String modelName, Processr process) {
		super();
		this.imei = imei;
		this.phoneName = phoneName;
		this.modelName = modelName;
		this.process = process;
	}
	public int getImei() {
		return imei;
	}
	public void setImei(int imei) {
		this.imei = imei;
	}
	public String getPhoneName() {
		return phoneName;
	}
	public void setPhoneName(String phoneName) {
		this.phoneName = phoneName;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
    public Phone()
    {
    	super();
    }
    public Phone(int imei, String phoneName, String modelName) {
		super();
		this.imei = imei;
		this.phoneName = phoneName;
		this.modelName = modelName;
	}
	public List<Camera> getCameras() {
		return cameras;
	}
	public void setCameras(List<Camera> cameras) {
		this.cameras = cameras;
	}
	@Override
	public String toString() {
		return "Phone [imei=" + imei + ", phoneName=" + phoneName + ", modelName=" + modelName + ", process=" + process
				+ ", cameras=" + cameras + "]";
	}
	public void addCamera(Camera cam) {
		cameras.add(cam);
		
	}
	
}
