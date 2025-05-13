package com.r2s.findInternship.infracstructure.exception.exception_v2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class ExceptionCustomV2 extends RuntimeException {
	private final Object errors;
	private final String trace;
	private final int status;

	public ExceptionCustomV2(String msg, Object errors, String trace, int status) {
		super(msg);
		this.errors = errors;
		this.trace = trace;
		this.status = status;
	}

	public String getTrace() {
		return trace;
	}
}
