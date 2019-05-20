package capuretta.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else 
        {
            try {

                String driver = "com.mysql.jdbc.Driver";
                String url = "jdbc:mysql://localhost:3306/capuretta_db";
                String user = "root";
                String password = "fttec";
            	
            	Class.forName(driver); //verifica se o driver do bd está no class path...
                
                connection = DriverManager.getConnection(url, user, password);
                
                connection.setAutoCommit(true);
            
            } 
            catch (ClassNotFoundException e) 
            {
                e.printStackTrace();
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            /*} catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();*/
            }
            return connection;
        }
    }
}