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

import at.c02.bpj.server.entity.Employee;
import at.c02.bpj.server.service.EmployeeService;

@Controller // sehr wichtig - Sagt Spring, dass die Klasse erzeugt werden muss
@RequestMapping(path = "employee")
public class EmployeeController {
	
	private EmployeeService employeeService;

	@Autowired
	// sagt Spring, dass diese Property automatisch beim Initialisieren des
	// Controllers befüllt werden soll
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Employee> getAllEmployees() {
		List<Employee> employees = employeeService.getAllEmployees();
		return employees;
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/{employeeid}")
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteEmployee(@PathVariable("employeeid") long employeeid) {
		employeeService.deleteEmployee(employeeid);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Employee createOrUpdateEmployee(@RequestBody Employee employee) {
		Employee responseemployee = employeeService.createOrUpdateEmployee(employee);
		return responseemployee;
	}

}
