package com.kmust.recruitment.service.ex;

/**
 * 购物车数据不存在
 */
public class HobbyNotFoundException extends ServiceException {

	private static final long serialVersionUID = -290753401450618624L;

	public HobbyNotFoundException() {
		super();
	}

	public HobbyNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public HobbyNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public HobbyNotFoundException(String message) {
		super(message);
	}

	public HobbyNotFoundException(Throwable cause) {
		super(cause);
	}

}
