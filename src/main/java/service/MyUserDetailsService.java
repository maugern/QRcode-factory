package service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.UserDao;
import entity.User;
import entity.UserRoles;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    
    @Autowired private UserDao userDao;

    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    @Transactional (readOnly=true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = userDao.getUserByAlias(username);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
        return buildUserForAuthentication(user, authorities);
    }

    /**
     * Method to convert entity.User to springframework.security.core.userdetails.User
     * @param user
     * @param authorities
     * @return new userdetails.User with authority
     */
    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getAlias(), user.getPasswdHash(), true, true, true, true, authorities);
    }

    /**
     * Build User authority based on his role 
     * @param userRole
     * @return List of granted authority
     */
    private List<GrantedAuthority> buildUserAuthority(Set<UserRoles> userRoles) {
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        for (UserRoles userRole : userRoles)
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole().toString()));
        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);
        return result;
    }

}
