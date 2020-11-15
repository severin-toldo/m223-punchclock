package com.stoldo.m223_punchclock.model.enums;

public enum ErrorCode {
	E1000("Unknown Error!"),
	E1001("Failed to parse JSON!"),
	E1002("Old password does not match new password!"),
	E1003("Cannot delete category, there are still related time entries!"),
	E1004("Cannot delete user, there are still related time entries!"),
	E1005("Token expired!"),
	E1006("Entity not found!");
	
	private String message;

	private ErrorCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
