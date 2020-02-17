package com.example.csvreader.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.csvreader.dto.EmployeeDTO;
import com.example.csvreader.model.Employee;
import com.example.csvreader.repository.EmployeeRepository;
import com.example.csvreader.service.EmployeeService;
import com.example.csvreader.util.CsvUtils;

@Controller
@RequestMapping("/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/")
	public String greetingForm(Model model) {
		return "uploadcsv";
	}

	/**
	 * This method allows CSV file to be uploaded.
	 * 
	 * @return String(WebPage)
	 */
	@PostMapping(value = "/upload", consumes = "multipart/form-data")
	public String uploadMultipart(@RequestParam("file") MultipartFile file, Model model) {

		if (file.isEmpty()) {
			model.addAttribute("message", "Please select a CSV file to upload.");
			model.addAttribute("status", false);
		} else {
			try {
				// Process CSV file and return List of Employees
				List<EmployeeDTO> employees = CsvUtils.processCSV(file);

				List<Employee> dbEmployeeLst = employees.stream().map(emp -> {
					Employee employee = new Employee();
					employee.setName(emp.getName());
					employee.setAddress(emp.getAddress());
					employee.setAge(emp.getAge());
					employee.setSalary(emp.getSalary());
					return employee;
				}).collect(Collectors.toList());

				// persist in database
				employeeRepository.saveAll(dbEmployeeLst);

				// save employees list on model
				model.addAttribute("users", dbEmployeeLst);
				model.addAttribute("status", true);

			} catch (Exception e) {
				model.addAttribute("message",
						"An error occurred while processing the CSV file Error is : " + e.getMessage());
				model.addAttribute("status", false);
			}

		}
		return "result";

	}

	/**
	 * This method exposes WebAPI Employees in JSON format.
	 * 
	 * @return Employee List
	 */
	@GetMapping("/users")
	@ResponseBody
	public List<EmployeeDTO> getUsers(@RequestParam Float minSal, @RequestParam Float maxSal) {

		return employeeService.getEmployeeSalRange(minSal, maxSal);
	}

}
