package com.iesVda.classDaos;
public class Department {

    private int departmentId;
    private String departmentName;
    private int managerId;
    private Employee manager;
    private int locationId;
    private Location location;

    @Override
    public String toString() {
        return "\n\t\t" + departmentName + " (id " + departmentId + ")" +
                "\n\t\tNombre del manager: " + getManager(managerId) + "(id "+ managerId + ")" +
                "\n\t\tLocalización " + location;
    }

    public String getManager(int managerId){
        return "";
    }

    public Department(){}
    
    public Department(int departmentId) {
    	this.departmentId = departmentId;
    }
    
    

    public Department(int departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public Department(int departmentId, String departmentName, Location location) {
        this(departmentId, departmentName);
        this.location = location;
    }

    public Department(int departmentId, String departmentName, Location location, int managerId) {
        this(departmentId, departmentName, location);
        this.location = location;
    }

    public Department(int departmentId, String departmentName, int locationId) {
        this(departmentId, departmentName);
        this.managerId = managerId;
    }

    public Department(int departmentId, String departmentName, int locationId, int managerId) {
        this(departmentId, departmentName, locationId);
        this.managerId = managerId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}
    
}
