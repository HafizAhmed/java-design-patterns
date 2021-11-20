package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {


    private final String DB_Connection = "jdbc:mysql://localhost:3306/userSession?user=admin&password=testpwd";
    public Connection getConnection() throws SQLException {

        Connection connection = DriverManager.getConnection(DB_Connection);
        return connection;
    }

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
