package com.jay.demodatajpa.demodatajpa.ExceptionHandle;

public class ProcessorNotFoundException extends Exception{
	private static final long serialVersionUID = 2L;
	
	public ProcessorNotFoundException(String message) {
		super(message);
	}
}
