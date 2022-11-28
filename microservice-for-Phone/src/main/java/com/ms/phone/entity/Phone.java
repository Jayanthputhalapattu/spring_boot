package com.ms.phone.entity;

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
import com.ms.phone.dto.PhoneDTO;
import com.ms.phone.dto.ProcessDTO;



@Entity
public class Phone {
	@Id
    private int imei;
//	@Column(name = "phone_number")
	@NotEmpty(message ="{phone.not.empty}")
    private String phoneName;
	@Column(name = "model_name")
    private String modelName;
	@Column(name="processor_id",nullable = false)
	private Integer processorId;
	
	public Integer getProcessorId() {
		return processorId;
	}

	public void setProcessorId(Integer processorId) {
		this.processorId = processorId;
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
    
    public Phone(int imei,  String phoneName, String modelName,
			Integer processorId, List<Camera> cameras) {
		super();
		this.imei = imei;
		this.phoneName = phoneName;
		this.modelName = modelName;
		this.processorId = processorId;
	
	}

	public Phone(int imei, String phoneName, String modelName) {
		super();
		this.imei = imei;
		this.phoneName = phoneName;
		this.modelName = modelName;
	}


	public Phone(int imei, @NotEmpty(message = "{phone.not.empty}") String phoneName, String modelName,
			Integer processorId) {
		super();
		this.imei = imei;
		this.phoneName = phoneName;
		this.modelName = modelName;
		this.processorId = processorId;
	}

	public static PhoneDTO createPhoneDTO(Phone ph) {
		PhoneDTO dto = new PhoneDTO();
		dto.setImei(ph.getImei());
		dto.setModelName(ph.getModelName());
		dto.setPhoneName(ph.getPhoneName());
//		dto.setProcess(null);
		ProcessDTO process = new ProcessDTO();
		process.setNo(ph.getProcessorId());
		dto.setProcess(process);
		
		return dto;
		
	}
}
