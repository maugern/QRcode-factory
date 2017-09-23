package fr.maugern.repository;

import fr.maugern.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/** JPA repository for User */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find User by username
     * @param username Username of user
     * @return User with corresponding username
     */
    public User findByUsername(final String username);
}
