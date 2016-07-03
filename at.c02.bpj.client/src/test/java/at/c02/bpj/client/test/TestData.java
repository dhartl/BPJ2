package at.c02.bpj.client.test;

import at.c02.bpj.client.api.model.Article;
import at.c02.bpj.client.api.model.Category;

public class TestData {

	public static Category category1() {
		return TestDataBuilder.createCategory(1L, "Kategorie1", 1);
	}

	public static Category category2() {
		return TestDataBuilder.createCategory(2L, "Kategorie2", 2);
	}

	public static Article article1() {
		return TestDataBuilder.createArticle(1L, "Artikel1", "Beschreibung1", 100d, category1());
	}

	public static Article article2() {
		return TestDataBuilder.createArticle(2L, "Artikel2", "Beschreibung2", 200d, category2());
	}
}
