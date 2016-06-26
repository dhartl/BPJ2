package at.c02.bpj.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import at.c02.bpj.server.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
