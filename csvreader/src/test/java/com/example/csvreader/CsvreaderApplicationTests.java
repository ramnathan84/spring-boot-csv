package com.example.csvreader;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.example.csvreader.dto.EmployeeDTO;
import com.example.csvreader.model.Employee;
import com.example.csvreader.repository.EmployeeRepository;
import com.example.csvreader.util.CsvUtils;

@SpringBootTest
public class CsvreaderApplicationTests {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void saveEMployee() {

		// given
		Employee empSave = new Employee("Number1", 34, 1000.0F, "Tampines");
		employeeRepository.save(empSave);

		assertNotNull(empSave.getId());
	}

	@Test
	public void retrieveEmployees() {

		List<EmployeeDTO> emp = employeeRepository.findEmployeesBySalary(0F, 4000F);

		assertNotNull(emp.get(0).getAge());
	}

	@Test
	public void checkNormalCSV() throws Exception {

		Path path = Paths.get("/Users/ram/Downloads/file.csv");
		String name = "file.csv";
		String originalFileName = "file.csv";
		String contentType = "text/plain";
		byte[] content = null;
		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {
		}
		MultipartFile result = new MockMultipartFile(name, originalFileName, contentType, content);

		CsvUtils.processCSV(result);

		assertThat(result, IsNull.notNullValue());

	}

}
