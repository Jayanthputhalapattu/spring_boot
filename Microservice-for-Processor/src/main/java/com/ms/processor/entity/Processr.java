package com.ms.processor.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity(name="processor")
public class Processr {
	@Id
   private int no;
	@Column(name = "model_name")
   private String modelName;
   private int cost;
   
   
   
public int getNo() {
	return no;
}
public void setNo(int no) {
	this.no = no;
}
public String getModelName() {
	return modelName;
}
public void setModelName(String modelName) {
	this.modelName = modelName;
}
public double getCost() {
	return cost;
}
public void setCost(int cost) {
	this.cost = cost;
}
public Processr(int no, String modelName, int cost) {
	super();
	this.no = no;
	this.modelName = modelName;
	this.cost = cost;
}
public Processr(int no) {
	super();
	this.no = no;
}
public Processr() {
	super();
}
@Override
public String toString() {
	return "Process [no=" + no + ", modelName=" + modelName + ", cost=" + cost + "]";
}
//   public ProcessDTO createProcessDTO(Processr process)
//   {
//	   ProcessDTO dto = new ProcessDTO();
//	   dto.setNo(process.getNo());
//	   dto.setModelName(process.modelName);
//	   dto.setCost(process.getCost());
//	   return dto;
//   }
}
