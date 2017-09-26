package fr.maugern.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.maugern.data.UserDao;
import fr.maugern.model.Role;
import fr.maugern.model.User;

/** UserDetails service implementation */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    /** {@inheritDoc} */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) {
        Optional<User> user = userDao.findByUsername(username);
        if (!user.isPresent())
            throw new UsernameNotFoundException("User with username " + username + " not found");

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.get().getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), grantedAuthorities);
    }

}
