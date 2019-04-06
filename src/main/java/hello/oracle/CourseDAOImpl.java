package hello.oracle;

import hello.dao.CourseDAO;
import hello.model.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Course> getAllCourses()
    {
        Session session = this.sessionFactory.openSession();
        List<Course> courses = session.createQuery("from Course").list();
        session.close();
        return courses;
    }

    public List<Course> getUserCourses(int user_id)
    {
        Session session = this.sessionFactory.openSession();
        List<Course> courses = session.createQuery("from Course where master_id = :master_id").setParameter("master_id", user_id).list();
        session.close();
        return courses;
    }

    public void saveCourse(Course course)
    {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(course);
        tx.commit();
        session.close();
    }

    public void deleteCourse(int course_id)
    {
        Session session = null;
        Transaction transaction = null;
        try {
            session = this.sessionFactory.openSession();;
            transaction = session.getTransaction();
            transaction.begin();

            Course course = session.get(Course.class, course_id);
            if(course!=null){
                session.delete(course);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
