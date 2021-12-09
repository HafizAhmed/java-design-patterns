import Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private Integer userID;
    private String userName;
    private String password;
    private UserSession userSession;

    public User(String userName, String password, DatabaseSessionManager sessionManager) throws SQLException {
        this.userName = userName;
        this.password = password;
        this.userSession = (UserSession) sessionManager.getSession(userName);
    }

    public User() {
    }


    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public UserSession getUserSession() {
        return userSession;
    }



    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }



    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    /**
     *
     * @return Preference from the User session
     */
    public Preference getPreference() {
        return this.userSession.getAttribute();
    }

    /**
     * Initializes a new user by checking if they already exist in the database and adding them to the database in case the user isn't in the database
     * @throws SQLException
     */
    public void initializeUser() throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.getConnection();
        String sql = "SELECT * FROM userData WHERE userData.userName = ? ";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, this.userName);
        ResultSet rs = stmt.executeQuery();
        if (rs.next())
        {
            this.userID = rs.getInt("userID");
            if (isValidUser()) {
                String userSelectSql = "SELECT preferences.fontSize, preferences.fontColor FROM userData JOIN preferences ON userData.preferenceID=Preferences.preferenceID WHERE userData.userName = ? ";
                PreparedStatement userSelectStmt = connection.prepareStatement(userSelectSql);
                userSelectStmt.setString(1, this.userName);
                ResultSet selectRs = userSelectStmt.executeQuery();
                if (selectRs.next()) {
                    this.userSession.getAttribute().setFontSize(selectRs.getInt("fontSize"));
                    this.userSession.getAttribute().setFontColor(selectRs.getString("fontColor"));
                }
            }
        }
        else
        {
            String insertUserSql =  "INSERT INTO userData (userName, password, sessionID) VALUES (?,?,?)";
            PreparedStatement insertStmt = connection.prepareStatement(insertUserSql);
            insertStmt.setString(1, this.userName);
            insertStmt.setString(2, this.password);
            insertStmt.setString(3, this.userSession.getSessionID());
            int insertedUser = insertStmt.executeUpdate();

        }
    }

    /**
     * Updates the database with user preference
     * @param preference
     * @throws SQLException
     */
    public void updatePreference( Preference preference) throws SQLException {
        // Update Preference Table
        int fontSize = preference.getFontSize();
        String fontColor = preference.getFontColor();
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.getConnection();
        String preferenceSql =  "INSERT INTO Preferences (fontsize, fontColor) VALUES (?,?)";
        PreparedStatement insertStmt = connection.prepareStatement(preferenceSql);
        insertStmt.setInt(1, fontSize);
        insertStmt.setString(2, fontColor);
        int insertedPreferences = insertStmt.executeUpdate();

        // Update User Table
        // Get The latest Preference ID
        String selectSql = "SELECT max(preferenceID) FROM Preferences";
        PreparedStatement selectStmt = connection.prepareStatement(selectSql);
        ResultSet rs = selectStmt.executeQuery();
        rs.next();
        int maxPreferenceId = rs.getInt(1);
        String userPreferenceSql =  "UPDATE userData SET preferenceID = ? WHERE userName = ?";
        PreparedStatement userPreferenceStmt = connection.prepareStatement(userPreferenceSql);
        userPreferenceStmt.setInt(1, maxPreferenceId);
        userPreferenceStmt.setString(2, this.userName);


    }

    /**
     * checks if a user is valid by checking if they have an assigned ID
     * @return
     */
    public boolean isValidUser(){
        return this.userID != null;
    }



}

