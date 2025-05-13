package com.r2s.findInternship.infracstructure.exception.exception_v1;

import java.util.Map;

import lombok.Getter;

@Getter
public class InternalServerErrorException extends RuntimeException {
	private Map<String, String> errors;

	public InternalServerErrorException(String msg) {
		super(msg);
	}

	public InternalServerErrorException(Map<String, String> params) {
		this.errors = params;
	}

}
