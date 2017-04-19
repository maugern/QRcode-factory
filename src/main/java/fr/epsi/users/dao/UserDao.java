package fr.epsi.users.dao;

import java.util.List;

import fr.epsi.users.model.User;

public interface UserDao {

    /**
     * Make a user persistent in database.
     * @param user User who will be insert.
     */
    void addUser(User user);

    /**
     * Update a persistent user. 
     * @param user User who will be updated
     */
    void updateUser(User user);

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
    User getUserById(Long id);

    /**
     * Get the persistent User with the associated id, corresponding with the User 'alias' Unique value.
     * @param alias User alias
     * @return The user
     */
    User getUserByAlias(String alias);

    /**
     * Delete a persistent User from the database.
     * @param user User who will be removed
     */
    void removeUser(User user);
}
