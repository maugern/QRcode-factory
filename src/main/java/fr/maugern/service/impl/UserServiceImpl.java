package fr.maugern.service.impl;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fr.maugern.data.RoleDao;
import fr.maugern.data.UserDao;
import fr.maugern.model.User;
import fr.maugern.service.UserService;

/** User Service implementation */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /** {@inheritDoc} */
    @Override
    public Optional<User> save(final User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleDao.findAll()));
        return userDao.save(user);
    }

    /** {@inheritDoc} */
    @Override
    public Optional<User> findByUsername(final String username) {
        return userDao.findByUsername(username);
    }
}
