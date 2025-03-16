package librarymanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import librarymanagement.DAO.IssuedBookDAO;
import librarymanagement.Entity.*;

public class StudentList extends JFrame {

    JTable studentListTable;
    private ResourceBundle messages;

    StudentList() {
        Locale locale = Locale.GERMANY;
        messages = ResourceBundle.getBundle("librarymanagement.studentlist", locale);

        setTitle(messages.getString("window.title"));
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnNames = {
                messages.getString("column.studentId"),
                messages.getString("column.studentName"),
                messages.getString("column.bookId"),
                messages.getString("column.bookName"),
                messages.getString("column.issueDate"),
                messages.getString("column.returnDate")
        };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try {
            IssuedBookDAO issuedBookDAO = new IssuedBookDAO();
            List<IssuedBook> issuedBooks = issuedBookDAO.getAllIssuedBooksWithStudentAndBook();

            if (issuedBooks != null) {
                for (IssuedBook issuedBook : issuedBooks) {
                    Student student = issuedBook.getStudent();
                    if (student != null && issuedBook.getBook() != null) {
                        model.addRow(new Object[]{
                                student.getStudentId(),
                                student.getStudentName(),
                                issuedBook.getBook().getBookId(),
                                issuedBook.getBook().getBookName(),
                                issuedBook.getIssueDate(),
                                issuedBook.getReturnDate() != null ? issuedBook.getReturnDate() : messages.getString("message.notReturned")
                        });
                    } else {
                        System.err.println("IssuedBook with ID " + issuedBook.getIssueId() + " has a null Book or Student.");
                    }
                }
                studentListTable = new JTable(model);
                JScrollPane scrollPane = new JScrollPane(studentListTable);
                add(scrollPane, BorderLayout.CENTER);
            } else {
                JOptionPane.showMessageDialog(this, messages.getString("message.retrieveError"), messages.getString("message.titleError"), JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, messages.getString("message.error") + ex.getMessage(), messages.getString("message.titleError"), JOptionPane.ERROR_MESSAGE);
        }

        setVisible(true);
    }

    public void populateStudentTable() {
        DefaultTableModel model = (DefaultTableModel) studentListTable.getModel();
        model.setRowCount(0);

        try {
            IssuedBookDAO issuedBookDAO = new IssuedBookDAO();
            List<IssuedBook> issuedBooks = issuedBookDAO.getAllIssuedBooksWithStudentAndBook();

            if (issuedBooks != null) {
                for (IssuedBook issuedBook : issuedBooks) {
                    Student student = issuedBook.getStudent();
                    if (student != null && issuedBook.getBook() != null) {
                        model.addRow(new Object[]{
                                student.getStudentId(),
                                student.getStudentName(),
                                issuedBook.getBook().getBookId(),
                                issuedBook.getBook().getBookName(),
                                issuedBook.getIssueDate(),
                                issuedBook.getReturnDate() != null ? issuedBook.getReturnDate() : messages.getString("message.notReturned")
                        });
                    } else {
                        System.err.println("IssuedBook with ID " + issuedBook.getIssueId() + " has a null Book or Student.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, messages.getString("message.retrieveError"), messages.getString("message.titleError"), JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, messages.getString("message.error") + ex.getMessage(), messages.getString("message.titleError"), JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new StudentList();
    }
}