package at.c02.bpj.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import at.c02.bpj.server.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

	List<Article> findByName(@Param("name") String name);

}
