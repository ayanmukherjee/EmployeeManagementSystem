package com.socgen.ems.common;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import com.socgen.ems.BaseTest;



public class ErrorControllerAdviceTest extends BaseTest {
	
	private ErrorControllerAdvice errorControllerAdvice = new ErrorControllerAdvice();
	
	@Test
	public void handleMethodArgumentNotValid(){
		MethodArgumentNotValidException mockMethodArgumentNotValidException = Mockito.mock(MethodArgumentNotValidException.class);
		HttpHeaders mockHeaders = Mockito.mock(HttpHeaders.class);
		HttpStatus mockStatus = HttpStatus.BAD_REQUEST;
		WebRequest mockRequest = Mockito.mock(WebRequest.class);
		BindingResult mockBindingResult = Mockito.mock(BindingResult.class);

		ObjectError mockError1 = new FieldError("object1", "error1", "message1");
		ObjectError mockError2 = new FieldError("object2", "error2", "message2");;
		List<ObjectError> mockErrors = Arrays.asList(mockError1, mockError2);
		
		Mockito.when(mockMethodArgumentNotValidException.getBindingResult()).thenReturn(mockBindingResult);
		Mockito.when(mockBindingResult.getAllErrors()).thenReturn(mockErrors);
		
		ResponseEntity<Object> responseEntity = errorControllerAdvice.handleMethodArgumentNotValid(mockMethodArgumentNotValidException, mockHeaders, mockStatus, mockRequest);
		Assert.assertEquals("{error1=message1, error2=message2}", responseEntity.getBody().toString());
		Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	
	}
	
	@Test
	public void handleResourceNotFoundException(){
		
		Assert.assertEquals("Message 1", errorControllerAdvice.handleResourceNotFoundException(new ResourceNotFoundException("Message 1")));
	
	}
	


}
