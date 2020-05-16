package com.kmust.recruitment.service.ex;

/**
 * 该用户能上传的项目数量达到上限
 */
public class ProjectSizeLimitException extends ServiceException {

	private static final long serialVersionUID = 2980764691076197110L;

	public ProjectSizeLimitException() {
		super();
	}

	public ProjectSizeLimitException(String message, Throwable cause, boolean enableSuppression,
                                     boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProjectSizeLimitException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProjectSizeLimitException(String message) {
		super(message);
	}

	public ProjectSizeLimitException(Throwable cause) {
		super(cause);
	}


}
