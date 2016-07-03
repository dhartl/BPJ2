package at.c02.bpj.client.service;

import java.util.List;

import at.c02.bpj.client.api.CustomerApi;
import at.c02.bpj.client.api.model.Customer;


public class CustomerService {
	
	private CustomerApi customerApi;

	//Konstruktor  API Klasse 
	public CustomerService(CustomerApi customerApi) {
		super();
		this.customerApi = customerApi;
	}

	//ServiceKlasse executeCall aufrufen --> Response anfordern
	public List<Customer> getCustomer() {
	
		return Services.executeCall(customerApi.getCustomer());
	}

	public Customer saveCustomer(Customer customer) throws ServiceException {
		return Services.executeCall(customerApi.saveCustomer(customer));
	}



}
