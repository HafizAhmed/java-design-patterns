import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.Assert.assertEquals;

public class DatabaseSessionStateTest {

    @Test
    public void testSetPreference(){
        User user2 = new User();
        user2.setUserID(10);
        user2.setUserName("Bob");
        user2.setPassword("cs427");
        UserSession bobSession = new UserSession();
        bobSession.setSessionID("122hkkkkb");
        bobSession.setCreationTime(Timestamp.from(Instant.now()));
        bobSession.setLastAccessTime(Timestamp.from(Instant.now()));
        user2.setUserSession(bobSession);
        Preference user2_preference = new Preference(13, "RED");
        bobSession.setAttribute(user2_preference);
        Assert.assertEquals(user2_preference, user2.getPreference());

    }

    @Test
    public void invalidUserTest(){
        User user1 = new User();
        boolean expectedOutput = false;
        Assert.assertEquals(expectedOutput, user1.isValidUser());

    }

    @Test
    public void validUserTest(){
        User user2 = new User();
        user2.setUserID(10);
        user2.setUserName("Bob");
        user2.setPassword("cs427");
        boolean expectedOutput = true;
        Assert.assertEquals(expectedOutput, user2.isValidUser());
    }



}
