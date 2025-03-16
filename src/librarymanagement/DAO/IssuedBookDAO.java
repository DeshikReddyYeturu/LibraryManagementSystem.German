package librarymanagement.DAO;

import librarymanagement.HibernateUtil;
import org.hibernate.*;
//import org.hibernate.query.Query;

import java.util.List;
import librarymanagement.Entity.IssuedBook;

public class IssuedBookDAO {

    public List<IssuedBook> getAllIssuedBooksWithStudentAndBook() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<IssuedBook> issuedBooks = null;
        try {
            transaction = session.beginTransaction();
            String hql = "FROM IssuedBook ib " +
                    "LEFT JOIN FETCH ib.student " +
                    "LEFT JOIN FETCH ib.book"; 
            Query<IssuedBook> query = session.createQuery(hql, IssuedBook.class);
            issuedBooks = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return issuedBooks;
    }

    public void saveIssuedBook(IssuedBook issuedBook) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(issuedBook);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public IssuedBook getIssuedBook(String bookId, String studentId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        IssuedBook issuedBook = null;
        try {
            String hql = "FROM IssuedBook ib WHERE ib.book.bookId = :bookId AND ib.student.studentId = :studentId " +
                         "ORDER BY ib.issueDate DESC";  

            issuedBook = session.createQuery(hql, IssuedBook.class)
                    .setParameter("bookId", bookId)
                    .setParameter("studentId", studentId)
                    .setMaxResults(1) 
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return issuedBook;
    }

    public void updateIssuedBook(IssuedBook issuedBook) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(issuedBook);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}