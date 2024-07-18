import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Main extends JFrame implements ActionListener {
    private JTextField idField, lastNameField, firstNameField, phoneField;
    private JButton previousButton, nextButton;
    private List<Customer> customers;
    private int currentIndex = 0;

    public Main() {
        setTitle("Customer Information");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField();
        idField.setEditable(false);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField();
        lastNameField.setEditable(false);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField();
        firstNameField.setEditable(false);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneField = new JTextField();
        phoneField.setEditable(false);

        previousButton = new JButton("Previous");
        nextButton = new JButton("Next");

        previousButton.addActionListener(this);
        nextButton.addActionListener(this);

        add(idLabel);
        add(idField);
        add(lastNameLabel);
        add(lastNameField);
        add(firstNameLabel);
        add(firstNameField);
        add(phoneLabel);
        add(phoneField);
        add(previousButton);
        add(nextButton);

        customers = DatabaseUtil.getCustomers();
        displayCustomer(0);
    }

    private void displayCustomer(int index) {
        if (index >= 0 && index < customers.size()) {
            Customer customer = customers.get(index);
            idField.setText(String.valueOf(customer.getCustomerId()));
            lastNameField.setText(customer.getLastName());
            firstNameField.setText(customer.getFirstName());
            phoneField.setText(customer.getPhone());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == previousButton) {
            if (currentIndex > 0) {
                currentIndex--;
                displayCustomer(currentIndex);
            }
        } else if (e.getSource() == nextButton) {
            if (currentIndex < customers.size() - 1) {
                currentIndex++;
                displayCustomer(currentIndex);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main Main = new Main();
            Main.setVisible(true);
        });
    }
}
