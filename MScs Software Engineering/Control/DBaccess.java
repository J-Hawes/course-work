package Control;

import java.sql.*;

public class DBaccess {

    //sends a query to the database, and returns a result set - reviewed by Simon
    public ResultSet query(String queryText)
    {
        Connection con = null;
        ResultSet resultSet = null;
        try
        {
            String hostname = "jss.cc19zwruity1.ap-southeast-2.rds.amazonaws.com";
            String port = "1433";
            String dbName = "JSS";
            String userName = "admin";
            String password = "mypassword";
            String jdbcUrl =
                    "jdbc:sqlserver://"
                            + hostname + ":" + port + ";"
                            + "database=" + dbName + ";"
                            + "user=" + userName + ";"
                            + "password=" + password + ";"
                            + "encrypt=false;";
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            con = DriverManager.getConnection(jdbcUrl);
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(queryText);
        }
        catch (SQLException ex)
        {
            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return resultSet;
    }
}
