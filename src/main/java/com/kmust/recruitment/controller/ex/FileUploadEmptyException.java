package com.kmust.recruitment.controller.ex;

public class FileUploadEmptyException extends FileUploadException {
    public FileUploadEmptyException() {
        super();
    }

    public FileUploadEmptyException(String message) {
        super(message);
    }

    public FileUploadEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadEmptyException(Throwable cause) {
        super(cause);
    }

    protected FileUploadEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
