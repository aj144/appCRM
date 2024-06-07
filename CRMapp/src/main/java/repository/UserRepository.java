package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.AppUser;

public interface UserRepository extends  JpaRepository<AppUser, Long>{
	AppUser findByUsername(String username);
	boolean existsByRoles_Name(String roleName);
}
