package at.c02.bpj.client.converter;

import at.c02.bpj.client.api.model.Employee;
import javafx.util.StringConverter;

public class EmployeeStringConverter extends StringConverter<Employee> {
	@Override
	public String toString(Employee employee) {
		return employee.getLastname();
	}

	@Override
	public Employee fromString(String string) {
		return null;
	}
}
