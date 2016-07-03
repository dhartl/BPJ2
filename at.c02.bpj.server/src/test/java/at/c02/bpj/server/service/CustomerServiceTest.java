package at.c02.bpj.server.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import at.c02.bpj.server.entity.Customer;
import at.c02.bpj.server.entity.Gender;
import at.c02.bpj.server.test.AbsDbUnitTest;

public class CustomerServiceTest extends AbsDbUnitTest {
	@Autowired
	private CustomerService customerService;

	@Test
	@DatabaseSetup(value = { "customer/customers-dbsetup.xml" }, type = DatabaseOperation.CLEAN_INSERT)
	public void testGetCustomers() {
		List<Customer> customers = customerService.getAllCustomers();
		Assert.assertEquals(3, customers.size());
	}

	@Test
	@DatabaseSetup(value = { "customer/customers_insert-dbsetup.xml" }, type = DatabaseOperation.CLEAN_INSERT)
	@ExpectedDatabase(value = "customer/customers_insert-dbexpect.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void testInsertCustomer() {
		Customer customer = new Customer();
		customer.setCompanyName("abc");
		customer.setContactGender(Gender.M);
		customerService.createOrUpdateCustomer(customer);
	}

	@Test
	@DatabaseSetup(value = { "customer/customers_update-dbsetup.xml" }, type = DatabaseOperation.CLEAN_INSERT)
	@ExpectedDatabase(value = "customer/customers_update-dbexpect.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void testUpdateArticle() {
		List<Customer> customers = customerService.getAllCustomers();
		Customer customer = customers.get(0);
		customer.setContactEmail("abc@def.at");
		customerService.createOrUpdateCustomer(customer);
	}

}
