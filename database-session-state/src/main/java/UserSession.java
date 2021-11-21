import Database.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;

public class UserSession implements DatabaseSession {

    private String sessionID;
    private Timestamp creationTime;
    private Timestamp lastAccessTime;
    private Preference preference;

    public String getSessionID() {
        return sessionID;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public Timestamp getLastAccessTime() {
        return lastAccessTime;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public void setLastAccessTime(Timestamp lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    /**
     * setAttribute method to set attributes related to the session, in this case user preference
     * @param value
     */
    @Override
    public void setAttribute(Preference value) {
        this.preference = value;

    }

    /**
     * getAttributes associated with the session, in this case user preference
     * @return
     */
    @Override
    public Preference getAttribute() {
        return this.preference;
    }



}
