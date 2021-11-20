package com.xiaoshan;

/**
 * LenderUtils is the core of the loan pattern. It helps programmers to avoid writing repetitive codes
 * when programmers want to reuse any resources. The programmers need to provide producers and consumers
 * for the LenderUtils and the LenderUtils will do the work for the programmers. The producer can be
 * reused by programmers.
 */
public class LenderUtils {
    /**
     * Lender lends the resources to the consumers and release the resources after consumer consumes
     * It throws exceptions when producer/consumer goes wrong
     * @param producer the producer which generates resources
     * @param consumer the consumers which consumes the resources
     * @param <T> resource type
     * @throws LoanException a customized exception
     */
    public static <T> void lend(Producer producer, Consumer<T> consumer) throws LoanException{
        try{
            T resource = producer.produce(consumer.getParameter());
            consumer.consume(resource);
        } catch (Exception e) {
            throw new LoanException(e.getMessage(),e);
        } finally{
            producer.close();
        }


    }
}
