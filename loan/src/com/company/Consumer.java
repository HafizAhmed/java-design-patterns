package com.company;

public interface Consumer<T> {

    void consume(T resource) throws Exception;
//    void setParaneter(Object...parameters) throws Exception;
    Object[] getParameter() throws Exception;

}
