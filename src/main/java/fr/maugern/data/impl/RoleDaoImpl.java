package fr.maugern.data.impl;

import fr.maugern.data.RoleDao;
import fr.maugern.model.Role;
import fr.maugern.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

/** Implementation of RoleDao repository */
@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private RoleRepository roleRepository;

    /** {@inheritDoc} */
    @Override
    public Set<Role> findAll() {
        return new HashSet<>(roleRepository.findAll());
    }
}
