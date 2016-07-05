package at.c02.bpj.server.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import at.c02.bpj.server.entity.Employee;

@Controller
@RequestMapping("login")
public class LoginController {

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Employee login() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (Employee) authentication.getPrincipal();
	}
}
