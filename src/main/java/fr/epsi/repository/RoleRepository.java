package fr.epsi.repository;

import fr.epsi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/** JPA repository for roles */
public interface RoleRepository extends JpaRepository<Role, Long>{
}
