package com.aeon.tot.auth.api.dto;

import java.util.Arrays;
import java.util.List;

public record ErrorResponse(List<String> error) {

	public static ErrorResponse convert(String error) {
		return new ErrorResponse(Arrays.asList(error));
	}

	public static ErrorResponse convertList(List<String> errorList) {
		return new ErrorResponse(errorList);
	}
}
