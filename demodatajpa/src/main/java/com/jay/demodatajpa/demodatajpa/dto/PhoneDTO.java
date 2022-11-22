package com.jay.demodatajpa.demodatajpa.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.PropertySource;

import com.jay.demodatajpa.demodatajpa.entities.Phone;
import com.jay.demodatajpa.demodatajpa.entities.Processr;


public class PhoneDTO {
	@NotNull(message = "{phone.id.empty}")
	private int imei;

	public ProcessDTO getProcess() {
		return process;
	}

	public void setProcess(ProcessDTO process) {
		this.process = process;
	}

	@NotEmpty(message = "{phone.name.empty},{inputs.not.entered}")
	private String phoneName;
	@NotEmpty(message = "{phone.model.empty}")
	private String modelName;
	private ProcessDTO process;

	public PhoneDTO(int imei, String phoneName, String modelName) {
		super();
		this.imei = imei;
		this.phoneName = phoneName;
		this.modelName = modelName;
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

	public PhoneDTO() {
		super();
	}

//	public Phone createPhoneEntity(PhoneDTO dto) {
//		Phone ph = new Phone();
//		ph.setImei(dto.getImei());
//		ph.setModelName(dto.getModelName());
//		ph.setPhoneName(dto.getPhoneName());
////		ph.setProcess(dto.getProcess());
//		return ph;
//	}

	@Override
	public String toString() {
		return "PhoneDTO [imei=" + imei + ", phoneName=" + phoneName + ", modelName=" + modelName + ", process="
				+ process + "]";
	}

	public PhoneDTO(int imei, String phoneName, String modelName, ProcessDTO process) {
		super();
		this.imei = imei;
		this.phoneName = phoneName;
		this.modelName = modelName;
		this.process = process;
	}

}
