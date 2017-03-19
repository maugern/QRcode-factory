package dao;

import java.util.List;

import entity.User;

public interface UserDao {

    /**
     * Make a user persistent in database.
     * @param user User who will be insert.
     */
    public void addUser(User user);

    /**
     * Update a persistent user. 
     * @param user User who will be updated
     */
    public void updateUser(User user);

    /**
     * List of all persistent users.
     * @return List of all users.
     */
    public List<User> listUsers();

    /**
     * Get the persistent User with the associated id, corresponding with the User primary key.
     * @param id User id (= primary key)
     * @return The user 
     */
    public User getUserById(Long id);

    /**
     * Get the persistent User with the associated id, corresponding with the User 'alias' Unique value.
     * @param alias User alias
     * @return The user
     */
    public User getUserByAlias(String alias);

    /**
     * Delete a persistent User from the database.
     * @param user User who will be removed
     */
    public void removeUser(User user);
}
