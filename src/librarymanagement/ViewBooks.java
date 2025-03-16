package librarymanagement;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import librarymanagement.DAO.*;
import librarymanagement.Entity.*;
import java.util.List;
import java.util.*;

public class ViewBooks extends JFrame {

    private JTable booksTable;
    private DefaultTableModel model;
    private ResourceBundle messages;

    ViewBooks() {
        Locale locale = Locale.GERMANY;
        messages = ResourceBundle.getBundle("librarymanagement.viewbooks", locale);

        setTitle(messages.getString("window.title"));
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnNames = {
                messages.getString("column.bookId"),
                messages.getString("column.bookName"),
                messages.getString("column.author"),
                messages.getString("column.quantity")
        };
        model = new DefaultTableModel(columnNames, 0);

        booksTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(booksTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void setVisible(boolean b) {
        if (b) {
            refreshData();
        }
        super.setVisible(b);
    }

    private void refreshData() {
        model.setRowCount(0);

        BookDAO bookDAO = new BookDAO();
        List<Book> books = bookDAO.getAllBooks();

        if (books != null) {
            for (Book book : books) {
                String bookId = book.getBookId();
                String bookName = book.getBookName();
                String author = book.getAuthor();
                int availableQuantity = book.getQuantity();

                model.addRow(new Object[]{bookId, bookName, author, availableQuantity});
            }
        } else {
            JOptionPane.showMessageDialog(this, messages.getString("message.error"), messages.getString("message.titleError"), JOptionPane.ERROR_MESSAGE);
        }
    }
}