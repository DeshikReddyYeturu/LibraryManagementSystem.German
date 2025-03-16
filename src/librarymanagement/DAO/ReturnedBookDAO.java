package librarymanagement.DAO;

import librarymanagement.HibernateUtil;
import org.hibernate.*;
import java.util.*;
import librarymanagement.Entity.ReturnedBook;
public class ReturnedBookDAO {

    public List<ReturnedBook> getAllReturnedBooks() {
        Transaction transaction = null;
        List<ReturnedBook> returnedBooks = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            returnedBooks = session.createQuery("FROM ReturnedBook").list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return returnedBooks;
    }
    public void saveReturnedBook(ReturnedBook returnedBook) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(returnedBook);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}