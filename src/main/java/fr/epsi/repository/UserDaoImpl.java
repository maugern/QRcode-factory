package fr.epsi.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /* (non-Javadoc)
     * @see api.UserDao#saveUser(User)
     */
    @Override
    public Optional<User> saveUser(final User user) {
        if (user.getId() == null) // if the user is not persistent
            sessionFactory.getCurrentSession().save(user);
        return Optional.of((User) sessionFactory.getCurrentSession().merge(user));
    }

    /* (non-Javadoc)
     * @see api.UserDao#listPersons()
     */
    @Override
    public List<User> listUsers() {
        CriteriaBuilder cb = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root);
        return sessionFactory.getCurrentSession().createQuery(cq).getResultList();
    }

    /* (non-Javadoc)
     * @see api.UserDao#getUserById(long)
     */
    @Override
    public Optional<User> getUserById(final Long id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().find(User.class,id));
    }

    /* (non-Javadoc)
     * @see api.UserDao#getUserByAlias(String)
     */
    @Override
    public Optional<User> getUserByAlias(final String alias) {
        CriteriaBuilder cb = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.where(cb.equal(root.get("alias"), alias));
        return Optional.ofNullable(sessionFactory.getCurrentSession().createQuery(cq).getSingleResult());
    }

    /* (non-Javadoc)
     * @see api.UserDao#removeUser(User)
     */
    @Override
    public void removeUser(final User user) {
        sessionFactory.getCurrentSession().remove(user);
    }

}
