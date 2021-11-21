import Database.DatabaseConnection;
import org.apache.commons.lang3.RandomStringUtils;

import java.nio.charset.Charset;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Random;

public class DatabaseSessionManager {
    /**
     * Checks the database if the user has an existing session and returns that session, otherwise creates a session
     * @param userName
     * @return DatabaseSession
     * @throws SQLException
     */
   public DatabaseSession getSession(String userName) throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.getConnection();
        String sql = "SELECT session.sessionID , session.creationTime , session.lastAccessTime FROM userData JOIN session ON userData.sessionID = session.sessionID " +
                "WHERE userData.userName = ? ";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, userName);
        ResultSet rs = stmt.executeQuery();
        UserSession userSession = null;
        if (rs.next()) {
            userSession = new UserSession();
            userSession.setSessionID(rs.getString("sessionID"));
            userSession.setCreationTime(rs.getTimestamp("creationTime"));
            userSession.setLastAccessTime(Timestamp.from(Instant.now()));
           // update lastAccessTime

        }
        else {
            userSession = new UserSession();
            //byte[] array = new byte[32]; // length is bounded by 32
            //new Random().nextBytes(array);
            String randomSessionID = RandomStringUtils.random(32, true, true);
            userSession.setSessionID(randomSessionID);
            userSession.setCreationTime(Timestamp.from(Instant.now()));
            userSession.setLastAccessTime(Timestamp.from(Instant.now()));
            String sessionSql = "INSERT INTO SESSION (sessionID, creationTime,lastAccessTime ) VALUES (?,?,?)";
            PreparedStatement insertStmtSession = connection.prepareStatement(sessionSql);
            insertStmtSession.setString(1, userSession.getSessionID());
            insertStmtSession.setTimestamp(2,userSession.getCreationTime());
            insertStmtSession.setTimestamp(3, userSession.getLastAccessTime());
            String userSql = "UPDATE userData SET sessionID = ? WHERE userName = ?";
            PreparedStatement updateStmtUser = connection.prepareStatement(userSql);
            updateStmtUser.setString(1, userSession.getSessionID());
            updateStmtUser.setString(2,userName);



        }
        return userSession;
    }

}
