package fr.epsi.repository;

import fr.epsi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/** JPA repository for User */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find User by username
     * @param username Username of user
     * @return User with corresponding username
     */
    User findByUsername(String username);
}
