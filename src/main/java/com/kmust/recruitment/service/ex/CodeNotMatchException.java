package com.kmust.recruitment.service.ex;

/**
 * 用户数据不存在
 */
public class CodeNotMatchException extends ServiceException {

	private static final long serialVersionUID = 7602631148264113908L;

	public CodeNotMatchException() {
		super();
	}

	public CodeNotMatchException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CodeNotMatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public CodeNotMatchException(String message) {
		super(message);
	}

	public CodeNotMatchException(Throwable cause) {
		super(cause);
	}

}
