package com.xiaoshan;

import java.io.*;

/**
 * a simple system console printer
 */
public class Writer implements Producer {
    @Override
    public PrintWriter produce(Object... parameters) throws LoanException {
        try{
            return new PrintWriter((OutputStream) parameters[0],(boolean)parameters[1]);
        }catch (Exception e) {
            throw new LoanException(e.getMessage(),e);
        }

    }
    @Override
    public void close(){

    }
}