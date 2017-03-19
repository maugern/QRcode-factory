package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import entity.User;
import entity.User_;

@Service
public class UserDaoImpl implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    /* (non-Javadoc)
     * @see api.UserDao#addUser(entity.User)
     */
    @Override
    @Transactional
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
        logger.info("User " + user +" successfully added.");
    }

    /* (non-Javadoc)
     * @see api.UserDao#updateUser(entity.User)
     */
    @Override
    @Transactional
    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("User " + user +" successfully updated.");
    }

    /* (non-Javadoc)
     * @see api.UserDao#listPersons()
     */
    @Override
    public List<User> listUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root);
        List<User> users = session.createQuery(cq).getResultList();
        logger.info("Query return " + users.size() + " users.");
        return users;
    }

    /* (non-Javadoc)
     * @see api.UserDao#getUserById(long)
     */
    @Override
    public User getUserById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = session.load(User.class, id);
        logger.info("User " + user +" loaded successfully.");
        return user;
    }

    /* (non-Javadoc)
     * @see api.UserDao#getUserByAlias(String)
     */
    @Override
    public User getUserByAlias(String alias) {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.where(cb.equal(root.get(User_.alias), alias));
        User user = session.createQuery(cq).getSingleResult();
        logger.info("User " + user +" loaded successfully.");
        return user;
    }

    /* (non-Javadoc)
     * @see api.UserDao#removeUser(entity.User)
     */
    @Override
    @Transactional
    public void removeUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(user);
        logger.info("User " + user + " deleted successfully.");
    }

}
