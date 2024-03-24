package Service;

import Model.UserProfile;
import executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

public class UsersDAO {
    private Executor executor;

    public UsersDAO(Connection connection){
        this.executor = new Executor(connection);
    }

    public UserProfile get(String login) throws SQLException{
        return executor.execQuery("select * from lab.users where login='" + login +"'", result -> {
            result.next();
            return new UserProfile(result.getString(2), result.getString(3), result.getString(4));
        });
    }

    public void insertUser(String login, String password, String email) throws  SQLException{
        executor.execUpdate("insert into lab.users (login, password, email) values ('" + login +"', '"+password+"', '"+email+"')");
    }
}
