package com.ms.processor.exceptionHandling;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AllExceptionsHandler {
 
	

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> MethodArgumentNotValidException
	(MethodArgumentNotValidException e)
	{
		ErrorMessage errmsg = new ErrorMessage();
		errmsg.setErrorCode(HttpStatus.BAD_REQUEST.value());
//		errmsg.setMessage(e.getAllErrors().stream().filter("",(e1,e2)->e1 +e2));
		errmsg.setMessage(e.getAllErrors().stream()
				.map(ObjectError::getDefaultMessage)
				.reduce("",(e1,e2)->e1+ " ,  "+e2)
				);
		
		return ResponseEntity.status(errmsg.getErrorCode()).body(errmsg);
		
		
	}
	
	@ExceptionHandler(ProcessorNotFoundException.class)
	public ResponseEntity<ErrorMessage> ProcessorNotFoundException(ProcessorNotFoundException e)
	{
		ErrorMessage errmsg = new ErrorMessage();
		errmsg.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errmsg.setMessage(e.getMessage());
		return ResponseEntity.status(errmsg.getErrorCode()).body(errmsg);
		
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> GeneralException(Exception e)
	{
		ErrorMessage errmsg = new ErrorMessage();
		errmsg.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errmsg.setMessage(e.getMessage());
		
		return ResponseEntity.status(errmsg.getErrorCode()).body(errmsg);
	}
}
