package libs;

import java.sql.*;

public class connectDB {
    private String dbName;
    private String userName;
    private String password;

    public connectDB(String dbName, String userName, String password) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    public Connection connection(Connection conn){
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
        return conn;
    }

    public boolean select_ID(String item, Connection conn) throws SQLException {
        boolean Flag = false;
        String query = "SELECT * FROM card WHERE card.id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, item);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Flag = true;
        }
        return Flag;
    }


    public ResultSet selection(String item, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + item;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }

    public void insert_card(String tmp_id, int tmp_status, Connection conn) throws SQLException {
        String query = "INSERT INTO card VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, tmp_id);
        stmt.setInt(2, tmp_status);
        stmt.execute();
    }

    public void insert_customer(String tmp_id, String tmp_name, int tmp_age, String tmp_moto_code, String tmp_dob, Connection conn) throws SQLException {
        String query = "INSERT INTO customer VALUES(?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, tmp_id);
        stmt.setString(2, tmp_name);
        stmt.setInt(3, tmp_age);
        stmt.setString(4, tmp_moto_code);
        stmt.setString(5, tmp_dob);
        stmt.execute();
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
