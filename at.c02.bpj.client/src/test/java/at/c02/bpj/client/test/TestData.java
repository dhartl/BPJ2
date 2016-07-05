package at.c02.bpj.client.test;

import at.c02.bpj.client.api.model.Article;
import at.c02.bpj.client.api.model.Category;
import at.c02.bpj.client.api.model.Customer;
import at.c02.bpj.client.api.model.Employee;
import at.c02.bpj.client.api.model.Offer;
import at.c02.bpj.client.api.model.OfferPosition;
import at.c02.bpj.client.api.model.OfferStatus;

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
	
	public static Employee employee1() {
		return TestDataBuilder.createEmployee(1L, "Michael", "Gmoser");
	}
	
	public static Customer customer1() {
		return TestDataBuilder.createCustomer(1L, "A-GmbH");
	}
	
	public static OfferStatus offerStatus1() {
		return TestDataBuilder.createOfferStatus();
	}
	
	public static Offer offer1() {
		return TestDataBuilder.createOffer(1L, offerStatus1(), customer1(), employee1());
	}
	
	public static Offer offer2() {
		return TestDataBuilder.createOffer(2L, offerStatus1(), customer1(), employee1());
	}
	
	public static OfferPosition offerPosition1() {
		return TestDataBuilder.createOfferPosition(1L, 1000, 100d, 10, article1(), offer1());
	}
	
	public static OfferPosition offerPosition2() {
		return TestDataBuilder.createOfferPosition(1L, 1001, 10d, 1, article2(), offer1());
	}

}
