package at.c02.bpj.client.test;

import java.sql.Date;

import at.c02.bpj.client.api.model.Article;
import at.c02.bpj.client.api.model.Category;
import at.c02.bpj.client.api.model.Customer;
import at.c02.bpj.client.api.model.Employee;
import at.c02.bpj.client.api.model.Offer;
import at.c02.bpj.client.api.model.OfferPosition;
import at.c02.bpj.client.api.model.OfferStatus;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

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
	
	public static Offer createOffer(Long offerId, Date createdDt, Date completedDt, 
					OfferStatus status, Customer customer, Employee employee) {
		Offer offer = new Offer();		
		offer.setOfferId(offerId);
		offer.setCreatedDt(createdDt);
		offer.setCompletedDt(completedDt);
		offer.setStatus(status);
		offer.setCustomer(customer);
		offer.setEmployee(employee);
		return offer;
	}
	
	public static OfferPosition createOfferPosition(Long offerPositionId, int posNr, double price, 
					int amount, Article article, Offer offerId) {
		OfferPosition offerPosition = new OfferPosition();
		offerPosition.setOfferId(offerId);
		offerPosition.setPosNr(posNr);
		offerPosition.setPrice(price);
		offerPosition.setPrice(price);
		offerPosition.setAmount(amount);
		offerPosition.setArticle(article);
		offerPosition.setOfferId(offerId);
		return offerPosition;
	}
	
	
	
}
