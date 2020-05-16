package com.kmust.recruitment.service.ex;

/**
 * 评论数据不存在
 */
public class CommentNotFoundException extends ServiceException {

	private static final long serialVersionUID = -3688349481224653582L;

	public CommentNotFoundException() {
		super();
	}

	public CommentNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                    boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CommentNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommentNotFoundException(String message) {
		super(message);
	}

	public CommentNotFoundException(Throwable cause) {
		super(cause);
	}
	
}
