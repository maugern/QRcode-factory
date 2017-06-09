package fr.epsi.service;

import fr.epsi.model.User;

/** User service interface */
public interface UserService {

    /**
     * Saving user
     * @param user User to save
     */
    void save(User user);

    /**
     * Find User by username
     * @param username User's username
     * @return User with corresponding username
     */
    User findByUsername(String username);
}
