package com.xiaoshan;
/**
 * The consumer interface is used to provide the guideline
 * for consumer classes
 * @param <T> the resource type that consumer wants to user
 */
public interface Consumer<T> {
    /**
     * the consume function defines how the consumer uses the resource
     * @param resource the resource that will be used by consumer
     * @throws LoanException a customized exception
     */
    void consume(T resource) throws LoanException;

    /**
     * this function is used to provide parameters for resources creation
     * @return parameters
     * @throws LoanException a customized exception
     */
    Object[] getParameter() throws LoanException;

}
