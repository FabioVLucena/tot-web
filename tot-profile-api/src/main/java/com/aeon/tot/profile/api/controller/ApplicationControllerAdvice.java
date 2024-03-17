package com.aeon.tot.profile.api.controller;

import java.time.format.DateTimeParseException;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aeon.tot.profile.api.dto.ErrorResponse;
import com.aeon.tot.profile.api.exception.WarningException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse treatmentMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		Set<String> errorStrList = ex.getBindingResult()
				.getAllErrors()
				.stream()
				.map(errorStr -> errorStr.getDefaultMessage())
				.collect(Collectors.toSet());
		
		return ErrorResponse.convertList(errorStrList);
	}
	
	@ExceptionHandler(WarningException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse treatmentWarningException(WarningException ex) {
		String errorStr = ex.getMessage();
		
		return ErrorResponse.convert(errorStr);
	}

	@ExceptionHandler(DateTimeParseException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse treatmentDateTimeParseException(DateTimeParseException ex) {
		String errorStr = ex.getMessage();
		
		return ErrorResponse.convert(errorStr);
	}
}
