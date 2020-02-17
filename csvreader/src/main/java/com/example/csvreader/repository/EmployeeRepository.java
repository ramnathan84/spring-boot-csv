package com.example.csvreader.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.csvreader.dto.EmployeeDTO;
import com.example.csvreader.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("SELECT new com.example.csvreader.dto.EmployeeDTO(e.name,e.age,e.salary,e.address) FROM Employee e where e.salary >= :minSal and e.salary<= :maxSal")
	public List<EmployeeDTO> findEmployeesBySalary(@Param("minSal") Float minSal, @Param("maxSal") Float maxSal);

}
