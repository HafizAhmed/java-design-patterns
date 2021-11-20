import java.sql.SQLException;

public interface DatabaseSession {
    void setAttribute( Preference value) throws SQLException;
    Preference getAttribute ();


}
