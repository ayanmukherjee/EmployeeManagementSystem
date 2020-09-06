package com.socgen.ems.common;

public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 517879073389905497L;

	public ResourceNotFoundException(String message){
		super(message);
	}

}
