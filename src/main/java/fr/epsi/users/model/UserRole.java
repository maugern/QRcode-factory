package fr.epsi.users.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * User roles
 */
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {
    
    private static final long serialVersionUID = 6333055865276880516L;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    private User user;

    @Id
    @Enumerated
    @Column(name = "role")
    private Role role;

    /** Default constructor */
    public UserRole(){}

    /**
     * UserRoles Constructor
     * @param user user affected by the role
     * @param role role to attribute
     */
    public UserRole (User user, Role role) {
        this.setUser(user);
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int result = 1;
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserRole userRole = (UserRole) obj;
        return userRole.getRole().ordinal() == role.ordinal() &&
               userRole.getUser().equals(user);
    }

}
