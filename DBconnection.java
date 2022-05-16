import java.sql.*;

/**
 * Seprate for each Local Host
*/
public class DBconnection
{
    // Properties
    private static final String USERNAME = "ather";
    private static final String PASSWORD = "ilyas";
    private static final String CONN = "jdbc:mysql://localhost:3306/";

    // Constructors   


    // Methods

    /**
     * 
     * @param dbName name of the database i.e "group" or "user"
     * @return 
     * @throws SQLException
     */
    public static Connection getConnection(String dbName) throws SQLException {
     
        return DriverManager.getConnection(CONN + dbName, USERNAME, PASSWORD);
    }
    
}