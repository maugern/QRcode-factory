package dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import entity.User;

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
	@SuppressWarnings({ "deprecation", "unchecked" }) // Forgive me, I dont have better solution : http://stackoverflow.com/questions/115692/how-to-avoid-type-safety-warnings-with-hibernate-hql-results
	@Override
	public List<User> listUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		org.hibernate.Query<User> q = session.createQuery("from users");
		List<User> usersList = Collections.checkedList(q.list(), User.class);
		return usersList;
	}

	/* (non-Javadoc)
	 * @see api.UserDao#getUserById(long)
	 */
	@Override
	public User getUserById(long id) {
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
		@SuppressWarnings("deprecation")
		org.hibernate.Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("alias", alias));
		User user = (User) cr.uniqueResult();
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
