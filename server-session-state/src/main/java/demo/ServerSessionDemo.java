package demo;

import serversession.Customer;
import serversession.Merchandise;
import serversession.ServerSessionManager;

public class ServerSessionDemo {
    public static void main(String[] args) {
        ServerSessionManager serverSessionManager = new ServerSessionManager();

        Customer james = new Customer("C-1234", serverSessionManager);
        Merchandise xbox = new Merchandise("XBOX", "playing games", 999);
        Merchandise macbook = new Merchandise("Macbook", "laptop", 2999);
        james.addToCart(xbox, 10);
        james.addToCart(macbook);

        String result = james.showCart();
        System.out.println(result);
    }
}
