package serversession;

/**
 *  Each customer has one shopping cart session.
 *  Use a map to manage the sessions.
 *  It is a map: item as key,  count as value.
 *  It records what items are customer wants to buy and its quantity.
 */
public interface ServerSession {

    /**
     * This method is used to add new merchandise in shopping cart
     * @param merchandise
     * @param quantity
     */
    void setAttribute(Merchandise merchandise, Integer quantity);

    /**
     * This method is used to get quantity of merchandise from shopping cart
     * @param merchandise
     * @return
     */
    Integer getAttribute(Merchandise merchandise);
}
