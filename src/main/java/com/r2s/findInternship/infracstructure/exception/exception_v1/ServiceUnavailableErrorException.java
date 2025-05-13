package com.r2s.findInternship.infracstructure.exception.exception_v1;

import java.util.Map;

import lombok.Getter;

@Getter
public class ServiceUnavailableErrorException extends RuntimeException {

	private Map<String, String> errors;

	public ServiceUnavailableErrorException(String msg) {
		super(msg);
	}

	public ServiceUnavailableErrorException(Map<String, String> params) {
		this.errors = params;
	}

}
