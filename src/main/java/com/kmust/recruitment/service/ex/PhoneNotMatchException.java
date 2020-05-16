package com.kmust.recruitment.service.ex;

/**
 * 用户数据不存在
 */
public class PhoneNotMatchException extends ServiceException {


	private static final long serialVersionUID = -416694517353762006L;

	public PhoneNotMatchException() {
		super();
	}

	public PhoneNotMatchException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PhoneNotMatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public PhoneNotMatchException(String message) {
		super(message);
	}

	public PhoneNotMatchException(Throwable cause) {
		super(cause);
	}

}
