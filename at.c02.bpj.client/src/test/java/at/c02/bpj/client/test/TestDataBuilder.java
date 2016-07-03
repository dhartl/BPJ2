package at.c02.bpj.client.test;

import at.c02.bpj.client.api.model.Article;
import at.c02.bpj.client.api.model.Category;

public class TestDataBuilder {
	public static Article createArticle(Long id, String name, String description, Double price, Category category) {
		Article article = new Article();
		article.setArticleId(id);
		article.setName(name);
		article.setPrice(price);
		article.setDescription(description);
		article.setCategory(category);
		return article;
	}

	public static Category createCategory(Long id, String name, int sortNr) {
		Category category = new Category();
		category.setCategoryId(id);
		category.setName(name);
		category.setSortNr(sortNr);
		return category;
	}
}
