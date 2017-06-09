package fr.epsi.service.impl;

import fr.epsi.data.RoleDao;
import fr.epsi.data.UserDao;
import fr.epsi.model.User;
import fr.epsi.repository.RoleRepository;
import fr.epsi.repository.UserRepository;
import fr.epsi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

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
    public void save(final User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleDao.findAll()));
        userDao.save(user);
    }

    /** {@inheritDoc} */
    @Override
    public User findByUsername(final String username) {
        return userDao.findByUsername(username).get();
    }
}
