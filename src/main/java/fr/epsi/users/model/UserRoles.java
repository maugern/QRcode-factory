package fr.epsi.users.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * User roles
 */
@Entity
@Table(name="user_roles", uniqueConstraints =  @UniqueConstraint(columnNames = {"id", "role"}))
public class UserRoles implements Serializable {

    private static final long serialVersionUID = -3668023374571098564L;

    /**
     * Role that can be attributed to User
     */
    public enum Role {
        /** Admin role */
        ROLE_ADMIN,
        /** User role */
        ROLE_USER
    }

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Id
    @Column(name = "role", nullable = false)
    private Role role;

    /** Default constructor */
    public UserRoles(){}

    /**
     * UserRoles Constructor
     * @param user user affected by the role
     * @param role role to attribute
     */
    public UserRoles (Long id, Role role) {
        this.id = id;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
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
        UserRoles other = (UserRoles) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return role == other.role;
    }
    
}
