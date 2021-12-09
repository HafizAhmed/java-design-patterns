package clientsession;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkerSession {
    /**
     * Logger used to track error messages.
     */
    public static final Logger LOGGER = Logger.getLogger(WorkerSession.class.getName());
    /**
     * The price, in money units, of 1 food unit.
     */
    public static final int FOOD_COST = 2;

    /**
     * Adds 1 money to the passed UserInfo and updates
     * their last access time.
     * @param info The UserInfo to modify.
     */
    public void work(final UserInfo info) {
        info.setMoney(info.getMoney() + 1);
        updateAccess(info);
    }

    /**
     * Attempts to deduct 2 money from the given
     * UserInfo instance to add 1 food.
     * If the UserInfo instance does not have enough money,
     * an error is printed.
     * @param info The UserInfo to modify.
     */
    public void purchase(final UserInfo info) {
        int balance = info.getMoney();
        if (balance >= FOOD_COST) {
            info.setMoney(balance - FOOD_COST);
            info.setNumFood(info.getNumFood() + 1);
        } else {
            LOGGER.log(Level.INFO, "User: " + info.getUserName()
                    + " does not have enough money to purchase food.");
        }
        updateAccess(info);
    }

    /**
     * Updates the UserInfo instance's last access time.
     * @param info The UserInfo to modify.
     */
    private void updateAccess(final UserInfo info) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "dd-MM-yyyy HH:mm:ss");
        info.setLastAccess(formatter.format(now));
    }
}
