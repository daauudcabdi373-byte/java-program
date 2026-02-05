



import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;
import java.util.Scanner;

public class StudentRegistrationApp extends JFrame {
    private JTextField tfFirstName, tfLastName, tfEmail, tfConfirmEmail;
    private JPasswordField pfPassword, pfConfirmPassword;
    private JComboBox<String> cbYear, cbMonth, cbDay;
    private JRadioButton rbMale, rbFemale, rbCivil, rbCSE, rbElec, rbEC, rbMech;
    private JTextArea outputArea;
    private ButtonGroup genderGroup, deptGroup;

    public StudentRegistrationApp() {
        setTitle("Student Registration System");
        setSize(950, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Color bgColor = new Color(225, 225, 225);
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new MatteBorder(40, 15, 8, 15, new Color(0, 102, 204))); 
        contentPane.setBackground(bgColor);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(bgColor);
        GridBagConstraints gbc = new GridBagConstraints();
        
        JLabel title = new JLabel("New Student Registration Form");
        title.setFont(new Font("Arial", Font.BOLD, 26)); 
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 30, 0); 
        mainPanel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        addFormRow(mainPanel, gbc, "Student First Name", tfFirstName = new JTextField(22), 1);
        addFormRow(mainPanel, gbc, "Student Last Name", tfLastName = new JTextField(22), 2);
        addFormRow(mainPanel, gbc, "Email Address", tfEmail = new JTextField(22), 3);
        addFormRow(mainPanel, gbc, "Confirm Email Address", tfConfirmEmail = new JTextField(22), 4);
        addFormRow(mainPanel, gbc, "Password", pfPassword = new JPasswordField(22), 5);
        addFormRow(mainPanel, gbc, "Confirm Password", pfConfirmPassword = new JPasswordField(22), 6);

        gbc.gridx = 0; gbc.gridy = 7; gbc.insets = new Insets(5, 0, 5, 10);
        mainPanel.add(new JLabel("Date of Birth"), gbc);
        JPanel dobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        dobPanel.setOpaque(false);
        cbYear = new JComboBox<>(new String[]{"Select Year"});
        for(int i=2026; i>=1960; i--) cbYear.addItem(String.valueOf(i));
        cbMonth = new JComboBox<>(new String[]{"Select Month", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"});
        cbDay = new JComboBox<>(new String[]{"Select Day"});
        for(int i=1; i<=31; i++) cbDay.addItem(String.valueOf(i));
        dobPanel.add(cbYear); dobPanel.add(cbMonth); dobPanel.add(cbDay);
        gbc.gridx = 1; mainPanel.add(dobPanel, gbc);

        addFormRow(mainPanel, gbc, "Gender", createGenderPanel(), 8);
        addFormRow(mainPanel, gbc, "Department", createDeptPanel(), 9);

        gbc.gridx = 2; gbc.gridy = 8;
        JLabel lblBelow = new JLabel("Your Data is Below:");
        lblBelow.setFont(new Font("Arial", Font.BOLD, 18)); 
        mainPanel.add(lblBelow, gbc);

        outputArea = new JTextArea(10, 25);
        outputArea.setEditable(false);
        outputArea.setBorder(new CompoundBorder(new LineBorder(Color.GRAY, 1), new EmptyBorder(5,5,5,5)));
        gbc.gridy = 9; gbc.gridheight = 2; gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(new JScrollPane(outputArea), gbc);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        btnPanel.setOpaque(false);
        JButton btnSubmit = new JButton("Submit");
        JButton btnCancel = new JButton("Cancel");
        btnPanel.add(btnSubmit); btnPanel.add(Box.createHorizontalStrut(10)); btnPanel.add(btnCancel);
        gbc.gridx = 1; gbc.gridy = 11; gbc.gridheight = 1; gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(btnPanel, gbc);

        contentPane.add(mainPanel, BorderLayout.CENTER);
        setContentPane(contentPane);
        btnSubmit.addActionListener(e -> handleSubmit());
        btnCancel.addActionListener(e -> resetForm());
    }

    private void addFormRow(JPanel p, GridBagConstraints gbc, String lab, Component c, int y) {
        gbc.gridx = 0; gbc.gridy = y; gbc.insets = new Insets(5, 0, 5, 10);
        p.add(new JLabel(lab), gbc);
        gbc.gridx = 1; gbc.insets = new Insets(5, 5, 5, 10);
        p.add(c, gbc);
    }

    private JPanel createGenderPanel() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0)); p.setOpaque(false);
        rbMale = new JRadioButton("Male"); rbFemale = new JRadioButton("Female");
        rbMale.setOpaque(false); rbFemale.setOpaque(false);
        genderGroup = new ButtonGroup(); genderGroup.add(rbMale); genderGroup.add(rbFemale);
        p.add(rbMale); p.add(Box.createHorizontalStrut(10)); p.add(rbFemale); return p;
    }

    private JPanel createDeptPanel() {
        JPanel p = new JPanel(new GridLayout(5, 1, 0, 2)); p.setOpaque(false);
        rbCivil = new JRadioButton("Civil"); rbCSE = new JRadioButton("Computer Science and Engineering");
        rbElec = new JRadioButton("Electrical"); rbEC = new JRadioButton("Electronics and Communication");
        rbMech = new JRadioButton("Mechanical");
        JRadioButton[] arr = {rbCivil, rbCSE, rbElec, rbEC, rbMech};
        deptGroup = new ButtonGroup();
        for(JRadioButton b : arr) { b.setOpaque(false); deptGroup.add(b); p.add(b); }
        return p;
    }

    private void handleSubmit() {
        StringBuilder err = new StringBuilder();
        String fName = tfFirstName.getText().trim();
        String lName = tfLastName.getText().trim();
        String email = tfEmail.getText().trim();
        String cEmail = tfConfirmEmail.getText().trim();
        String pass = new String(pfPassword.getPassword());
        String cPass = new String(pfConfirmPassword.getPassword());

        
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        
        if(fName.isEmpty() || lName.isEmpty()) err.append("- First and Last names are required.\n");
        
        if(!Pattern.compile(emailPattern).matcher(email).matches()) {
            err.append("- Email format is invalid (e.g., name@example.com).\n");
        }
        if(!email.equals(cEmail)) err.append("- Email addresses do not match.\n");

        if(pass.length() < 8 || pass.length() > 20 || !pass.matches(".*[a-zA-Z].*") || !pass.matches(".*\\d.*"))
            err.append("- Password must be 8-20 chars with at least 1 letter and 1 digit.\n");
        if(!pass.equals(cPass)) err.append("- Passwords do not match.\n");

        if(cbYear.getSelectedIndex() == 0 || cbMonth.getSelectedIndex() == 0 || cbDay.getSelectedIndex() == 0) {
            err.append("- Date of Birth selection is incomplete.\n");
        } else {
            LocalDate dob = LocalDate.of(Integer.parseInt((String)cbYear.getSelectedItem()), cbMonth.getSelectedIndex(), cbDay.getSelectedIndex());
            int age = Period.between(dob, LocalDate.now()).getYears();
            if(age < 16 || age > 60) err.append("- Age must be between 16 and 60 years.\n");
        }

        if(genderGroup.getSelection() == null) err.append("- Please select a Gender.\n");
        if(deptGroup.getSelection() == null) err.append("- Please select a Department.\n");

        if(err.length() > 0) {
            JOptionPane.showMessageDialog(this, err.toString(), "Validation Errors", JOptionPane.ERROR_MESSAGE);
        } else {
            processValidSubmit(fName, lName, email);
        }
    }

    private void processValidSubmit(String fName, String lName, String email) {
        int count = 1;
        File file = new File("students.csv");
        if(file.exists()) {
            try (Scanner sc = new Scanner(file)) {
                while(sc.hasNextLine()) { 
                    String line = sc.nextLine();
                    if(!line.trim().isEmpty()) count++; 
                }
            } catch (Exception e) {}
        }

        String id = LocalDate.now().getYear() + "-" + String.format("%05d", count);
        String gender = rbMale.isSelected() ? "M" : "F";
        String dept = rbCivil.isSelected() ? "Civil" : rbCSE.isSelected() ? "CSE" : rbElec.isSelected() ? "Elec" : rbEC.isSelected() ? "EC" : "Mech";
        String dob = cbYear.getSelectedItem() + "-" + String.format("%02d", cbMonth.getSelectedIndex()) + "-" + String.format("%02d", cbDay.getSelectedIndex());
        
        String record = String.format("ID: %s | %s %s | %s | %s | %s | %s", id, fName, lName, gender, dept, dob, email);
        outputArea.setText(record);

        try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) {
            pw.println(record);
            JOptionPane.showMessageDialog(this, "Data Saved Successfully to students.csv!");
        } catch (IOException ex) { ex.printStackTrace(); }
    }

    private void resetForm() {
        tfFirstName.setText(""); tfLastName.setText(""); tfEmail.setText(""); tfConfirmEmail.setText("");
        pfPassword.setText(""); pfConfirmPassword.setText("");
        genderGroup.clearSelection(); deptGroup.clearSelection();
        cbYear.setSelectedIndex(0); cbMonth.setSelectedIndex(0); cbDay.setSelectedIndex(0);
        outputArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentRegistrationApp().setVisible(true));
    }
}
