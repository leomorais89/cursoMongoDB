package com.example.mongo.services.exception;

public class SaveException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SaveException(String msg) {
		super(msg);
	}
}
