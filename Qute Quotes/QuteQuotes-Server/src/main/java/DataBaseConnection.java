import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBaseConnection implements SQLInterface {

    public static final String IN_MEMORY_DB_URL = "jdbc:sqlite::memory:";

    public static final String DISK_DB_URL = "jdbc:sqlite:";

    public static String dbUrl = DISK_DB_URL+"QuteQuoteDataBase.db";

    public static void main(String[] args) {

        try( final Connection connection = DriverManager.getConnection( dbUrl ) ){
            System.out.println( "Connected to database " );
        }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
    }

    @Override
    public void insert(Connection connection, String Quote, String Author) throws SQLException {
        try( final Statement statement = connection.createStatement() ){
            boolean gotAResultSet = statement.execute(
                    "INSERT INTO "
                            + "Quotes" +"( Quote, Author ) "+
                            "VALUES ( \""+ Author +"\", \""+ Quote+"\" )"
            );
            if ( gotAResultSet ){
                throw new RuntimeException( " >> Response: Unexpectedly got a SQL result.");
            }
            else{
                final int updateCount = statement.getUpdateCount();
                if( updateCount == 1 ){
                    System.out.println( " >> Response: 1 row INSERTED into "+ "Quotes");
                }
                else{
                    throw new RuntimeException( " >> Response: Expected 1 row to be inserted, but got " + updateCount );
                }
            }
        }
    }

    @Override
    public List<String> readData(Connection connection, String column) throws SQLException {
        List<String> resultList = new ArrayList<>();

        try( final Statement statement = connection.createStatement() ){
            boolean gotAResultSet = statement.execute(
                    "SELECT *"
                            +" FROM "+"\"Quotes\""
            );
            if ( !gotAResultSet ){
                throw new RuntimeException( " >> Response: Expected a SQL result, but got an update count instead!" );
            }
            try ( ResultSet results = statement.getResultSet() ){
                while( results.next() ){
                    resultList.add( results.getString(column) );

                }
                System.out.println( " >> Response: This is the table result: " + Arrays.toString( resultList.toArray() ) );
            }
        }
        return resultList;
    }

}