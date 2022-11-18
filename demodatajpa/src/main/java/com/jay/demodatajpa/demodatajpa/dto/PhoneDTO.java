package com.jay.demodatajpa.demodatajpa.dto;

import com.jay.demodatajpa.demodatajpa.entities.Phone;
import com.jay.demodatajpa.demodatajpa.entities.Processr;


public class PhoneDTO {
   private int imei;
   public Processr getProcess() {
	return process;
}
public void setProcess(Processr process) {
	this.process = process;
}
private String phoneName;
   private String modelName;
   private Processr process;
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
  public Phone createPhoneEntity(PhoneDTO dto)
  {
	  Phone ph = new Phone();
	  ph.setImei(dto.getImei());
	  ph.setModelName(dto.getModelName());
	  ph.setPhoneName(dto.getPhoneName());
	  ph.setProcess(dto.getProcess());
	  return ph;
  }

@Override
public String toString() {
	return "PhoneDTO [imei=" + imei + ", phoneName=" + phoneName + ", modelName=" + modelName + ", process=" + process
			+ "]";
}
public PhoneDTO(int imei, String phoneName, String modelName, Processr process) {
	super();
	this.imei = imei;
	this.phoneName = phoneName;
	this.modelName = modelName;
	this.process = process;
}
  
}
