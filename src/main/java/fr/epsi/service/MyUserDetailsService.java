package fr.epsi.service;

import java.util.*;

import fr.epsi.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.epsi.repository.UserDao;
import fr.epsi.model.User;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService, UserService {

    @Autowired
    private UserDao userDao;

    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    @Transactional (readOnly=true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = userDao.getUserByAlias(username).get();
        return buildUserForAuthentication(user, buildUserAuthority(user.getUserRoles()));
    }

    /**
     * Methode to make user persistent in database (when register or update personal information for exemple
     * @param user User who will be save
     * @return the saved user, empty if fail to save the user.
     */
    @Transactional
    public Optional<User> registerNewAccount(User user) throws Exception {
        if (userDao.getUserByAlias(user.getAlias()).isPresent())
            throw new Exception("User with alias " + user.getAlias() + " already exists.");
        return userDao.saveUser(user);
    }

    /**
     * Method to convert fr.epsi.model.User to springframework.security.core.userdetails.User
     * User account is implicitly enabled and non expired
     * @param user
     * @param authorities
     * @return new userdetails.User with authority
     */
    private org.springframework.security.core.userdetails.User buildUserForAuthentication(final User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getAlias(), user.getPasswdHash(), true, true, true, true, authorities);
    }

    /**
     * Build User authority based on his role 
     * @param userRoles
     * @return List of granted authority
     */
    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        for (UserRole userRole : userRoles)
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole().toString()));
        return new ArrayList<GrantedAuthority>(setAuths);
    }

    /* (non-Javadoc)
     * @see fr.epsi.service.UserService#save(fr.epsi.model.User)
     */
    @Override
    public Optional<User> save(User user) {
        return userDao.saveUser(user);
    }

    /* (non-Javadoc)
     * @see fr.epsi.service.UserService#findByUsername(java.lang.String)
     */
    @Override
    public Optional<User> findByUsername(String username) {
        return userDao.getUserByAlias(username);
    }

    /* (non-Javadoc)
     * @see fr.epsi.service.UserService#findByAlias(java.lang.String)
     */
    @Override
    public Optional<User> findByAlias(String alias) {
        return userDao.getUserByAlias(alias);
    }

}
