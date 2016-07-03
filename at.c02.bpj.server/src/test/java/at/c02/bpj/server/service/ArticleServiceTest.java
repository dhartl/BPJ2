package at.c02.bpj.server.service;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import at.c02.bpj.server.entity.Article;
import at.c02.bpj.server.test.AbsDbUnitTest;

public class ArticleServiceTest extends AbsDbUnitTest {
	@Autowired
	private ArticleService articleService;

	@Autowired
	private CategoryService categoryService;

	@Test
	@DatabaseSetup(value = { "article/articles-dbsetup.xml" }, type = DatabaseOperation.CLEAN_INSERT)
	public void testGetArticles() {
		List<Article> articles = articleService.getAllArticles();
		Assert.assertEquals(3, articles.size());
		Assert.assertEquals(1L, (long) articles.get(0).getCategory().getCategoryId());
	}

	@Test
	@DatabaseSetup(value = { "article/articles_insert-dbsetup.xml" }, type = DatabaseOperation.CLEAN_INSERT)
	public void testInsertArticle() {
		Article article = new Article();
		article.setCategory(categoryService.findById(1L));
		article.setName("Article 1");
		article.setPrice(BigDecimal.valueOf(100d));
		article = articleService.createOrUpdateArticle(article);
		Assert.assertNotNull(article.getArticleId());
	}

	@Test
	@DatabaseSetup(value = { "article/articles_update-dbsetup.xml" }, type = DatabaseOperation.CLEAN_INSERT)
	public void testUpdateArticle() {
		Article article = articleService.getArticleById(1L);
		article.setCategory(categoryService.findById(2L));
		article = articleService.createOrUpdateArticle(article);
		Assert.assertEquals(2L, (long) (article.getCategory().getCategoryId()));
	}

}
