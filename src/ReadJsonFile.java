import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadJsonFile {
    public static void main(String[] args) throws IOException, ParseException {
//        JSONParser jsonParser = new JSONParser();
//        FileReader fileReader = new FileReader("JsonFile");
//        Object obj = jsonParser.parse(fileReader); why??????????
//        JSONObject bookJsonObj = (JSONObject) obj;
//        String id = (String) bookJsonObj.get("id");
//        String language = (String) bookJsonObj.get("language");
//        String edition = (String) bookJsonObj.get("edition");
//        String author = (String) bookJsonObj.get("author");
//        System.out.println("id\t " + id + "language\t " + language+
//                "edition\t "+ edition + "author\t " + author);


        JSONObject employeeDetails = new JSONObject();
        employeeDetails.put("firstName", "Zahra");
        employeeDetails.put("lastName", "Da");
        employeeDetails.put("id", "1");

        JSONObject employeeObject = new JSONObject();
        employeeObject.put("employee", employeeDetails);

        //Second Employee
        JSONObject employeeDetails2 = new JSONObject();
        employeeDetails2.put("firstName", "Hajar");
        employeeDetails2.put("lastName", "Mir");
        employeeDetails2.put("id", "2");
        JSONObject employeeObject2 = new JSONObject();
        employeeObject2.put("employee", employeeDetails2);

        //Add employees to list
        JSONArray employeeList = new JSONArray();
        employeeList.add(employeeObject);
        employeeList.add(employeeObject2);

        //Write JSON file
        try (FileWriter file = new FileWriter("employees.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(employeeList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }


        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("employees.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray employeeList1 = (JSONArray) obj;
            System.out.println(employeeList1);

            //Iterate over employee array
            employeeList1.forEach(emp -> parseEmployeeObject((JSONObject) emp));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }


    private static void parseEmployeeObject(JSONObject employee) {
        //Get employee object within list
        JSONObject employeeObject = (JSONObject) employee.get("employee");

        //Get employee id
        String id = (String) employeeObject.get("id");
        System.out.println(id);

        //Get employee first name
        String firstName = (String) employeeObject.get("firstName");
        System.out.println(firstName);

        //Get employee last name
        String lastName = (String) employeeObject.get("lastName");
        System.out.println(lastName);


    }

}
