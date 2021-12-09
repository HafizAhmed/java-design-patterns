package clientsession;

public class UserInfo {
    /**
     * The name of the user associated with this instance.
     */
    private final String userName;
    /**
     * The String timestamp of the last time this
     * user accessed the server.
     * Updated whenever the user makes a request to the server.
     */
    private String lastAccess;
    /**
     * The amount of money units possessed by this user.
     */
    private int money;
    /**
     * The amount of food units possessed by this user.
     */
    private int numFood;

    /**
     * Constructs a UserInfo object with the passed username
     * and default values otherwise.
     * @param name The user's name.
     */
    public UserInfo(final String name) {
        this.userName = name;
        this.lastAccess = "";
        this.money = 0;
        this.numFood = 0;
    }

    /**
     * Getter for userName.
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Getter for lastAccess.
     * @return lastAccess
     */
    public String getLastAccess() {
        return lastAccess;
    }

    /**
     * Getter for money.
     * @return money
     */
    public int getMoney() {
        return money;
    }

    /**
     * Getter for numFood.
     * @return numFood.
     */
    public int getNumFood() {
        return numFood;
    }

    /**
     * Setter for money.
     * @param amt The new amount of money for this user.
     */
    public void setMoney(final int amt) {
        this.money = amt;
    }

    /**
     * Setter for lastAccess.
     * @param timestamp The new lastAccess String for this user.
     */
    public void setLastAccess(final String timestamp) {
        this.lastAccess = timestamp;
    }

    /**
     * Setter for numFood.
     * @param amt The new amount of food for this user.
     */
    public void setNumFood(final int amt) {
        this.numFood = amt;
    }
}
