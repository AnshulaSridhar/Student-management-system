
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class StudentManagementSystem extends JFrame implements ActionListener {
    // Components
    JLabel nameLabel, ageLabel, genderLabel, titleLabel;
    JTextField nameField, ageField;
    JComboBox<String> genderComboBox;
    JButton addButton, displayButton;
    JTextArea displayArea;

    // Data
    String[] genders = {"Male", "Female", "Other"};
    StringBuilder studentData = new StringBuilder();

    public StudentManagementSystem() {
        // Frame setup
        setTitle("Student Management System");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setLayout(new BorderLayout(10, 10));

        // Title Panel
        JPanel titlePanel = new JPanel();
        titleLabel = new JLabel("Student Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.BLUE);
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Main Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(new Color(240, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        nameLabel = new JLabel("Name:");
        nameField = new JTextField(15);
        ageLabel = new JLabel("Age:");
        ageField = new JTextField(5);
        genderLabel = new JLabel("Gender:");
        genderComboBox = new JComboBox<>(genders);
        addButton = new JButton("Add Student");
        addButton.addActionListener(this);
        displayButton = new JButton("Display Students");
        displayButton.addActionListener(this);

        gbc.gridx = 0; gbc.gridy = 0; inputPanel.add(nameLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0; inputPanel.add(nameField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; inputPanel.add(ageLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1; inputPanel.add(ageField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; inputPanel.add(genderLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 2; inputPanel.add(genderComboBox, gbc);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        inputPanel.add(addButton, gbc);
        gbc.gridy = 4; inputPanel.add(displayButton, gbc);

        add(inputPanel, BorderLayout.CENTER);

        // Display Panel
        JPanel displayPanel = new JPanel();
        displayPanel.setBorder(new TitledBorder("Student List"));
        displayPanel.setBackground(Color.WHITE);
        displayArea = new JTextArea(10, 35);
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        displayPanel.add(scrollPane);
        add(displayPanel, BorderLayout.SOUTH);

        // Show the frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addStudent();
        } else if (e.getSource() == displayButton) {
            displayStudents();
        }
    }

    private void addStudent() {
        String name = nameField.getText().trim();
        String ageText = ageField.getText().trim();

        // Validate input fields
        if (name.isEmpty() || ageText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int age = Integer.parseInt(ageText);
            if (age <= 0) {
                JOptionPane.showMessageDialog(this, "Age must be a positive number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String gender = (String) genderComboBox.getSelectedItem();
            // Append student data to StringBuilder
            studentData.append("Name: ").append(name)
                    .append(", Age: ").append(age)
                    .append(", Gender: ").append(gender).append("\n");

            // Clear fields after adding student
            nameField.setText("");
            ageField.setText("");
            nameField.requestFocus();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for age.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayStudents() {
        if (studentData.length() == 0) {
            displayArea.setText("No students added yet.");
        } else {
            displayArea.setText(studentData.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentManagementSystem::new);
    }
}
