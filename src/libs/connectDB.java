package libs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectDB {
    private String dbName;
    private String userName;
    private String password;

    public connectDB(String dbName, String userName, String password) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    public void connection(Connection conn){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Download JDBC
            String url = "jdbc:mysql://localhost:3306/car";// your db name
            String user = this.userName; // your db username
            String password = this.password; // your db password
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("Connect success!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
