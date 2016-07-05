package at.c02.bpj.server.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import at.c02.bpj.server.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findByUsername(String username);

	@Modifying
	@Query("update Employee set lastLoginDt = :date where employeeId = :employeeId")
	void updateLtLoginDt(@Param("employeeId") Long employeeId, @Param("date") Date date);

}
