package fr.epsi.users.dao;

import fr.epsi.users.model.User;
import fr.epsi.users.model.User_;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    /* (non-Javadoc)
     * @see api.UserDao#addUser(User)
     */
    @Override
    @Transactional
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
    }

    /* (non-Javadoc)
     * @see api.UserDao#updateUser(User)
     */
    @Override
    @Transactional
    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
    }

    /* (non-Javadoc)
     * @see api.UserDao#listPersons()
     */
    @Override
    @Transactional (readOnly=true)
    public List<User> listUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root);
        List<User> users = session.createQuery(cq).getResultList();
        return users;
    }

    /* (non-Javadoc)
     * @see api.UserDao#getUserById(long)
     */
    @Override
    @Transactional (readOnly=true)
    public User getUserById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = session.load(User.class, id);
        return user;
    }

    /* (non-Javadoc)
     * @see api.UserDao#getUserByAlias(String)
     */
    @Override
    @Transactional (readOnly=true)
    public User getUserByAlias(String alias) {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.where(cb.equal(root.get(User_.alias), alias));
        User user = session.createQuery(cq).getSingleResult();
        return user;
    }

    /* (non-Javadoc)
     * @see api.UserDao#removeUser(User)
     */
    @Override
    @Transactional
    public void removeUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(user);
    }

}
