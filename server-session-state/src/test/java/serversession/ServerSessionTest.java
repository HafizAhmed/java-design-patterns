package serversession;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ServerSessionTest {

    @Test
    public void customerAddTwoItems_ShouldReturnSameItemsWithQuantity() {
        ServerSessionManager serverSessionManager = new ServerSessionManager();

        Customer Tom = new Customer("C-1234", serverSessionManager);
        Merchandise xbox = new Merchandise("xbox", "playing games", 999);
        Merchandise macbook = new Merchandise("Macbook", "laptop", 2999);
        Tom.addToCart(xbox, 10);
        Tom.addToCart(macbook);

        String result = Tom.showCart();

        String expected = "xbox : 10\n" +
                          "Macbook : 1\n";

        assertEquals(expected, result);
    }

    @Test
    public void sameCustomerSameShoppingCart_ShouldReturnSameShoppingCart() {
        ServerSessionManager serverSessionManager = new ServerSessionManager();

        Customer james = new Customer("AD154", serverSessionManager);
        Customer jamesReturn = new Customer("AD154", serverSessionManager);
        assertEquals(james.showCart(), jamesReturn.showCart());
    }

    @Test(expected = NullPointerException.class)
    public void customerAddItemsWithoutShoppingCart_ShouldThrowException(){
        Customer Jessica = new Customer("W007", null);
        Merchandise merchandise = new Merchandise("Skincare", "test", 1099);
        Jessica.addToCart(merchandise);
    }




}
