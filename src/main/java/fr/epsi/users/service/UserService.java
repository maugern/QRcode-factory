package fr.epsi.users.service;

import java.util.Optional;

import fr.epsi.users.model.User;

public interface UserService {

    /**
     * Store or update user in database
     * @param user User who will be store
     * @return empty optional if cannot be store (maybe already exists),
     * or optional containing the stored user. 
     */
    public Optional<User> save(User user);
    
    /**
     * Find User with username(=alias) in database.
     * @param username
     * @return Empty if username(=alias) not found
     */
    public Optional<User> findByUsername(String username);

    /**
     * Find User with alias in database.
     * @param username
     * @return Empty if alias not found
     */
    public Optional<User> findByAlias(String alias);
    
}
