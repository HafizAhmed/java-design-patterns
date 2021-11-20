package com.xiaoshan;

/**
 * a generalized exception class that is used for generalizing
 * different types of exceptions to one exception
 */
public class LoanException extends Exception {
    /**
     * the constructor of the LoanException
     * @param message the actual exception message
     * @param cause the actual exception cause
     */
    public LoanException(String message, Throwable cause) {
        super(message, cause);
    }
}
