package com.xiaoshan;

import java.io.*;

/**
 *  A producer that produces file reader for consumers
 */
public class FileRead implements Producer {
    private BufferedReader br;
    @Override
    public BufferedReader produce(Object... parameters) throws LoanException {
        try {
            File file =new File((String)parameters[0]);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            this.br = br;
            return br;
        } catch (Exception e) {
            throw new LoanException(e.getMessage(),e);

        }

    }
    @Override
    public void close() throws LoanException{
        try {
            if (br != null) {
                br.close();
            }
        } catch (Exception e) {
            throw new LoanException(e.getMessage(),e);
        }
    }
}
