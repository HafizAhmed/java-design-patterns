package com.company;

import java.io.*;
public class Writer implements Producer{
    @Override
    public PrintWriter produce(Object... parameters){
        //System.out.println(parameters[1]);
        return new PrintWriter((OutputStream) parameters[0],(boolean)parameters[1]);
    }
    @Override
    public void close(){

    }
}