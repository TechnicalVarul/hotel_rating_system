package com.hrs.hotel.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8814594381741125762L;

	@SuppressWarnings("unused")
	private final String resourceName;
	
	
	@SuppressWarnings("unused")
	private final String fieldName;
	
	@SuppressWarnings("unused")
	private final String fieldValue;
	
	public ResourceNotFoundException(String resourceName, String fieldName,String fieldValue){
		super(String.format("%s is not found with %s: %s.", resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		
	}
	
}
