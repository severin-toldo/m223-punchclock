package com.stoldo.m223_punchclock.model.enums;


public enum ErrorCode {
	E1000("Unknown Error!"),
	E1001("Failed to parse JSON!"),
	E1002("Old password does not match new password!"),
	E1003("Cannot delete category, there are still related time entries!"),
	E1004("Cannot delete user, there are still related time entries!"),
	E1005("Token expired!"),
	E1006("Entity not found!"),
	E1007("E-Mail or Password is wrong!"),
	E1008("Invalid E-Mail"),
	E1009("Checkin cannot be before checkout!");
	
	private String message;

	private ErrorCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
