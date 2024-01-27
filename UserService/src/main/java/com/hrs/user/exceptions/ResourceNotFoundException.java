package com.hrs.user.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3918270471165501947L;
	
	
	@SuppressWarnings("unused")
	private final String resourceName;
	
	@SuppressWarnings("unused")
	private final String fieldName;
	
	@SuppressWarnings("unused")
	private final String fieldValue;
	
	
	public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
		super(String.format("%s not found with %s: %s.",resourceName,fieldName,fieldValue ));
		this.fieldName = fieldName;
		this.resourceName = resourceName;
		this.fieldValue = fieldValue;
	}


}
