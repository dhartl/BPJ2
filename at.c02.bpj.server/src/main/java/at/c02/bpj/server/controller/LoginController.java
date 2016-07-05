package at.c02.bpj.server.controller;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import at.c02.bpj.server.entity.Employee;
import at.c02.bpj.server.repository.EmployeeRepository;

@Controller
@RequestMapping("login")
public class LoginController {

	private EmployeeRepository employeeRepository;

	@Autowired
	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@Transactional
	public Employee login() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Employee employee = (Employee) authentication.getPrincipal();
		if (employee != null) {
			employeeRepository.updateLtLoginDt(employee.getEmployeeId(), new Date());
		}
		return employee;
	}
}
