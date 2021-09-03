package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {

        String query = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(45), " +
                "lastName VARCHAR(45), " +
                "age TINYINT, " +
                "PRIMARY KEY (id));";

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.createSQLQuery(query).executeUpdate();

        session.getTransaction().commit();

        session.close();
    }

    @Override
    public void dropUsersTable() {

        String query = "DROP TABLE IF EXISTS users";

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.createSQLQuery(query).executeUpdate();

        session.getTransaction().commit();

        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(new User(name, lastName, age));

        session.getTransaction().commit();

        session.close();
    }

    @Override
    public void removeUserById(long id) {

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        User user = session.get(User.class, id);

        session.delete(user);

        session.getTransaction().commit();

        session.close();
    }

    @Override
    public List<User> getAllUsers() {

        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery cq = cb.createQuery(User.class);

        Root<User> root = cq.from(User.class);

        cq.select(root);

        Query query = session.createQuery(cq);

        List<User> userList = query.getResultList();

        session.close();

        return userList;
    }

    @Override
    public void cleanUsersTable() {

        String query = "TRUNCATE TABLE users";

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.createSQLQuery(query).executeUpdate();

        session.getTransaction().commit();

        session.close();
    }
}
