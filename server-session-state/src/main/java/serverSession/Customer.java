package serverSession;

public class Customer {
    private String customerId;
    private ShoppingCartSession cartSession;

    public Customer(String customerId, ServerSessionManager sessionManager) {
        this.customerId = customerId;
        this.cartSession = (ShoppingCartSession) sessionManager.getSession(customerId);
    }

    // get shopping cart session
    public String showCart() {
        StringBuilder sb = new StringBuilder();
        for(Merchandise merchandise : cartSession.getShoppingCart().keySet()) {
            sb.append(merchandise.getName() + " : " + cartSession.getAttribute(merchandise));
            sb.append("\n");
        }
        return sb.toString();
    }

    // update shopping cart
    public void addToCart(Merchandise merchandise) {
        this.cartSession.setAttribute(merchandise, 1);
    }

    // update shopping cart
    public void addToCart(Merchandise merchandise, Integer quantity) {
        this.cartSession.setAttribute(merchandise, quantity);
    }
}
