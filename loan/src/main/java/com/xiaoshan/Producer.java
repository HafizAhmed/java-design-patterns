package com.xiaoshan;

/**
 * The producer interface is used to provide the guideline
 * for producer classes
 */
public interface Producer {
    /**
     * User provided parameters to produce resources
     * @param parameters
     * @param <T> resource type
     * @return resource
     * @throws LoanException a customized exception
     */
    <T> T produce(Object...parameters) throws LoanException;

    /**
     * release resources
     * @throws LoanException a customized exception
     */
    void close() throws LoanException;
}
