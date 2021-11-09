package com.company;

import java.io.*;

public class Main {
    public static void conter(String a, int b) {
        System.out.println(a);
        System.out.println(b);

    }

    public static void inter(Object ...input) {
        conter((String)input[0],(int)input[1]);
    }
    public static void main(String args[]) throws Exception {
        Writer w = new Writer();
        //User u = new User();
        //u.setParaneter(System.out,true);
        Lender.lend(w,new Consumer<PrintWriter>(){
            Object[] parameters = new Object[]{System.out,true};
            public void consume(PrintWriter writer) {
                writer.println("Printer Test");
            }
            public Object[] getParameter(){
                return parameters;
            }
        });

        //User u = new User();
        //u.setParaneter(System.out,true);
        Lender.lend(w,new Consumer<PrintWriter>(){
            Object[] parameters = new Object[]{System.out,true};
            public void consume(PrintWriter writer) throws Exception {
                throw new Exception();
            }
            public Object[] getParameter(){
                return parameters;
            }
        });
    }
}
