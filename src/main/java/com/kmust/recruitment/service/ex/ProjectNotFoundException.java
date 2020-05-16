package com.kmust.recruitment.service.ex;

/**
 * 项目数据不存在
 */
public class ProjectNotFoundException extends ServiceException {

	private static final long serialVersionUID = 4234461510773896049L;

	public ProjectNotFoundException() {
		super();
	}

	public ProjectNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                    boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProjectNotFoundException(String message) {
		super(message);
	}

	public ProjectNotFoundException(Throwable cause) {
		super(cause);
	}

}
