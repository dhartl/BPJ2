package at.c02.bpj.server.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import at.c02.bpj.server.entity.Offer;
import at.c02.bpj.server.entity.OfferPosition;
import at.c02.bpj.server.entity.OfferStatus;
import at.c02.bpj.server.repository.CustomerRepository;
import at.c02.bpj.server.repository.EmployeeRepository;
import at.c02.bpj.server.test.AbsDbUnitTest;

public class OfferServiceTest extends AbsDbUnitTest {

	@Autowired
	private OfferService offerService;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	@DatabaseSetup(value = "offer/offers_insert-dbsetup.xml", type = DatabaseOperation.CLEAN_INSERT)
	@ExpectedDatabase(value = "offer/offers_insert-dbexpect.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void testInsertOffer() {
		Offer offer = new Offer();
		offer.setStatus(OfferStatus.CREATED);
		offer.setCreatedDt(new Date());
		offer.setCustomer(customerRepository.findOne(1L));
		offer.setEmployee(employeeRepository.findOne(1L));
		OfferPosition pos1 = new OfferPosition();
		pos1.setPosNr(1);
		pos1.setAmount(2);
		pos1.setPrice(BigDecimal.valueOf(100d));
		pos1.setArticle(articleService.getArticleById(1L));
		offer.getOfferPositions().add(pos1);
		OfferPosition pos2 = new OfferPosition();
		pos2.setPosNr(2);
		pos2.setAmount(5);
		pos2.setPrice(BigDecimal.valueOf(50d));
		pos2.setArticle(articleService.getArticleById(2L));
		offer.getOfferPositions().add(pos2);
		offerService.createOrUpdateOffer(offer);
	}

	@Test
	@DatabaseSetup(value = "offer/offers_update-dbsetup.xml", type = DatabaseOperation.CLEAN_INSERT)
	@ExpectedDatabase(value = "offer/offers_update-dbexpect.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void testUpdateOffer() {
		Offer offer = offerService.getAllOffers().get(0);
		offer.getOfferPositions().stream().filter(position -> Objects.equals(2, position.getPosNr()))
				.forEach(position -> offer.getOfferPositions().remove(position));
		OfferPosition pos3 = new OfferPosition();
		pos3.setPosNr(3);
		pos3.setAmount(9);
		pos3.setPrice(BigDecimal.valueOf(20d));
		pos3.setArticle(articleService.getArticleById(3L));
		offer.getOfferPositions().add(pos3);
		offerService.createOrUpdateOffer(offer);
	}
}
