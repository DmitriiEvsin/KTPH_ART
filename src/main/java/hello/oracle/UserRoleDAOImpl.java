package hello.oracle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hello.model.UserRole;
import hello.model.UserRoleUpd;

import java.util.List;

import hello.dao.UserRoleDAO;


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
    public void saveUserRole(UserRoleUpd u) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(u);
        tx.commit();
        session.close();
    }

    @Override
    public UserRoleUpd getByUserRole(Integer user_id, Integer role_id)
    {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("from UserRoleUpd where user_id = :user_id and role_id = :role_id");
        ((org.hibernate.query.Query) query).setParameter("user_id",user_id);
        ((org.hibernate.query.Query) query).setParameter("role_id",role_id);
        List<UserRoleUpd> user_l = query.list();
        session.close();
        UserRoleUpd userRole = user_l.get(0);
        return userRole;
    }

    @Override
    public void deleteUserRole(Long id)
    {
        Session session = null;
        Transaction transaction = null;
        try {
            session = this.sessionFactory.openSession();;
            transaction = session.getTransaction();
            transaction.begin();

            UserRole userRole = session.get(UserRole.class, id);
            if(userRole!=null){
                session.delete(userRole);
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
