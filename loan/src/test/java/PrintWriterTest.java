package com.xiaoshan;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

/**
 * Use the system console writer to test the loan pattern
 */
public class PrintWriterTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    /**
     * set up the streams that can be used to test the output from the console
     */
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    /**
     * restore the streams
     */
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    /**
     * test whether the loan pattern works or not
     */
    @Test
    public void usePrintWriter() {
        try{
            Writer w = new Writer();
            LenderUtils.lend(w,new Consumer<PrintWriter>(){
                Object[] parameters = new Object[]{System.out,true};
                public void consume(PrintWriter writer) {
                    writer.println("Printer Test");
                }
                public Object[] getParameter(){
                    return parameters;
                }
            });
            assertEquals("Printer Test\r\n",outContent.toString());
        } catch (Exception e){
            assertTrue(false);
        }


    }
    /**
     * test whether the function throws correct exception when something
     * goes wrong
     * @throws LoanException the generalized exception
     */
    @Test (expected = LoanException.class)
    public void exceptionPrintWriter() throws LoanException{
        Writer w = new Writer();
        LenderUtils.lend(w,new Consumer<PrintWriter>(){
            Object[] parameters = new Object[]{"asd",true};
            public void consume(PrintWriter writer){
            }
            public Object[] getParameter(){
                return parameters;
            }
        });
    }



}

//
