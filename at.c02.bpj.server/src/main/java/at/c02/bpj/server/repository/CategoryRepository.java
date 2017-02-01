package at.c02.bpj.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import at.c02.bpj.server.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query("select category from Category category where lower(name) = lower(?1)")
	List<Category> findByName(String name);

	@Query("select category from Category category where lower(name) like lower(?1)")
	List<Category> findByNameLike(String string);

}
