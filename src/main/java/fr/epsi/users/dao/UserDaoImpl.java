package fr.epsi.users.dao;

import fr.epsi.users.model.User;
import fr.epsi.users.model.User_;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    SessionFactory sessionFactory;

    /* (non-Javadoc)
     * @see api.UserDao#saveUser(User)
     */
    @Override
    public Optional<User> saveUser(User user) {
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
    public Optional<User> getUserById(Long id) {
        return Optional.of(sessionFactory.getCurrentSession().find(User.class,id));
    }

    /* (non-Javadoc)
     * @see api.UserDao#getUserByAlias(String)
     */
    @Override
    public Optional<User> getUserByAlias(String alias) {
        CriteriaBuilder cb = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.where(cb.equal(root.get(User_.alias), alias));
        return Optional.of(sessionFactory.getCurrentSession().createQuery(cq).getSingleResult());
    }

    /* (non-Javadoc)
     * @see api.UserDao#removeUser(User)
     */
    @Override
    public void removeUser(User user) {
        sessionFactory.getCurrentSession().remove(user);
    }

}
