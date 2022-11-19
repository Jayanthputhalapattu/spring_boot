package com.jay.demodatajpa.demodatajpa.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.jay.demodatajpa.demodatajpa.dto.PhoneDTO;


@Entity
public class Phone {
	@Id
    private int imei;
//	@Column(name = "phone_number")
    private String phoneName;
	@Column(name = "model_name")
    private String modelName;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Processr process;
	public Processr getProcess() {
		return process;
	}
	public void setProcess(Processr process) {
		this.process = process;
	}
	public Phone(int imei, String phoneName, String modelName, Processr process) {
		super();
		this.imei = imei;
		this.phoneName = phoneName;
		this.modelName = modelName;
		this.process = process;
	}

	
	@Override
	public String toString() {
		return "Phone [imei=" + imei + ", phoneName=" + phoneName + ", modelName=" + modelName + ", process=" + process
				+ "]";
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
	public PhoneDTO createPhoneDTO(Phone ph)
    {
    	PhoneDTO dto = new PhoneDTO();
    	dto.setImei(ph.getImei());
    	dto.setModelName(ph.getModelName());
    	dto.setPhoneName(ph.getPhoneName());
    	dto.setProcess(ph.getProcess());
    	return dto;	
    }
}
