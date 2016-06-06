package at.c02.bpj.server.service;

import java.util.List;

import at.c02.bpj.server.entity.Customer;
import at.c02.bpj.server.repository.CustomerRepository;


public class CustomerService {
	
	private CustomerRepository customerRepository;

	
	public CustomerRepository getCustomerRepository() {
		return customerRepository;
	}

	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	/**
	 * findet alle Kunden
	 * @return Liste aller Kunden
	 */
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	/**
	 * löscht den Kunden mit id 
	 * @param customerId
	 */
	public void deleteCustomer(long customerid) {
		customerRepository.delete(customerid);
	}

	/**
	 * erstellt oder aktualisiert Kunden. 
	 * @param customer
	 */
	public Customer createOrUpdateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
}
