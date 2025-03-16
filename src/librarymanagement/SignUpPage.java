package librarymanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import librarymanagement.DAO.*;
import librarymanagement.Entity.*;

public class SignUpPage extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton signupButton, loginButton;
    private ResourceBundle messages;

    public SignUpPage() {
        Locale locale = Locale.GERMANY;
        messages = ResourceBundle.getBundle("librarymanagement.signup", locale);

        setTitle(messages.getString("window.title"));
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel usernameLabel = new JLabel(messages.getString("label.username"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel(messages.getString("label.password"));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordField, gbc);

        signupButton = new JButton(messages.getString("button.signup"));
        gbc.gridx = 0;
        gbc.gridy = 2;
        signupButton.addActionListener(this);
        panel.add(signupButton, gbc);

        loginButton = new JButton(messages.getString("button.login"));
        gbc.gridx = 1;
        gbc.gridy = 2;
        loginButton.addActionListener(this);
        panel.add(loginButton, gbc);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            Admin admin = new Admin();
            admin.setUsername(username);
            admin.setPassword(password);

            AdminDAO adminDAO = new AdminDAO();
            try {
                adminDAO.saveAdmin(admin);
                JOptionPane.showMessageDialog(this, messages.getString("message.signupSuccess"));
                new LoginPage().setVisible(true);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, messages.getString("message.signupError"), messages.getString("window.title"), JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        } else if (e.getSource() == loginButton) {
            new LoginPage().setVisible(true);
            dispose();
        }
    }
}