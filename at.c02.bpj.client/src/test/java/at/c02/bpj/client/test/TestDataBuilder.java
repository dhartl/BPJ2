package at.c02.bpj.client.test;

import at.c02.bpj.client.api.model.Article;
import at.c02.bpj.client.api.model.Category;
import at.c02.bpj.client.api.model.Customer;
import at.c02.bpj.client.api.model.Employee;
import at.c02.bpj.client.api.model.Offer;
import at.c02.bpj.client.api.model.OfferPosition;
import at.c02.bpj.client.api.model.OfferStatus;

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

	public static Offer createOffer(Long offerId, OfferStatus status, Customer customer, Employee employee) {
		Offer offer = new Offer();
		offer.setOfferId(offerId);
		offer.setStatus(status);
		offer.setCustomer(customer);
		offer.setEmployee(employee);
		return offer;
	}

	public static OfferPosition createOfferPosition(Long offerPositionId, int posNr, double price, int amount,
			Article article, Offer offerId) {
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

	public static Employee createEmployee(Long employeeId, String firstname, String lastname) {
		Employee employee = new Employee();
		employee.setEmployeeId(employeeId);
		employee.setFirstname(firstname);
		employee.setLastname(lastname);
		return employee;
	}

	public static Customer createCustomer(Long customerId, String companyName) {
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		customer.setCompanyName(companyName);
		return customer;
	}

	public static OfferStatus createOfferStatus() {
		return OfferStatus.COMPLETED;
	}

}
