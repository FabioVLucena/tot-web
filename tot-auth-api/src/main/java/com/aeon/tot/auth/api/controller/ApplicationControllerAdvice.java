package com.aeon.tot.auth.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aeon.tot.auth.api.dto.ErrorResponse;
import com.aeon.tot.auth.api.exception.WarningException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse treatmentMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		List<String> errorStrList = ex.getBindingResult()
				.getAllErrors()
				.stream()
				.map(errorStr -> errorStr.getDefaultMessage())
				.collect(Collectors.toList());
		
		return ErrorResponse.convertList(errorStrList);
	}
	
	@ExceptionHandler(WarningException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse treatmentWarningException(WarningException ex) {
		String errorStr = ex.getMessage();
		
		return ErrorResponse.convert(errorStr);
	}
}
