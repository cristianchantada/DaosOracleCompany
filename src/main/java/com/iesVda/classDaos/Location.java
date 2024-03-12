package com.iesVda.classDaos;
public class Location {

    private int locationId;
    private String streetAddress;
    private long postalCode;
    private String city;
    private String stateProvice;
    private String countryId;
    private Country country;

    @Override
    public String toString() {
        return "(id " + locationId + "):"
                + "\n\t\t\tCalle: " + streetAddress
                + "\n\t\t\tCódigo postal: " + postalCode
                + "\n\t\t\tCiudad: " + city
                + "\n\t\t\tEstado/Provincia: " + stateProvice
                + "\n\t\t\tPaís: " + country;
    }

    public Location(){}
    
    public Location(int locationId) {
    	this.locationId = locationId;
    }

    public Location(int locationId, String streetAddress, int postalCode, String city, String stateProvice, Country country) {
        this.locationId = locationId;
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.city = city;
        this.stateProvice = stateProvice;
        this.country = country;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public long getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(long postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvice() {
        return stateProvice;
    }

    public void setStateProvice(String stateProvice) {
        this.stateProvice = stateProvice;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
