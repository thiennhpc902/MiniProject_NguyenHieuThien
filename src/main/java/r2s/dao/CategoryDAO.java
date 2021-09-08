package r2s.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import r2s.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, String> {

}
