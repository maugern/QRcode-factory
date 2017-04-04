package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * User roles
 */
@Entity
@Table(name="user_roles")
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
    @Column(name="id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRoleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private User user;

    @Column(name = "role", nullable = true)
    private Role role;

    /**
     * UserRoles Constructor
     * @param user user affected by the role
     * @param role role to attribute
     */
    public UserRoles (User user, Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
