package at.c02.bpj.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import at.c02.bpj.server.entity.Customer;
import at.c02.bpj.server.service.CustomerService;

@Controller // sehr wichtig - Sagt Spring, dass die Klasse erzeugt werden muss
@RequestMapping(path = "customer")
public class CustomerController {

	private CustomerService customerService;

	@Autowired
	// sagt Spring, dass diese Property automatisch beim Initialisieren des
	// Controllers befüllt werden soll
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	// gibt einen Basispfad für alle Controllermethoden an.
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Customer> getAllCustomers() {
		List<Customer> customer = customerService.getAllCustomers();
		return customer;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Customer createOrUpdateCustomer(@RequestBody Customer customer) {
		Customer responsecustomer = customerService.createOrUpdateCustomer(customer);
		return responsecustomer;
	}

}
