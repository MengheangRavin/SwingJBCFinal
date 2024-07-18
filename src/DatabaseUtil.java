import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3333/?user=root";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "pwd123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    public static List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customer";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(resultSet.getInt("customer_id"));
                customer.setLastName(resultSet.getString("customer_last_name"));
                customer.setFirstName(resultSet.getString("customer_first_name"));
                customer.setPhone(resultSet.getString("customer_phone"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }
}
