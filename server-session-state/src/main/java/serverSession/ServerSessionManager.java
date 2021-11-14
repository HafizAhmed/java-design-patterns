package serverSession;

import java.util.HashMap;
import java.util.Map;

public class ServerSessionManager {
    // it stores a map: customerID to their session
    private Map<String, ServerSession> sessionMap;

    public ServerSessionManager() {
        sessionMap = new HashMap<String, ServerSession>();
    }

    ServerSession getSession(String customerId) {
        // if customerId not found, create a new session and add it into the manager
        if(sessionMap.containsKey(customerId)) {
            return sessionMap.get(customerId);
        } else {
            ServerSession newSession = new ShoppingCartSession();
            sessionMap.put(customerId, newSession);
            return newSession;
        }
    }
}
