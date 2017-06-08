package fr.epsi.service;

import fr.epsi.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
