package com.newlecmineursprj.advice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResult extends Exception{
    private Exception exception;
    private String message;
    private Integer code;
    private String viewName;

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public ErrorResult(Exception exception, String message, Integer code, String viewName) {
        this.exception = exception;
        this.message = message;
        this.code = code;
        this.viewName = viewName;
    }
}
