package com.aeon.tot.auth.api.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aeon.tot.auth.api.dto.ErrorResponse;
import com.aeon.tot.auth.api.exception.WarningException;

import io.jsonwebtoken.security.SignatureException;

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

	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse treatmentUsernameNotFoundException(UsernameNotFoundException ex) {
		String errorStr = ex.getMessage();
		
		return ErrorResponse.convert(errorStr);
	}

	@ExceptionHandler(SignatureException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse treatmentSignatureException(SignatureException ex) {
		String errorStr = ex.getMessage();
		
		return ErrorResponse.convert(errorStr);
	}
}
