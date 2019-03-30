package hello.oracle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

import hello.model.AppRole;
import hello.dao.AppRoleDAO;

public class AppRoleDAOImpl implements AppRoleDAO{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<String> getRoleNames(int userId) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("select ur.appRole.role_name from UserRole ur where ur.appUser.user_id = :userid");
        query.setParameter("userid",userId);
        //List<AppUser> user_l = session.createQuery("from AppUser").list();
        List<String> roles = query.list();
        session.close();
        //AppUser appUser = user_l.get(0);
        return roles;
    }

    @Override
    public List<AppRole> getUserRoles(Integer userId) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("select ur.appRole from UserRole ur where ur.appUser.user_id = :userid");
        query.setParameter("userid",userId);
        //List<AppUser> user_l = session.createQuery("from AppUser").list();
        List<AppRole> roles = query.list();
        session.close();
        //AppUser appUser = user_l.get(0);
        return roles;
    }

    @Override
    public List<AppRole> getAppRoles()
    {
        Session session = this.sessionFactory.openSession();
        List<AppRole> roles = session.createQuery("from AppRole").list();
        session.close();
        return roles;
    }

    @Override
    public void saveAppRole(AppRole appRole)
    {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(appRole);

        transaction.commit();
        session.close();
    }

    public AppRole getRoleById(int id)
    {
        Session session = this.sessionFactory.openSession();
        AppRole role = (AppRole) session.load(AppRole.class, id);
        return role;
    }

    public void deleteAppRole(Integer role_id)
    {
        Session session = null;
        Transaction transaction = null;
        try {
            session = this.sessionFactory.openSession();;
            transaction = session.getTransaction();
            transaction.begin();

            AppRole appRole = session.get(AppRole.class, role_id);
            if(appRole!=null){
                session.delete(appRole);
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