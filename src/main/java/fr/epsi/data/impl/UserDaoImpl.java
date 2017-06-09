package fr.epsi.data.impl;

import fr.epsi.data.UserDao;
import fr.epsi.model.User;
import fr.epsi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/** Implementation of UserDao repository */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;

    /** {@inheritDoc} */
    @Override
    public Optional<User> save(final User user) {
        return Optional.ofNullable(userRepository.save(user));
    }

    /** {@inheritDoc} */
    @Override
    public Optional<User> findByUsername(final String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }
}
