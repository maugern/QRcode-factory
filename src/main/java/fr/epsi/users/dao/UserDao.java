package fr.epsi.users.dao;

import java.util.List;
import java.util.Optional;

import fr.epsi.users.model.User;

public interface UserDao {

    /**
     * Make a user persistent in database, or update it if already exist
     * @param user Saved user.
     * @return Merged user
     */
    Optional<User> saveUser(User user);

    /**
     * List of all persistent users.
     * @return List of all users.
     */
    List<User> listUsers();

    /**
     * Get the persistent User with the associated id, corresponding with the User primary key.
     * @param id User id (= primary key)
     * @return The user 
     */
    Optional<User> getUserById(Long id);

    /**
     * Get the persistent User with the associated id, corresponding with the User 'alias' Unique value.
     * @param alias User alias
     * @return The user
     */
    Optional<User> getUserByAlias(String alias);

    /**
     * Delete a persistent User from the database.
     * @param user User who will be removed
     */
    void removeUser(User user);
}
