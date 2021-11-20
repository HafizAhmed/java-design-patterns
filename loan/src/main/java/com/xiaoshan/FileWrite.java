package com.xiaoshan;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * A producer that produces file writer for consumers
 */
public class FileWrite implements Producer {
    private BufferedWriter bw;
    @Override

    public BufferedWriter produce(Object... parameters) throws LoanException {
        try {
            File file =new File((String)parameters[0]);
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = null;
            if ((Boolean)parameters[1] == true) {
                fw = new FileWriter(file,true);
            } else {
                fw = new FileWriter(file,false);
            }
            BufferedWriter bw = new BufferedWriter(fw);
            this.bw = bw;
            return bw;
        } catch (Exception e) {
            throw new LoanException(e.getMessage(),e);
        }

    }
    @Override
    public void close() throws LoanException{
        try {
            if (bw != null) {
                bw.close();
            }
        } catch (Exception e) {
            throw new LoanException(e.getMessage(),e);
        }

    }
}
