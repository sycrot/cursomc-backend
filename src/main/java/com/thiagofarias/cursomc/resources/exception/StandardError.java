package com.thiagofarias.cursomc.resources.exception;

import java.io.Serializable;

public class StandardError implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String error;
	private Long timeStamp;
	private String message;
	private String path;
	
	public StandardError(Integer status, String error, Long timeStamp, String message, String path) {
		super();
		this.status = status;
		this.error = error;
		this.timeStamp = timeStamp;
		this.message = message;
		this.path = path;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	
	
}
