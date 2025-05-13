package com.r2s.findInternship.infracstructure.exception.exception_v1;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
	private String entity;
	private String field;
	private String value;

	public ResourceNotFoundException(String msg) {
		super(msg);
	}

	@Override
	public String toString() {
		return String.format("%s not found with %s: %s", entity, field, value);
	}
}
