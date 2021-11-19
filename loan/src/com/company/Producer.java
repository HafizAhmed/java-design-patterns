package com.company;

import java.io.PrintWriter;

public interface Producer {
    <T> T produce(Object...parameters) throws Exception;
    void close() throws Exception;
}
