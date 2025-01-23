import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SingleFunctionSQLInjection {

    public static void main(String[] args) {
        // Assuming userInput is taken from a form or HTTP request
        String userInput = "some_user_input"; // e.g., user enters "' OR 1=1 --"
        
        try {
            // Establishing a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "username", "password");
            Statement stmt = conn.createStatement();
            
            // Vulnerable query construction using user input directly in SQL query
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
            
            // Executing the query
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                System.out.println("User found: " + rs.getString("username"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
