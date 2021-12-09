package clientsession;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to represent a User (worker) that works to earn money
 * and uses that money to buy food, which it can eat.
 *
 * All state is stored "client" side with the User via its UserInfo property.
 * The "server" is represented as a WorkerSession instance, whose methods
 * accept a UserInfo object and peform actions to modify it.
 */
public class User {
    /**
     * Logger used to track error messages.
     */
    public static final Logger LOGGER = Logger.getLogger(User.class.getName());
    /**
     * The session instance to be used as a server by this user.
     */
    private final WorkerSession workerSession;
    /**
     * The UserInfo instance that will hold information about this user.
     */
    private final UserInfo info;

    /**
     * Constructs a new User object with the given username
     * and default UserInfo values.
     * @param userName The name of this user
     * @param session The session associated with this user.
     */
    public User(final String userName, final WorkerSession session) {
        workerSession = session;
        info = new UserInfo(userName);
    }

    /**
     * Earns 1 money for this user.
     */
    public void earnMoney() {
        workerSession.work(info);
    }

    /**
     * Tries to purchase 1 food for this user.
     * Food costs 2 money per unit.
     * If the user does not have sufficient money,
     * an error is printed and nothing else happens.
     */
    public void buyFood() {
        workerSession.purchase(info);
    }

    /**
     * Consumes 1 food.
     * Prints an error if the user tries to eat while
     * possessing no food.
     */
    public void eat() {
        if (info.getNumFood() >= 1) {
            info.setNumFood(info.getNumFood() - 1);
        } else {
            LOGGER.log(Level.INFO, "User: " + info.getUserName()
            + " cannot eat because they have no food.");
        }
    }

    /**
     * Gets the UserInfo object associated with this user.
     * @return info
     */
    public UserInfo getInfo() {
        return info;
    }

    /**
     * Constructs and returns a String summary of this user's info.
     * @return The constructed String.
     */
    public String showInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("User: ");
        sb.append(info.getUserName());
        sb.append("\n");
        sb.append("Last Access: ");
        sb.append(info.getLastAccess());
        sb.append("\n");
        sb.append("Money: ");
        sb.append(info.getMoney());
        sb.append("\n");
        sb.append("Food: ");
        sb.append(info.getNumFood());
        sb.append("\n");
        return sb.toString();
    }
}
