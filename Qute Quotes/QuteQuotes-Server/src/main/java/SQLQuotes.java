import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLQuotes {
    HashMap<Integer, Quote> quotes = new HashMap<>();

    public Quote insertData(String name, String text){
        SQLInterface sql = new DataBaseConnection();

        try( final Connection connection = DriverManager.getConnection( DataBaseConnection.dbUrl ) ) {

            sql.insert( connection , name, text );

        } catch (SQLException e){
            System.err.println(e.getMessage());
        }

        Quote quote = Quote.create(name, text);

        return quote;

    }

    public List<String> readAuthorColumn(){
        SQLInterface sql = new DataBaseConnection();
        List<String> AuthorRead = new ArrayList<>();

        try( final Connection connection = DriverManager.getConnection( DataBaseConnection.dbUrl ) ) {

            AuthorRead = sql.readData( connection , "Author" );

        } catch (SQLException e){
            System.err.println(e.getMessage());
        }

        return AuthorRead;
    }

    public List<String> readQuoteColumn(){
        SQLInterface sql = new DataBaseConnection();
        List<String> QuoteRead = new ArrayList<>();

        try( final Connection connection = DriverManager.getConnection( DataBaseConnection.dbUrl ) ) {

            QuoteRead = sql.readData( connection , "Quote" );

        } catch (SQLException e){
            System.err.println(e.getMessage());
        }

        return QuoteRead;
    }

    private HashMap<Integer, Quote> fillQuotes(){
        List<String> nameResults = new ArrayList<>();
        List<String> textResults = new ArrayList<>();

        nameResults = readAuthorColumn();
        textResults = readQuoteColumn();

        for (int i = 0; i < nameResults.size(); i++){
            Quote quote = Quote.create(textResults.get(i), nameResults.get(i));
            quotes.put(i, quote);

        }

        return quotes;
    }

    public ArrayList all (){
        quotes = fillQuotes();
        return new ArrayList(quotes.values());
    }

    public Quote get(Integer id){
        quotes = fillQuotes();
        return quotes.get(id);
    }


}
