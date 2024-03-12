package com.iesVda.classDaos;
import java.time.LocalDate;


public class JobHistory {

    private int employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String jobId;
    private int departmentId;
    Department department;
    Job job;

    @Override
    public String toString() {
        return "\n\t\tFecha de inicio: " + startDate +
                "\n\t\tFecha de finalización: " + endDate +
                "\n\t\tTrabajo: " + /*jobName +*/  "(Id " + jobId + ")" +
                "\n\t\tDepartamento:";
    }

    public JobHistory(){}
    
    public JobHistory(int employeeId) {
    	this.employeeId = employeeId;
    }

    public JobHistory(int employeeId, LocalDate startDate, String jobId, int departmentId) {
        this(employeeId);
        this.startDate = startDate;
        this.jobId = jobId;
        this.departmentId = departmentId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
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

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

}
