package fr.maugern.service;

import java.util.Optional;

import fr.maugern.model.User;

/** User service interface */
public interface UserService {

    /**
     * Saving user
     * @param user User to save
     * @return The optionally saved user
     */
    Optional<User> save(User user);

    /**
     * Find User by username
     * @param username User's username
     * @return User with corresponding username
     */
    Optional<User> findByUsername(String username);
}
