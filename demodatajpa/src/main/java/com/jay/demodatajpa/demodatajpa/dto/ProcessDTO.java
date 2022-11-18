package com.jay.demodatajpa.demodatajpa.dto;
import com.jay.demodatajpa.demodatajpa.entities.Processr;

public class ProcessDTO {
  private int no;
  private String modelName;
  private double cost;
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
public void setCost(double cost) {
	this.cost = cost;
}
public ProcessDTO(int no, String modelName, double cost) {
	super();
	this.no = no;
	this.modelName = modelName;
	this.cost = cost;
}
public ProcessDTO() {
	super();
}
@Override
public String toString() {
	return "ProcessDTO [no=" + no + ", modelName=" + modelName + ", cost=" + cost + "]";
}
  
 public Processr createProcessEntity(ProcessDTO prdto)
 {
	 Processr process = new Processr();
	 process.setNo(prdto.getNo());
	 process.setModelName(prdto.getModelName());
	 process.setCost(prdto.getCost());
	 return process;
	 
 }
}
