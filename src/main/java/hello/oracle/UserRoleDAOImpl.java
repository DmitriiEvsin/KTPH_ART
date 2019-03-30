package hello.oracle;

import hello.model.UserRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

import hello.dao.UserRoleDAO;
import org.hibernate.Transaction;

public class UserRoleDAOImpl implements UserRoleDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<String> getRoleUser()
    {
        Session session = this.sessionFactory.openSession();
        List<String> roles = session.createQuery("from UserRole").list();
        return roles;
    }

    @Override
    public void saveUserRole(UserRole u) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(u);
        tx.commit();
        session.close();
    }

}
