package basededonnee;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBconnexion {

    static Connection connection=null;

    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/reservation";
            connection=DriverManager.getConnection(url,"root","");

        } catch (Exception e) {
        }

        return connection;
    }



}
