package Service;

import Model.UserProfile;

import java.sql.*;
import java.util.Properties;

public class DBService {
    private final Connection connection;

    public DBService() {
        connection = getPostgressConnection();
    }

    public UserProfile getUser(String login) {
        try {
            return new UsersDAO(connection).get(login);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void addUser(UserProfile user) {
        try {
            new UsersDAO(connection).insertUser(user.getLogin(), user.getPassword(), user.getEmail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection getPostgressConnection(){
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/javaLabs";
            Properties authorization = new Properties();
            authorization.put("user", "postgres");
            authorization.put("password", "1234");
            return DriverManager.getConnection(url, authorization);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return  null;
    }
}
