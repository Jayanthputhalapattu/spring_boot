package com.ms.phone.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.PropertySource;

import com.ms.phone.entity.Phone;



public class PhoneDTO {
	@NotNull(message = "{phone.id.empty}")
	private int imei;
	@NotEmpty(message = "{phone.name.empty},{inputs.not.entered}")
	private String phoneName;
	@NotEmpty(message = "{phone.model.empty}")
	private String modelName;
	private ProcessDTO process;
	private List<Integer> cameras = new ArrayList<>();
	
	public static Phone createPhoneEntity(PhoneDTO dto)
	{
		Phone ph = new Phone();
		ph.setImei(dto.getImei());
		ph.setModelName(dto.getModelName());
		ph.setPhoneName(dto.getPhoneName());
		ph.setProcessorId(dto.getProcess().getNo());
		
		return ph;
	}

	public int getImei() {
		return imei;
	}

	public String getPhoneName() {
		return phoneName;
	}

	public String getModelName() {
		return modelName;
	}

	public ProcessDTO getProcess() {
		return process;
	}

	public List<Integer> getCameras() {
		return cameras;
	}

	public void setImei(int imei) {
		this.imei = imei;
	}

	public void setPhoneName(String phoneName) {
		this.phoneName = phoneName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public void setProcess(ProcessDTO process) {
		this.process = process;
	}

	public void setCameras(List<Integer> cameras) {
		this.cameras = cameras;
	}

	public PhoneDTO(@NotNull(message = "{phone.id.empty}") int imei,
			@NotEmpty(message = "{phone.name.empty},{inputs.not.entered}") String phoneName,
			@NotEmpty(message = "{phone.model.empty}") String modelName, ProcessDTO process, List<Integer> cameras) {
		super();
		this.imei = imei;
		this.phoneName = phoneName;
		this.modelName = modelName;
		this.process = process;
		this.cameras = cameras;
	}

	public PhoneDTO() {
		super();
	}

	@Override
	public String toString() {
		return "PhoneDTO [imei=" + imei + ", phoneName=" + phoneName + ", modelName=" + modelName + ", process="
				+ process + ", cameras=" + cameras + "]";
	}

}
