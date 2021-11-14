package serverSession;

// each customer has one shopping cart session
// what data is include?
// it is a map: item -> count
// it records what items are customer wants to buy and its quantity
public interface ServerSession {
    void setAttribute(Merchandise merchandise, Integer quantity);
    Integer getAttribute(Merchandise merchandise);
}
