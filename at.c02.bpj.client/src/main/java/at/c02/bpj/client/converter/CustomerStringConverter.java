package at.c02.bpj.client.converter;

import at.c02.bpj.client.api.model.Customer;
import javafx.util.StringConverter;

public class CustomerStringConverter extends StringConverter<Customer> {
	@Override
	public String toString(Customer customer) {
		return customer.getCompanyName();
	}

	@Override
	public Customer fromString(String string) {
		return null;
	}
}
