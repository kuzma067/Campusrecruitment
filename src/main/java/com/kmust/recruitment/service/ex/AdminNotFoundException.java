package com.kmust.recruitment.service.ex;

/**
 * 管理员数据不存在
 */
public class AdminNotFoundException extends ServiceException {

	private static final long serialVersionUID = -2111882581918102362L;

	public AdminNotFoundException() {
		super();
	}

	public AdminNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AdminNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public AdminNotFoundException(String message) {
		super(message);
	}

	public AdminNotFoundException(Throwable cause) {
		super(cause);
	}

}
