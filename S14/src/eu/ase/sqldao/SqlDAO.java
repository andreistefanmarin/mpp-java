package eu.ase.sqldao;

import java.io.File;
import java.sql.*;

public class SqlDAO {
    private Connection sqliteConn;
    private static SqlDAO currentInstance;

    private SqlDAO() {
        boolean cdb = false;
        File f = new File("./users.db");
        if (!f.exists()) {
            cdb = true;
        }
        try {
            Class.forName("org.sqlite.JDBC");
            sqliteConn = DriverManager.getConnection("jdbc:sqlite:users.db");
            sqliteConn.setAutoCommit(false);
            if(cdb) {
                createDBTable();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void createDBTable() throws SQLException {
        Statement statement = sqliteConn.createStatement();

        String sqlCreateTable = "create table USERS (ID INT PRIMARY KEY NOT NULL, NAME TEXT NOT NULL," +
                "EMAIL CHAR(50), PASSWORD TEXT NOT NULL);";
        statement.execute(sqlCreateTable);
        statement.close();
        sqliteConn.commit();
    }

    public void insertIntoDB(int id, String name, String email, String password) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            if (sqliteConn != null) {
                PreparedStatement ps = sqliteConn.prepareStatement("insert into USERS(ID, NAME, EMAIL, PASSWORD) " +
                        "values (?, ?, ?, ?)");
                ps.setInt(1, id);
                ps.setString(2, name);
                ps.setString(3, email);
                ps.setString(4, password);

                ps.executeUpdate();
                ps.close();

                sqliteConn.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayDB() {
        System.out.println("Display the db: ");
        try {
            Statement statement = sqliteConn.createStatement();
            String sqlSelect = "select * from USERS;";

            ResultSet resultSet = statement.executeQuery(sqlSelect);
            while(resultSet.next())
            {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("NAME");
                String email = resultSet.getString("EMAIL");
                String password = resultSet.getString("PASSWORD");

                System.out.printf("\nID = %d, Name = %s, email = %s, password = %s", id, name, email, password);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized SqlDAO getInstance() {
        if (currentInstance == null) {
            currentInstance = new SqlDAO();
        }
        return currentInstance; //singleton pattern
    }

    public void closeDB() {
        if(sqliteConn != null) {
            try {
                sqliteConn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
