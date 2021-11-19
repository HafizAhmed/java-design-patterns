package com.xiaoshan;

import org.junit.After;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * A test for BufferedWriter and BufferedReader
 */
public class BufferedReaderWriterTest {
    /**
     * delete the test files
     */
    @After
    public void deleteTestFiles() {
        String fileName = "test.txt";
        File existFile = new File(fileName);
        existFile.delete();
    }

    /**
     * test write/append of BufferedWriter and read of BufferedReader in
     * loan pattern
     */
    @Test
    public void writeTest() {
        String fileName = "test.txt";
        String test1 = "Test1";
        String test2 = "Test2";
        String test3 = "Test3";
        String test4 = "Test4";
        try {
            FileWrite w = new FileWrite();
            LenderUtils.lend(w,new Consumer<BufferedWriter>(){
                Object[] parameters = new Object[]{fileName,false};
                public void consume(BufferedWriter writer) throws LoanException {
                    try {
                        writer.write(test1);
                        writer.newLine();
                        writer.append(test2);
                        writer.newLine();
                    } catch (Exception e) {
                        throw new LoanException(e.getMessage(),e);
                    }
                }
                public Object[] getParameter(){
                    return parameters;
                }
            });

            LenderUtils.lend(w,new Consumer<BufferedWriter>(){
                Object[] parameters = new Object[]{"test.txt",false};
                public void consume(BufferedWriter writer) throws LoanException {
                    try{
                        writer.write(test3);
                        writer.newLine();
                        writer.append(test4);
                        writer.newLine();
                    } catch (Exception e) {
                        throw new LoanException(e.getMessage(),e);
                    }
                }
                public Object[] getParameter(){
                    return parameters;
                }
            });


            String[] result = new String[2];
            FileRead r = new FileRead();
            LenderUtils.lend(r,new Consumer<BufferedReader>(){
                Object[] parameters = new Object[]{fileName};
                public void consume(BufferedReader reader) throws LoanException {
                    try{
                        result[0] = reader.readLine();
                        result[1] = reader.readLine();
                    } catch (Exception e) {
                        throw new LoanException(e.getMessage(),e);
                    }
                }
                public Object[] getParameter(){
                    return parameters;
                }
            });
            assertEquals(test3,result[0]);
            assertEquals(test4,result[1]);

        } catch (Exception e) {
            assertTrue(false);
        }


    }
    /**
     * test append of BufferedWriter and read of BufferedReader in
     * loan pattern
     */
    @Test
    public void appendTest() {
        String fileName = "test.txt";
        String test1 = "Test1";
        String test2 = "Test2";
        String test3 = "Test3";
        String test4 = "Test4";
        try{
            File existFile = new File(fileName);
            existFile.delete();

            FileWrite w = new FileWrite();

            LenderUtils.lend(w,new Consumer<BufferedWriter>(){
                Object[] parameters = new Object[]{fileName,false};
                public void consume(BufferedWriter writer) throws LoanException {
                    try {
                        writer.write(test1);
                        writer.newLine();
                        writer.append(test2);
                        writer.newLine();
                    } catch (Exception e) {
                        throw new LoanException(e.getMessage(),e);
                    }
                }
                public Object[] getParameter(){
                    return parameters;
                }
            });



            LenderUtils.lend(w,new Consumer<BufferedWriter>(){
                Object[] parameters = new Object[]{fileName,true};
                public void consume(BufferedWriter writer) throws LoanException {
                    try{
                        writer.write(test3);
                        writer.newLine();
                        writer.append(test4);
                        writer.newLine();
                    } catch (Exception e) {
                        throw new LoanException(e.getMessage(),e);
                    }
                }
                public Object[] getParameter(){
                    return parameters;
                }
            });

            String[] result = new String[4];
            FileRead r = new FileRead();
            LenderUtils.lend(r,new Consumer<BufferedReader>(){
                Object[] parameters = new Object[]{fileName};
                public void consume(BufferedReader reader) throws LoanException {
                    try{
                        result[0] = reader.readLine();
                        result[1] = reader.readLine();
                        result[2] = reader.readLine();
                        result[3] = reader.readLine();
                    } catch (Exception e) {
                        throw new LoanException(e.getMessage(),e);
                    }

                }
                public Object[] getParameter(){
                    return parameters;
                }
            });
            assertEquals(test1,result[0]);
            assertEquals(test2,result[1]);
            assertEquals(test3,result[2]);
            assertEquals(test4,result[3]);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * test whether the function throws correct exception when BufferedWriter
     * goes wrong
     * @throws LoanException the generalized exception
     */
    @Test (expected = LoanException.class)
    public void exceptionTest() throws LoanException{
        FileWrite w = new FileWrite();
        LenderUtils.lend(w,new Consumer<BufferedWriter>(){
            Object[] parameters = new Object[]{};
            public void consume(BufferedWriter writer) throws LoanException{
                try {
                    throw new IOException();
                }catch (Exception e) {
                    throw new LoanException(e.getMessage(),e);
                }
            }
            public Object[] getParameter(){
                return parameters;
            }
        });
    }
    /**
     * test whether the function throws correct exception when BufferedReader
     * goes wrong
     * @throws LoanException the generalized exception
     */
    @Test (expected = LoanException.class)
    public void exceptionTest2() throws LoanException{
        FileRead w = new FileRead();
        LenderUtils.lend(w,new Consumer<BufferedReader>(){
            Object[] parameters = new Object[]{"nonexist.txt"};
            public void consume(BufferedReader reader){

            }
            public Object[] getParameter(){
                return parameters;
            }
        });
    }
}
