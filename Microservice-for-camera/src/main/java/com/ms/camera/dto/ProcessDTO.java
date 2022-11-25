package com.ms.camera.dto;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class ProcessDTO {
	@NotNull
  private int no;
	@NotEmpty(message = "{processor_name_empty}")
  private String modelName;
	@NotNull
	@Min(1)
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
public void setCost( int cost) {
	this.cost = cost;
}
public ProcessDTO(int no, String modelName,int cost) {
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
  
// public Processr createProcessEntity(ProcessDTO prdto)
// {
//	 Processr process = new Processr();
//	 process.setNo(prdto.getNo());
//	 process.setModelName(prdto.getModelName());
//	 process.setCost(prdto.getCost());
//	 return process;
//	 
// }
}
