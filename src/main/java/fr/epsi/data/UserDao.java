package fr.epsi.data;

import fr.epsi.model.User;

import java.util.Optional;

/** User DAO interface */
public interface UserDao {

    /**
     * Make user persistent in database
     * @param user the User to make persistent
     * @return Optional User
     */
    Optional<User> save(User user);

    /**
     * Find user with username
     * @param username User's username
     * @return The user
     */
    Optional<User> findByUsername(String username);
}
