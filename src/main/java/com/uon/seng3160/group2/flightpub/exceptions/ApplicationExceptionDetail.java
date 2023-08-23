package com.uon.seng3160.group2.flightpub.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApplicationExceptionDetail {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date timestamp;
	private int code;
	private String status;
	private String message;
	private String stackTrace;
	private Object data;
	
	
	public ApplicationExceptionDetail() {
		this.timestamp = new Date();
	}
	
	public ApplicationExceptionDetail(HttpStatus httpStatus, String message) {
		this();
		this.code = httpStatus.value();
		this.status = httpStatus.name();
		this.message = message;
	}
	
	public ApplicationExceptionDetail(HttpStatus httpStatus, String message, String stackTrace) {
		this(httpStatus, message);
		this.stackTrace = stackTrace;
	}
	
	public ApplicationExceptionDetail(HttpStatus httpStatus, String message, String stackTrace, Object data) {
		this(httpStatus, message, stackTrace);
		this.data = data;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
