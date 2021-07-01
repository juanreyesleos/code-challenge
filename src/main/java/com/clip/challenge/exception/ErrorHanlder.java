package com.clip.challenge.exception;

import java.io.FileNotFoundException;

import org.h2.jdbc.JdbcSQLSyntaxErrorException;
import org.hibernate.QueryException;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import ch.qos.logback.classic.Logger;

@ControllerAdvice
public class ErrorHanlder {
    protected Logger logger = (Logger) LoggerFactory.getLogger(ErrorHanlder.class);

    
    @ExceptionHandler({NoHandlerFoundException.class})
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public CommonException notFoundException(Exception ex) {
		logger.error(ex.getMessage());
		return new CommonException("Not Found Exception - "+ ex.getMessage(), HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value());
	}
    
    @ExceptionHandler({JdbcSQLSyntaxErrorException.class, QueryException.class,IllegalStateException.class,
    	NullPointerException.class,ArrayIndexOutOfBoundsException.class,IllegalStateException.class
    	,ArithmeticException.class,FileNotFoundException.class})
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public CommonException jdbcSQLSyntaxErrorException(Exception ex) {
		logger.error(ex.getMessage());
		return new CommonException("There was an error during the server process", HttpStatus.INTERNAL_SERVER_ERROR.name(), HttpStatus.INTERNAL_SERVER_ERROR.value());
	}
	

	@ExceptionHandler({MethodArgumentNotValidException.class,HttpMessageNotReadableException.class,
		HttpRequestMethodNotSupportedException.class})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public CommonException methodArgumentNotValidException(Exception ex) {
		logger.error(ex.getMessage());		
		StringBuilder sb = new StringBuilder();
		
		if (ex instanceof MethodArgumentNotValidException) {
			BindingResult result = ((BindException) ex).getBindingResult();			
			for (FieldError error : result.getFieldErrors()) {
				StringBuilder se = new StringBuilder();
				se.append("Field [");
				se.append(error.getField());
				se.append("] ");
				se.append(error.getDefaultMessage());
				se.append(" - ");
				sb.append(se.toString());
			}
		}
		if (sb.toString().isEmpty()) {
			sb.append(ex.getMessage());
		}
		return new CommonException(sb.toString(), HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value());
	}

}
