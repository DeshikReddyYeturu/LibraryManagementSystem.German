package librarymanagement;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;
import librarymanagement.DAO.*;
import librarymanagement.Entity.*;

public class ViewStudents extends JFrame {
    JTable studentsTable;
    private ResourceBundle messages;

    ViewStudents() {
        Locale locale = Locale.GERMANY;
         messages = ResourceBundle.getBundle("librarymanagement.viewstudents", locale);

        setTitle(messages.getString("window.title"));
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnNames = {
                messages.getString("column.studentId"),
                messages.getString("column.studentName"),
                messages.getString("column.studentContact")
        };
        Object[][] studentData = {};

        StudentDAO studentDAO = new StudentDAO();
        List<Student> students = studentDAO.getAllStudents();

        studentData = new Object[students.size()][3];
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            studentData[i][0] = student.getStudentId();
            studentData[i][1] = student.getStudentName();
            studentData[i][2] = student.getStudentContact();
        }

        studentsTable = new JTable(studentData, columnNames);
        JScrollPane scrollPane = new JScrollPane(studentsTable);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}