package serverSession;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartSession implements ServerSession {
    private Map<Merchandise, Integer> shoppingCart;

    public ShoppingCartSession() {
        this.shoppingCart = new HashMap<>();
    }

    @Override
    public void setAttribute(Merchandise merchandise, Integer quantity) {

        shoppingCart.put(merchandise, shoppingCart.getOrDefault(merchandise, 0) + quantity);
    }

    @Override
    public Integer getAttribute(Merchandise merchandise) {
        return shoppingCart.get(merchandise);
    }

    public Map<Merchandise, Integer> getShoppingCart() {
        return shoppingCart;
    }
}
