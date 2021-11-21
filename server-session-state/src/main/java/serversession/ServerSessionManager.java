package serversession;

import java.util.HashMap;
import java.util.Map;

public class ServerSessionManager {
    /**
     * It stores a map: customerID to their session
     */
    private Map<String, ServerSession> sessionMap;

    /**
     * This constructor is used to create a map.
     */
    public ServerSessionManager() {
        sessionMap = new HashMap<String, ServerSession>();
    }

    /**
     * This method is used to if customerId not found, create a new session and add it into the manager
     * @param customerId
     * @return serverSession
     */
    ServerSession getSession(String customerId) {

        if(sessionMap.containsKey(customerId)) {
            return sessionMap.get(customerId);
        } else {
            ServerSession newSession = new ShoppingCartSession();
            sessionMap.put(customerId, newSession);
            return newSession;
        }
    }
}
