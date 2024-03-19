package com.iesVda.classDaos;

import java.util.ArrayList;
import java.util.List;

public class companyEmployees {

	List<Employee> employeesOfCompany;
	int numberOfEmployees;
	
	public companyEmployees() {
		getCompanyEmployees();
		catchNumberOfEmployees();
	}
	
	public void getCompanyEmployees(){
		List<Employee> listOfEmployees = new ArrayList<>();
		EmployeeDao employeeDao = new EmployeeDao();
		listOfEmployees = employeeDao.getAll();
		this.employeesOfCompany = listOfEmployees;
	}

	public void catchNumberOfEmployees() {
		this.numberOfEmployees = this.employeesOfCompany.size(); 
	}
	
	public int getNumberOfEmployees() {
		return numberOfEmployees;
	} 

	public List<Employee> getEmployeesOfCompany() {
		return employeesOfCompany;
	}

	public void setEmployeesOfCompany(List<Employee> employees) {
		this.employeesOfCompany = employees;
	}

	public void setNumberOfEmployees(int numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}

}
