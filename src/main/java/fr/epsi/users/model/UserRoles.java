package fr.epsi.users.model;

import javax.persistence.*;

/**
 * User roles
 */
@Entity
@Table(name="user_roles", catalog="test", uniqueConstraints =  @UniqueConstraint(columnNames = {"id", "role"}))
public class UserRoles {

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private User user;

    @Id
    @Column(name = "role", nullable = false)
    private Role role;

    /**
     * UserRoles Constructor
     * @param user user affected by the role
     * @param role role to attribute
     */
    public UserRoles (User user, Role role) {
        this.user = user;
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public Role getRole() {
        return role;
    }

}
