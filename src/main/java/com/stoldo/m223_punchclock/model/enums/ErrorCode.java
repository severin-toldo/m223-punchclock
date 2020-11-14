package com.stoldo.m223_punchclock.model.enums;

public enum ErrorCode {
	E1000("Unknown Error!"),
	E1001("Failed to parse JSON!"),
	E1002("Old password does not match new password!");
	
	private String message;

	private ErrorCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
