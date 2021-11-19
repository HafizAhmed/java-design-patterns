package com.company;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;


public class PrintWriterTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void usePrintWriter() throws Exception {
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
        //assertEquals("Printer Test\r\n",outContent.toString());

    }

    @Test
    public void exceptionPrintWriter() throws Exception {
        Writer w = new Writer();
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
        //System.out.println(outContent.toString());
        assertEquals("java.lang.Exception\r\n",outContent.toString());
    }


}

//
