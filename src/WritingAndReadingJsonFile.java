import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class WritingAndReadingJsonFile {

    //JDBC and database properties.
    private static final String DB_DRIVER =
            "com.mysql.jdbc.Driver";
    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/employees";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "57247";

    public static Connection ConnectToDB() throws Exception {
        Connection con = null;
        con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        return con;
    }

    public static void main(String args[]) {
        //Creating a JSONParser object
        JSONParser jsonParser = new JSONParser();
        try {
            //Parsing the contents of the JSON file
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("employees.json"));
            //Retrieving the array
            JSONArray jsonArray = (JSONArray) jsonObject.get("employee");
            Connection con = ConnectToDB();
            //Insert a row into the table
            PreparedStatement insertingValues = con.prepareStatement("INSERT INTO employees values (?, ?, ?, ?, ?, ? )");
            for (Object object : jsonArray) {
                JSONObject record = (JSONObject) object;
                int id = Integer.parseInt((String) record.get("ID"));
                String first_name = (String) record.get("First_Name");
                String last_name = (String) record.get("Last_Name");

                insertingValues.setInt(1, id);
                insertingValues.setString(2, first_name);
                insertingValues.setString(3, last_name);
                insertingValues.executeUpdate();
            }
            System.out.println("Records inserted.....");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

