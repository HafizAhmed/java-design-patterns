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
 * Use the producer object to test the producer interface
 */
public class ProducerTest {
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
     * restore the streams and delete the test files
     */
    @After
    public void restoreStreams() {
        String fileName = "test.txt";
        System.setOut(originalOut);
        System.setErr(originalErr);
        File existFile = new File(fileName);
        existFile.delete();
    }

    /**
     * test the produce of PrintWriter
     */
    @Test
    public void PrintWriterProduceTest() {
        String testString = "Hello World!";
        try{
            Writer w = new Writer();
            PrintWriter pw = w.produce(System.out,true);
            assertNotNull(pw);
            pw.println(testString);
            assertEquals(testString+"\r\n",outContent.toString());
        } catch (Exception e){
            assertTrue(false);
        }
    }
    /**
     * test produce of BufferedWriter producer
     */
    @Test
    public void FileWriteProduceTest() {
        String testString = "Hello World!";
        String fileName = "test.txt";
        try{
            FileWrite w = new FileWrite();
            BufferedWriter bw = w.produce(fileName,false);
            assertNotNull(bw);
            bw.write(testString);
            w.close();
        } catch (Exception e){
            assertTrue(false);
        }
    }
    /**
     * test produce of BufferedReader producer
     */
    @Test
    public void FileReadProduceTest() {
        String testString = "Hello World!";
        String fileName = "test.txt";
        try{
            FileWrite w = new FileWrite();
            BufferedWriter bw = w.produce(fileName,false);
            bw.write(testString);
            bw.close();

            FileRead r = new FileRead();
            BufferedReader br = r.produce(fileName);
            assertNotNull(br);
            assertEquals(testString,br.readLine());
            r.close();
        } catch (Exception e){
            assertTrue(false);
        }
    }
    /**
     * test close of BufferedWriter producer
     */
    @Test (expected = IOException.class)
    public void FileWriteCloseTest() throws LoanException,IOException{
        String fileName = "test.txt";
        String testString = "Hello World!";
        FileWrite w = new FileWrite();
        BufferedWriter bw = w.produce(fileName,false);
        assertNotNull(bw);
        w.close();
        bw.write(testString);
    }
    /**
     * test close of BufferedReader producer
     */
    @Test (expected = IOException.class)
    public void FileReadCloseTest() throws LoanException,IOException{
        String fileName = "test.txt";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("sss");
        bw.close();
        FileRead r = new FileRead();
        BufferedReader br = r.produce(fileName);
        assertNotNull(br);
        file.delete();
        r.close();
        br.readLine();
    }
}