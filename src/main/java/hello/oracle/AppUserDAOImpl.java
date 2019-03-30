package hello.oracle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

import hello.model.AppUser;
import hello.dao.AppUserDAO;

public class AppUserDAOImpl implements AppUserDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveUser(AppUser u) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(u);
        tx.commit();
        session.close();
    }

    public void updateUser(AppUser u) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(u);
        tx.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AppUser> getAllUsers() {
        Session session = this.sessionFactory.openSession();
        List<AppUser> paperList = session.createQuery("from AppUser").list();
        session.close();
        return paperList;
    }

    @Override
    public AppUser getUserByUsername(String username)
    {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("from AppUser where user_name = :username");
        query.setParameter("username",username);
        //List<AppUser> user_l = session.createQuery("from AppUser").list();
        List<AppUser> user_l = query.list();
        session.close();
        AppUser appUser = user_l.get(0);
        return appUser;
    }

    @Override
    public AppUser getUserById(int id)
    {
        Session session = this.sessionFactory.openSession();
        AppUser user = (AppUser) session.load(AppUser.class, id);
        return user;
    }

    public void deleteAppUser(Integer user_id)
    {
        Session session = null;
        Transaction transaction = null;
        try {
            session = this.sessionFactory.openSession();;
            transaction = session.getTransaction();
            transaction.begin();

            AppUser appUser = session.get(AppUser.class, user_id);
            if(appUser!=null){
                session.delete(appUser);
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
