package org.sss.blogger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sss.blogger.entity.Role;

// Integer is the type of primary key
public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findByName(String name);

}
