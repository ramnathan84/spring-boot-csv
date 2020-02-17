package com.example.csvreader.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.csvreader.dto.EmployeeDTO;
import com.example.csvreader.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<EmployeeDTO> getEmployeeSalRange(float minSal, float maxSal) {

		return employeeRepository.findEmployeesBySalary(minSal, maxSal);
	}

}
