package com.iesVda.classDaos;
import java.time.LocalDate;
import java.util.List;

public class Employee {

    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate hireDate;
    private String jobId;
    private Job job;
    private List<jobHistory> jobHistoryList;
    private double salary;
    private double commissionPct;
    private int managerId;
    private Employee manager;
    private int departmentId;
    private Department department;
    boolean isManager;
    boolean isWorking;

    
    public Employee(){}
    
    public Employee(int employeeId) {
    	this.employeeId = employeeId;
    }

    public Employee(int employeeId, String firstName, String lastName) {
        this(employeeId);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee(int employeeId, String firstName, String lastName, String email, String phoneNumber) {
        this(employeeId, firstName, lastName);
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    
    public String getFirstName() {
    	return firstName;
    }
    
    public void setFirstName(String firstName) {
    	this.firstName = firstName;
    }
    
    public String getLastName() {
    	return lastName;
    }
    
    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }    

    public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getJobId() {
		return jobId;
	}

	public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public List<jobHistory> getJobHistory() {
        return jobHistoryList;
    }

    public void setJobHistory(List<jobHistory> jobHistory) {
        this.jobHistoryList = jobHistory;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getCommissionPct() {
        return commissionPct;
    }

    public void setCommissionPct(double commissionPct) {
        this.commissionPct = commissionPct;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public void setJobId(String jobId){
        this.jobId = jobId;
    }
    
    public void setManager(Employee manager) {
    	this.manager = manager;
    }
    
    public Employee getmanager() {
    	return manager;
    }
    
    @Override
    public String toString() {
        String data = "Empleado:" +
                "\n\tId: " + employeeId +
                "\n\tNombre: " + firstName +
                "\n\tApellidos: " + lastName +
                "\n\tEmail: " + email +
                "\n\tNúmero de teléfono: " + phoneNumber +
                "\n\tFecha de contratación: " + hireDate +
                "\n\tSalario: " + salary + " €" +
                "\n\tComisión: " + commissionPct + " €" +
                "\n\tTrabajo actual: " + job +
                "\n\tDepartamento: " + department +
                "\n\tHistorial de trabajos: ";

        for(int i = 0; i < jobHistoryList.toArray().length; i++){
            data += "\n\tTrabajo Nº" + i + ":\n\t\t" /*+ jobHistoryList*/;
        }

                /*", managerId=" + managerId +
                ", departmentId=" + departmentId;*/


        return data;


    }
    
}
