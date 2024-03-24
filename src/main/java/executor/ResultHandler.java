package executor;

import java.sql.*;

public interface ResultHandler<T> {
    T handle(ResultSet resultSet) throws SQLException;
}
