import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SQLInterface {

    void insert(Connection connection, String name, String text) throws SQLException;

    List<String> readData(Connection connection, String column) throws SQLException;

}
