package com.example.csvreader.dto;

import com.opencsv.bean.CsvBindByName;

public class EmployeeDTO {

	@CsvBindByName(column = "name")
	private String name;

	@CsvBindByName(column = "age")
	private Integer age;

	@CsvBindByName(column = "salary")
	private Float salary;

	@CsvBindByName(column = "address")
	private String address;

	public EmployeeDTO() {

	}

	public EmployeeDTO(String name, Integer age, Float salary, String address) {
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "EmployeeDTO [name=" + name + ", age=" + age + ", salary=" + salary + ", address=" + address + "]";
	}

}
