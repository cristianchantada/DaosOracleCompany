package com.iesVda.classDaos;

public class Job {

    private String jobId;
    private String jobTitle;
    private double minSalary;
    private double maxSalary;

    @Override
    public String toString() {
        return "\n\t\tTrabajo: " + jobTitle +
                "\n\t\tSalario mínimo del trabajo: " + minSalary + " €" +
                "\n\t\tSalario máximo del trabajo: " + maxSalary + " €";
    }

    public Job(){}
    
    public Job(String jobId){
    	this();
    	this.jobId = jobId;
    }

    public Job(String jobId, String jobTitle) {
        this(jobId);
        this.jobTitle = jobTitle;
    }

    public Job(String jobId, String jobTitle, double minSalary) {
        this(jobId, jobTitle);
        this.minSalary = minSalary;
    }

    public Job(String jobId, String jobTitle, double minSalary, double maxSalary) {
        this(jobId, jobTitle, minSalary);
        this.maxSalary = maxSalary;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(double minSalary) {
        this.minSalary = minSalary;
    }

    public double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(double maxSalary) {
        this.maxSalary = maxSalary;
    }


}
