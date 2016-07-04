package at.c02.bpj.client.service;

import java.util.List;

import at.c02.bpj.client.api.EmployeeApi;
import at.c02.bpj.client.api.model.Employee;

public class EmployeeService {

	private EmployeeApi employeeApi;

	// Konstruktor API Klasse
	public EmployeeService(EmployeeApi employeeApi) {
		super();
		this.employeeApi = employeeApi;
	}

	// ServiceKlasse executeCall aufrufen --> Response anfordern
	public List<Employee> getEmployee() {

		return Services.executeCall(employeeApi.getEmployee());
	}
}
