package fr.epsi.users.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.mindrot.jbcrypt.BCrypt;

import fr.epsi.users.model.UserRoles.Role;

/**
 * User class
 */
@Entity
@Table(name="users")
public class User implements Serializable {

    private static final long serialVersionUID = 5316527283073594682L;

    @Id
    @Column(name="id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="alias", nullable = false, unique = true)      
    private String alias;
    
    @Column(name="name")
    private String name;
    
    @Column(name="email", nullable = false, unique = true)
    private String email;
    
    @Column(name="passwdHash", nullable = false)
    private String passwdHash;
    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserRoles.class)
    private Set<UserRoles> userRoles = new HashSet<UserRoles>(0);

    /**
     * User constructor.
     * @param alias login or username, who must be unique
     * @param name label
     * @param email who must be unique
     * @param password non crypted
     */
    public User(String alias,
                String name,
                String email,
                String password)
    {
        this.alias = alias;
        this.name = name;
        this.email = email;
        setPassword(password); // Keep this set or password will be save in clear
        addRole(Role.ROLE_USER); // All users have ROLE_USER by default
    }

    /** 
     * Return a User id, associated with id column (= primary key).
     * @return User id
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Set User id.
     * @param id User id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Return User name, associated with name column.
     * @return name of User
     */
    public String getName() {
        return name;
    }

    /**
     * Set User name.
     * @param name User name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return User alias, associated with name column.
     * Will be use as username, so it must be unique.
     * @return User alias
     */
    public String getAlias() {
        return alias;
    }
    
    /**
     * Set User alias.
     * @param alias User alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Return User email, associated with email column.
     * For security reason, it must be unique.
     * @return User email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Set User email.
     * @param email User email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Set a new passwordHash using
     * <a href="https://en.wikipedia.org/wiki/Bcrypt">bCrypt algorithm</a>.
     * Only hashed password will be save and no salt,
     * to protect against rainbow attacks.
     * @param password non crypted
     */
    private void setPassword(String password) {
        this.passwdHash = BCrypt.hashpw(password, generateSalt());
    }

    /**
     * Testing if password match hashed password using bCrypt algorithm.
     * @param password to test
     * @return true if password match
     */
    public boolean isGoodPassword(String password) {
        return BCrypt.checkpw(password, this.passwdHash);
    }

    /**
     * Return User passwdHash, associated with email passwdHash column.
     * @return User passwdHash
     */
    public String getPasswdHash() {
        return passwdHash;
    }

    /**
     * GenerateSalt log_rounds parameter determines the complexity
     * the work factor is 2**log_rounds, and the default is 12.
     * Always remember in computer science, is only a pseudo-random number.
     * @return BCrypt generated salt
     */
    private String generateSalt() {
        return BCrypt.gensalt(12); // increase default values, who is 10.
    }
    
    public Set<UserRoles> getUserRole() {
        return this.userRoles;
    }

    public void setUserRole(Set<UserRoles> userRoles) {
        this.userRoles = userRoles;
    }
    
    /**
     * Add role to the Set of user role
     * @param role role to add to user 
     * @return true id successfully added, false if role already exist;
     */
    public boolean addRole(UserRoles.Role role) {
        return userRoles.add(new UserRoles(id, role));
    }
    
    /**
     * Remove role to the Set of user role
     * @param role role to remove to user 
     * @return true id successfully remove, false if role not exist;
     */
    public boolean removeRole(UserRoles.Role role) {
        for(UserRoles userRole : userRoles) {
            if (userRole.getRole().equals(role)) {
                return userRoles.remove(userRole);
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (getClass() != obj.getClass())
            return false;
        User user = (User) obj;
        return id.equals(user.getId()) &&
               alias.equals(user.getAlias()) &&
               name.equals(user.getName()) &&
               email.equals(user.getEmail()) &&
               passwdHash.equals(user.getPasswdHash());
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (alias != null ? alias.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (passwdHash != null ? passwdHash.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        // JSON format
        return "{\"id\":"+id+","
               + "\"alias\":\""+alias+"\","
               + "\"name\":\""+name+"\","
               + "\"email\":\""+email+"\","
               + "\"passwdHash\":\""+passwdHash+"\"}";
    }

}
