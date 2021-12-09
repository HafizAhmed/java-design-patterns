package clientsession;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ClientSessionTest {
    @Test
    public void userNoFundsUnableToPurchase() {
        WorkerSession session = new WorkerSession();
        User testUser = new User("test", session);

        testUser.buyFood();

        UserInfo info = testUser.getInfo();
        assertEquals(info.getNumFood(), 0);
        assertEquals(info.getMoney(), 0);
    }

    @Test
    public void userEarnMoneyBuyFoodShouldUpdate() {
        WorkerSession session = new WorkerSession();
        User testUser = new User("test", session);

        testUser.earnMoney();
        testUser.earnMoney();
        assertEquals(testUser.getInfo().getMoney(), 2);
        testUser.buyFood();
        UserInfo info = testUser.getInfo();
        assertEquals(info.getNumFood(), 1);
        assertEquals(info.getMoney(), 0);
    }

    @Test
    public void userEatZeroFoodShouldFail() {
        WorkerSession session = new WorkerSession();
        User testUser = new User("test", session);

        testUser.eat();

        assertEquals(testUser.getInfo().getNumFood(), 0);
    }
}
