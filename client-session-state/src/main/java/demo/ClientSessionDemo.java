package demo;

import clientsession.User;
import clientsession.WorkerSession;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class ClientSessionDemo {
    /**
     * Logger used to track messages.
     */
    public static final Logger LOGGER = Logger.getLogger(ClientSessionDemo.class.getName());
    private ClientSessionDemo() {
        //Suppress warnings
    }
    /**
     * Tests some User functionality.
     * @param args unused
     */
    public static void main(final String[] args) {
        WorkerSession session = new WorkerSession();
        User joe = new User("joe", session);
        LOGGER.log(Level.INFO, joe.showInfo());
        joe.earnMoney();
        LOGGER.log(Level.INFO, joe.showInfo());
        joe.earnMoney();
        joe.buyFood();
        LOGGER.log(Level.INFO, joe.showInfo());
        joe.eat();
        LOGGER.log(Level.INFO, joe.showInfo());
    }
}
