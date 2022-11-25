package com.ms.camera.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.PropertySource;




public class PhoneDTO {
	@NotNull(message = "{phone.id.empty}")
	private int imei;
	@NotEmpty(message = "{phone.name.empty},{inputs.not.entered}")
	private String phoneName;
	@NotEmpty(message = "{phone.model.empty}")
	private String modelName;
	private ProcessDTO process;
	private List<CameraDTO> cameras = new ArrayList<>();
	
	
	public List<CameraDTO> getCameras() {
		return cameras;
	}

	public void setCameras(List<CameraDTO> cameras) {
		this.cameras = cameras;
	}

	public ProcessDTO getProcess() {
		return process;
	}

	public void setProcess(ProcessDTO process) {
		this.process = process;
	}


	public PhoneDTO() {
		super();
	}


	public PhoneDTO(int imei, String phoneName, String modelName, ProcessDTO process) {
		super();
		this.imei = imei;
		this.phoneName = phoneName;
		this.modelName = modelName;
		this.process = process;
	}
	public PhoneDTO(int imei, String phoneName, String modelName) {
		super();
		this.imei = imei;
		this.phoneName = phoneName;
		this.modelName = modelName;
	}

	public PhoneDTO( int imei,
		String phoneName,
			 String modelName, ProcessDTO process, List<CameraDTO> cameras) {
		super();
		this.imei = imei;
		this.phoneName = phoneName;
		this.modelName = modelName;
		this.process = process;
		this.cameras = cameras;
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

	@Override
	public String toString() {
		return "PhoneDTO [imei=" + imei + ", phoneName=" + phoneName + ", modelName=" + modelName + ", process="
				+ process + ", cameras=" + cameras + "]";
	}
 public void addCamera(CameraDTO dto)
 {
	 cameras.add(dto);
 }
	

}
