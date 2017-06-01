package fr.epsi.users.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.epsi.users.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.epsi.users.dao.UserDao;
import fr.epsi.users.model.User;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

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
     * Method to convert fr.epsi.users.model.User to springframework.security.core.userdetails.User
     * User account is implicitly enabled and non expired
     * @param user
     * @param authorities
     * @return new userdetails.User with authority
     */
    private org.springframework.security.core.userdetails.User buildUserForAuthentication(fr.epsi.users.model.User user, List<GrantedAuthority> authorities) {
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

}
