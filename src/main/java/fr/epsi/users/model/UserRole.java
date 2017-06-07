package fr.epsi.users.model;

import javax.persistence.*;
import javax.validation.Valid;

import java.io.Serializable;

/**
 * User roles
 */
@Entity
@Table(name = "user_roles")
public class UserRole implements Serializable {
    
    private static final long serialVersionUID = 6333055865276880516L;

    @Valid
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    private User user;

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    /** Default constructor */
    public UserRole() {}

    /**
     * UserRoles Constructor
     * @param user user affected by the role
     * @param role role to attribute
     */
    protected UserRole (User user, Role role) {
        this.setUser(user);
        this.role = role;
    }

    /**
     * Get user, associated with user_id column
     * @return User associated to this role
     */
    public User getUser() {
        return user;
    }

    /**
     * Set User
     * @param user User to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Get role, associated with the column role
     * @return role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Set role
     * @param role role to Set
     */
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
