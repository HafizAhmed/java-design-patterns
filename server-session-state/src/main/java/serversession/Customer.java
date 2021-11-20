package serversession;

public class Customer {
    private ShoppingCartSession cartSession;

    /**
     * This constructor is used to create customer with id, and sessionManager
     * @param customerId
     * @param sessionManager manages all the sessions
     */
    public Customer(String customerId, ServerSessionManager sessionManager) {
        this.cartSession = (ShoppingCartSession) sessionManager.getSession(customerId);
    }

    /**
     * This method is used to get shopping cart session
     * @return shopping cart information
     */
    public String showCart() {
        StringBuilder sb = new StringBuilder();
        for(Merchandise merchandise : cartSession.getShoppingCart().keySet()) {
            sb.append(merchandise.getName());
            sb.append(" : ");
            sb.append(cartSession.getAttribute(merchandise));
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * This method is used to  update shopping cart with quantity as 1
     * @param merchandise  one shopping item
     */
    public void addToCart(Merchandise merchandise) {
        this.cartSession.setAttribute(merchandise, 1);
    }

    /**
     * This method is used to update shopping cart with provided quantity
     * @param merchandise shopping items
     * @param quantity
     */
    public void addToCart(Merchandise merchandise, Integer quantity) {
        this.cartSession.setAttribute(merchandise, quantity);
    }
}
