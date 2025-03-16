package librarymanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import java.util.ResourceBundle;
import librarymanagement.DAO.*;
import librarymanagement.Entity.*;

public class AddStudent extends JFrame implements ActionListener {
    JTextField studentIdField, studentNameField, studentContactField;
    JButton addButton;
    private ResourceBundle messages;

    public AddStudent() {
        Locale locale = Locale.GERMANY;
        messages = ResourceBundle.getBundle("librarymanagement.addstudent", locale);

        setTitle(messages.getString("window.title"));
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel studentIdLabel = new JLabel(messages.getString("label.studentId"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(studentIdLabel, gbc);

        studentIdField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(studentIdField, gbc);

        JLabel studentNameLabel = new JLabel(messages.getString("label.studentName"));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(studentNameLabel, gbc);

        studentNameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(studentNameField, gbc);

        JLabel studentContactLabel = new JLabel(messages.getString("label.studentContact"));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(studentContactLabel, gbc);

        studentContactField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(studentContactField, gbc);

        addButton = new JButton(messages.getString("button.add"));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        addButton.addActionListener(this);
        panel.add(addButton, gbc);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String studentId = studentIdField.getText().trim();
            String studentName = studentNameField.getText().trim();
            String studentContact = studentContactField.getText().trim();

            if (studentId.isEmpty() || studentName.isEmpty() || studentContact.isEmpty() ||
                    studentId.contains(" ") || studentName.contains(" ") || studentContact.contains(" ")) {
                JOptionPane.showMessageDialog(this, messages.getString("message.emptyFields"));
                return;
            }

            Student student = new Student();
            student.setStudentId(studentId);
            student.setStudentName(studentName);
            student.setStudentContact(studentContact);

            StudentDAO studentDAO = new StudentDAO();
            try {
                studentDAO.saveStudent(student);
                JOptionPane.showMessageDialog(this, messages.getString("message.success"));
                this.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, messages.getString("message.failure"));
                ex.printStackTrace();
            }
        }
    }
}