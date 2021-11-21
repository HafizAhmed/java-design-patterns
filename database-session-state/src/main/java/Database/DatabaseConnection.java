package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {


    private final static String DB_Connection = "jdbc:mysql://localhost:3306/userSession?user=admin&password=testpwd";

    /**
     * Used to get a database connection
     * @return Connections
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {


        return DriverManager.getConnection(DB_Connection);
    }

    /**
     * Closes a database connection
     * @param connection
     */
    public void closeConnection(Connection connection) {
        try {
            if (connection != null)
                connection.close();

        } catch (SQLException ex) {
            System.err.println("Error closing connections");
            ex.printStackTrace();
        }
    }
}
