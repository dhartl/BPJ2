package at.c02.bpj.server.service;

import java.util.List;

import at.c02.bpj.server.entity.Employee;
import at.c02.bpj.server.repository.EmployeeRepository;

public class EmployeeService {
	
private EmployeeRepository employeeRepository;

	
	public EmployeeRepository getEmployeeRepository() {
	return employeeRepository;
	}

	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
	this.employeeRepository = employeeRepository;
	}

	/**
	 * findet alle Mitarbeiter
	 * @return Liste aller Mitarbeiter
	 */
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	/**
	 * löscht den Mitarbeiter mit id 
	 * @param employeeid
	 */
	public void deleteEmployee(long employeeid) {
		employeeRepository.delete(employeeid);
	}

	/**
	 * erstellt oder aktualisiert Mitarbeiter. 
	 * @param employee
	 */
	public Employee createOrUpdateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	

}
