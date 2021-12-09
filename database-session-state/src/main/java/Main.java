import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        DatabaseSessionManager dbSessionManager = new DatabaseSessionManager();
        // Check if the user is already in the database and get their preferences
        User testUser = new User("testUser3", "testpwd3", dbSessionManager);
        // User preferences should be set up by selecting values from the front-end, simulating values in the below preference object
        Preference userPreference = new Preference(10,"RED");
        UserSession currentSession = testUser.getUserSession();
        currentSession.setAttribute(userPreference);
        testUser.initializeUser();
        testUser.updatePreference(userPreference);

    }
}
