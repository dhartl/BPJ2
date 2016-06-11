package at.c02.bpj.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.c02.bpj.server.entity.Customer;
import at.c02.bpj.server.repository.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository customerRepository;

	@Autowired
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	/**
	 * findet alle Kunden
	 * 
	 * @return Liste aller Kunden
	 */
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	/**
	 * löscht den Kunden mit id
	 * 
	 * @param customerid
	 */
	public void deleteCustomer(long customerid) {
		customerRepository.delete(customerid);
	}

	/**
	 * erstellt oder aktualisiert Kunden.
	 * 
	 * @param customer
	 */
	public Customer createOrUpdateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

}
