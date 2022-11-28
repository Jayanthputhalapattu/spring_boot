package com.ms.phone.exceptionHanlding;

public class PhoneNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public PhoneNotFoundException(String errors)
	{
		super(errors);
	}

}
