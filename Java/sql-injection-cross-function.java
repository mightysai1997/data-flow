import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CrossFunctionSQLInjection {

    public static void main(String[] args) {
        String userInput = "some_user_input"; // e.g., user enters "' OR 1=1 --"
        
        // Call function to validate user input (could be incorrectly validated)
        if (isValidInput(userInput)) {
            // Pass validated input to another function for database query
            fetchUserData(userInput);
        }
    }

    public static boolean isValidInput(String input) {
        // (Insecure: No proper validation here)
        return input != null && !input.isEmpty();
    }

    public static void fetchUserData(String userInput) {
        try {
            // Establishing a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "username", "password");
            Statement stmt = conn.createStatement();
            
            // Vulnerable query construction using user input directly
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
