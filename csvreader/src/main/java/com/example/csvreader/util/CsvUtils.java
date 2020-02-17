package com.example.csvreader.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.csvreader.dto.EmployeeDTO;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CsvUtils {

	public static List<EmployeeDTO> processCSV(MultipartFile file) throws Exception {
		List<EmployeeDTO> employees = null;
		try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

			// Create CSV bean with a data bean Employee
			CsvToBean<EmployeeDTO> csvToBean = new CsvToBeanBuilder<EmployeeDTO>(reader).withType(EmployeeDTO.class)
					.withIgnoreLeadingWhiteSpace(true).build();

			// convert CSVBean object to list of employees
			if (csvToBean != null) {
				employees = csvToBean.parse();
			}

			return employees;
		}

	}
}