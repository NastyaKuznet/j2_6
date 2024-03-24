package executor;

import java.sql.*;

public class Executor {
    private final Connection connection;

    public Executor(Connection connect){
        connection = connect;
    }

    public void execUpdate(String update){
        try(Statement stmt = connection.createStatement()) {
            stmt.execute(update);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void execUpdate(String[] updates){
        try{
            connection.setAutoCommit(false);
            for(String update: updates) {
                Statement stmt = connection.createStatement();
                stmt.execute(update);
                stmt.close();
            }
            connection.commit();
        } catch (SQLException e) {
            try{
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ignore) { }
        }
    }

    public <T> T execQuery(String query, ResultHandler<T> handler){
        try(Statement stmt = connection.createStatement()) {
            stmt.execute(query);
            ResultSet result = stmt.getResultSet();
            T value = handler.handle(result);
            result.close();
            return value;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}