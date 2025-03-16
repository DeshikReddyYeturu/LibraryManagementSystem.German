package librarymanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainMenu extends JFrame implements ActionListener {
    JButton addBookButton, issueBookButton, returnBookButton, viewBooksButton, studentsButton;
    StudentList studentList;
    private ResourceBundle messages;

    MainMenu() {
        Locale locale = Locale.GERMANY;
        messages = ResourceBundle.getBundle("librarymanagement.mainmenu", locale);

        setTitle(messages.getString("window.title"));
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        addBookButton = new JButton(messages.getString("button.addBook"));
        addBookButton.setPreferredSize(new Dimension(200, 50));
        addBookButton.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        addBookButton.addActionListener(this);
        panel.add(addBookButton, gbc);

        issueBookButton = new JButton(messages.getString("button.issueBook"));
        issueBookButton.setPreferredSize(new Dimension(200, 50));
        issueBookButton.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        issueBookButton.addActionListener(this);
        panel.add(issueBookButton, gbc);

        returnBookButton = new JButton(messages.getString("button.returnBook"));
        returnBookButton.setPreferredSize(new Dimension(200, 50));
        returnBookButton.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        returnBookButton.addActionListener(this);
        panel.add(returnBookButton, gbc);

        viewBooksButton = new JButton(messages.getString("button.viewBooks"));
        viewBooksButton.setPreferredSize(new Dimension(200, 50));
        viewBooksButton.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 3;
        viewBooksButton.addActionListener(this);
        panel.add(viewBooksButton, gbc);

        studentsButton = new JButton(messages.getString("button.students"));
        studentsButton.setPreferredSize(new Dimension(200, 50));
        studentsButton.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 4;
        studentsButton.addActionListener(this);
        panel.add(studentsButton, gbc);

        add(panel, BorderLayout.CENTER);
        setVisible(true);

        studentList = new StudentList();
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == addBookButton) {
                new AddBook().setVisible(true);
            } else if (e.getSource() == issueBookButton) {
                new IssueBook().setVisible(true);
            } else if (e.getSource() == returnBookButton) {
                new ReturnBook(studentList).setVisible(true);
            } else if (e.getSource() == viewBooksButton) {
                new ViewBooks().setVisible(true);
            } else if (e.getSource() == studentsButton) {
                createStudentMenu();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, messages.getString("message.error") + ex.getMessage(), messages.getString("message.titleError"), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createStudentMenu() {
        JPopupMenu studentMenu = new JPopupMenu();
        JMenuItem addStudentItem = new JMenuItem(messages.getString("menu.addStudent"));
        JMenuItem viewStudentsItem = new JMenuItem(messages.getString("menu.viewStudents"));
        JMenuItem studentListItem = new JMenuItem(messages.getString("menu.studentList"));

        addStudentItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddStudent().setVisible(true);
            }
        });

        viewStudentsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewStudents().setVisible(true);
            }
        });

        studentListItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                studentList.setVisible(true);
            }
        });

        studentMenu.add(addStudentItem);
        studentMenu.add(viewStudentsItem);
        studentMenu.add(studentListItem);

        studentMenu.show(studentsButton, 0, studentsButton.getHeight());
    }

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
    }
}