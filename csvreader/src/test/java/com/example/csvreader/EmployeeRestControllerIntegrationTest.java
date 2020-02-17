package com.example.csvreader;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.csvreader.dto.EmployeeDTO;
import com.example.csvreader.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeRestControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private EmployeeService service;

	@Test
	public void employeeSal() throws Exception {

		EmployeeDTO firstEmp = new EmployeeDTO("Number1", 34, 1000.0F, "Tampines");
		EmployeeDTO secondEmp = new EmployeeDTO("Number2", 37, 4000.0F, "Pasir Ris");

		float minSal = 0F;
		float maxSal = 4000F;

		List<EmployeeDTO> allEmployees = Arrays.asList(firstEmp, secondEmp);

		when(service.getEmployeeSalRange(minSal, maxSal)).thenReturn(allEmployees);

		mvc.perform(get("/users").param("minSal", String.valueOf(minSal)).param("maxSal", String.valueOf(maxSal)))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].name", is("Number1")))
				.andExpect(jsonPath("$[0].age", is(34))).andExpect(jsonPath("$[0].salary", is(1000.0)))
				.andExpect(jsonPath("$[0].address", is("Tampines"))).andExpect(jsonPath("$[1].name", is("Number2")))
				.andExpect(jsonPath("$[1].age", is(37))).andExpect(jsonPath("$[1].salary", is(4000.0)))
				.andExpect(jsonPath("$[1].address", is("Pasir Ris")));
		;

		verify(service, times(1)).getEmployeeSalRange(0F, 4000F);
		// verifyNoMoreInteractions(service);

	}

}
