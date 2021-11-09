package com.company;
import java.lang.reflect.*;
public class Lender {

    public static <T> void lend(Producer producer, Consumer<T> consumer)
            throws Exception {
        try {
            T resource = producer.produce(consumer.getParameter());
            consumer.consume(resource);
        } catch(Exception e){
            //e.printStackTrace();
            System.out.println(e);
        }finally {
            producer.close();
        }
    }
}
