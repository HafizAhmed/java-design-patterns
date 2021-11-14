package serverSession;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ServerSessionTest {

    @Test
    public void sessionManagerCreateNewTest() {
        ServerSessionManager serverSessionManager = new ServerSessionManager();

        Customer james = new Customer("C-1234", serverSessionManager);
        Merchandise xbox = new Merchandise("xbox", "playing games", 999);
        Merchandise macbook = new Merchandise("Macbook", "laptop", 2999);
        james.addToCart(xbox, 10);
        james.addToCart(macbook);

        String result = james.showCart();

        String expected = "xbox : 10\n" +
                          "Macbook : 1\n";

        assertEquals(expected, result);
    }

    @Test
    public void sessionManagerGetTest() {
        ServerSessionManager serverSessionManager = new ServerSessionManager();

        Customer james = new Customer("C-1234", serverSessionManager);
        Customer jamesReturn = new Customer("C-1234", serverSessionManager);
        assertEquals(james.showCart(), jamesReturn.showCart());
    }
}
