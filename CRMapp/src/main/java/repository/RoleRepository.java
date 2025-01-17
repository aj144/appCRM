package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Role;


	public interface RoleRepository extends JpaRepository<Role, Long> {
	    Role findByName(String name);
	}

