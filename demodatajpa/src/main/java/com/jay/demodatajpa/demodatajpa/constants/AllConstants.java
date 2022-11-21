package com.jay.demodatajpa.demodatajpa.constants;

public enum AllConstants {
	
   PHONE_NOT_FOUND("phone.not.found"),
   PROCESSOR_NOT_FOUND("processor.not.found"),
   GENERAL_EXCEPTION("general.exception");
	private String value;
   AllConstants(String value)
   {
	   this.value = value;
   }
   @Override
   public String toString()
   {
	   return value;
   }
}
