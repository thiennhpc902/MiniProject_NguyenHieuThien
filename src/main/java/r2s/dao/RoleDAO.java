package r2s.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import r2s.entity.Roles;

public interface RoleDAO extends JpaRepository<Roles, String>{

}
