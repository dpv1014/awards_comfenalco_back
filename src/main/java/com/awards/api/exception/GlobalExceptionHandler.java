package com.awards.api.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.awards.api.utils.ErrorInfo;


@RestControllerAdvice(basePackages = {"com.awards.api.controller"})
public class GlobalExceptionHandler {
	private ResponseEntity<ErrorInfo> error(final Exception exception, final HttpStatus httpStatus,
			HttpServletRequest request) {
		return new ResponseEntity<>(new ErrorInfo(exception, request.getRequestURI()), httpStatus);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorInfo> handleRuntimeException(HttpServletRequest request, final RuntimeException e) {
		return error(e, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(PeopleNotFoundException.class)
	public ResponseEntity<ErrorInfo> handleUserNotPresentException(HttpServletRequest request,
			PeopleNotFoundException e) {
		return error(e, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(AwardNotFoundException.class)
	public ResponseEntity<ErrorInfo> handleUserNotPresentException(HttpServletRequest request,
			AwardNotFoundException e) {
		return error(e, HttpStatus.BAD_REQUEST, request);
	}
}