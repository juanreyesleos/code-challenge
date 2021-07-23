package com.clip.challenge.exception;

public class CommonException {

	private String messgae;

	private String status;

	private int statusCode;

	public CommonException(String messsage, String status, int statusCode) {
		this.messgae = messsage;
		this.status = status;
		this.statusCode = statusCode;

	}

	public String getMessgae() {
		return messgae;
	}

	public void setMessgae(String messgae) {
		this.messgae = messgae;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
