package fr.maugern.repository;

import fr.maugern.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/** JPA repository for roles */
public interface RoleRepository extends JpaRepository<Role, Long>{
}
