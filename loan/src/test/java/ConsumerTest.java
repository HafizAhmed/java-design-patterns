package com.xiaoshan;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
/**
 * Test the consumer interface by consumer objects
 */
public class ConsumerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private Consumer<PrintWriter> printWriterConsumer;
    private Consumer<BufferedWriter> fileWriteConsumer;
    private Consumer<BufferedReader> fileReadConsumer;
    String fileName = "test.txt";
    String testString = "Hello World!";
    /**
     * set up the streams that can be used to test the output from the console and consumers
     */
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        printWriterConsumer = new Consumer<PrintWriter>(){
            Object[] parameters = new Object[]{System.out,true};
            public void consume(PrintWriter writer) {
                writer.println("Printer Test");
            }
            public Object[] getParameter(){
                return parameters;
            }
        };
        fileWriteConsumer = new Consumer<BufferedWriter>(){
            Object[] parameters = new Object[]{fileName,false};
            public void consume(BufferedWriter writer) throws LoanException {
                try {
                    writer.write(testString);
                } catch (Exception e) {
                    throw new LoanException(e.getMessage(),e);
                }
            }
            public Object[] getParameter(){
                return parameters;
            }
        };
        fileReadConsumer = new Consumer<BufferedReader>(){
            Object[] parameters = new Object[]{fileName};
            public void consume(BufferedReader reader) throws LoanException {
                try{
                    System.out.println(reader.readLine());
                } catch (Exception e) {
                    throw new LoanException(e.getMessage(),e);
                }
            }
            public Object[] getParameter(){
                return parameters;
            }
        };
    }

    /**
     * restore the streams and delete the test files
     */
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        File existFile = new File(fileName);
        existFile.delete();
    }

    /**
     * test getParameters of PrintWriter Consumer
     */
    @Test
    public void printWriterConsumerGetParameterTest() {
        try{
            assertEquals(System.out,(OutputStream)printWriterConsumer.getParameter()[0]);
            assertEquals(true,(boolean)printWriterConsumer.getParameter()[1]);
        } catch (Exception e){
            assertTrue(false);
        }
    }
    /**
     * test consume of PrintWriter Consumer
     */
    @Test
    public void printWriterConsumerConsumeTest() {

        try{
            PrintWriter pw = new PrintWriter((OutputStream)printWriterConsumer.getParameter()[0],(boolean)printWriterConsumer.getParameter()[1]);
            pw.println(testString);
            assertEquals(testString + "\r\n",outContent.toString());
        } catch (Exception e){
            assertTrue(false);
        }
    }
    /**
     * test getParameters of fileWrite Consumer
     */
    @Test
    public void fileWriteConsumerGetParameterTest() {
        try{
            assertEquals(fileName,(String)fileWriteConsumer.getParameter()[0]);
            assertEquals(false,(boolean)fileWriteConsumer.getParameter()[1]);
        } catch (Exception e){
            assertTrue(false);
        }
    }
    /**
     * test consume of fileWrite Consumer
     */
    @Test
    public void fileWriteConsumerConsumeTest() {

        try{
            File file = new File((String)fileWriteConsumer.getParameter()[0]);
            FileWriter fw = new FileWriter(file,(boolean)fileWriteConsumer.getParameter()[1]);
            BufferedWriter bw = new BufferedWriter(fw);
            fileWriteConsumer.consume(bw);
            bw.close();
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            assertEquals(testString,br.readLine());
            br.close();
        } catch (Exception e){
            assertTrue(false);
        }
    }
    /**
     * test getParameters of fileRead Consumer
     */
    @Test
    public void fileReadConsumerGetParameterTest() {
        try{
            assertEquals(fileName,(String)fileReadConsumer.getParameter()[0]);
        } catch (Exception e){
            assertTrue(false);
        }
    }
    /**
     * test consume of fileRead Consumer
     */
    @Test
    public void fileReadConsumerConsumeTest() {
        try{
            File file = new File((String)fileWriteConsumer.getParameter()[0]);
            FileWriter fw = new FileWriter(file,false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(testString);
            bw.close();
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            fileReadConsumer.consume(br);
            assertEquals(testString+"\r\n",outContent.toString());
            br.close();
        } catch (Exception e){
            assertTrue(false);
        }
    }
}
