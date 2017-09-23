package fr.maugern.data;

import fr.maugern.model.Role;

import java.util.Set;

/** Role DAO interface */
public interface RoleDao {

    /**
     * Get all role in database
     * @return Set of Rol:w
     */
    Set<Role> findAll();
}
