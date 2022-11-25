package com.ms.processor.exceptionHandling;

public class ProcessorNotFoundException extends Exception{
	private static final long serialVersionUID = 2L;
	
	public ProcessorNotFoundException(String message) {
		super(message);
	}
}
