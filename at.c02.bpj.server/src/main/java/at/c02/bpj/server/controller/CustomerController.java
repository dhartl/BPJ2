package at.c02.bpj.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Customer> getAllCustomers() {
		List<Customer> customers = customerService.getAllCustomers();
		return customers;
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/{customerid}")
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteCustomer(@PathVariable("customerid") long customerid) {
		customerService.deleteCustomer(customerid);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Customer createOrUpdateCustomer(@RequestBody Customer customer) {
		Customer responseCustomer = customerService.createOrUpdateCustomer(customer);
		return responseCustomer;
	}

	
}
