package com.sample.exception;

import org.springframework.stereotype.Component;

public class ErrorResponse {

	private String errorMessage;
	private String errorDescription;

	public ErrorResponse(String errorMessage, String errorDescription) {
		super();
		this.errorMessage = errorMessage;
		this.errorDescription = errorDescription;
	}

	public ErrorResponse() {
		super();
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the errorDescription
	 */
	public String getErrorDescription() {
		return errorDescription;
	}

	/**
	 * @param errorDescription
	 *            the errorDescription to set
	 */
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ErrorResponse [");
		if (errorMessage != null) {
			builder.append("errorMessage=");
			builder.append(errorMessage);
			builder.append(", ");
		}
		if (errorDescription != null) {
			builder.append("errorDescription=");
			builder.append(errorDescription);
		}
		builder.append("]");
		return builder.toString();
	}

}
