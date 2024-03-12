package com.iesVda.classDaos;

public class Country {

	private String countryId;
	private String countryName;
	private int regionid;
	private Region region;

	@Override
	public String toString() {
		return countryName + " (id " + countryId + ")" + "\n\t\t\tRegión: " + region;
	}

	public Country() {
	}

	public Country(String countryId) {
		this();
		this.countryId = countryId;
	}

	public Country(String countryId, String countryName, Region region) {
		this(countryId);
		this.countryName = countryName;
		this.region = region;
	}

	public Country(String countryId, String countryName, int regionid) {
		this(countryId);
		this.countryName = countryName;
		this.regionid = regionid;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public int getRegionid() {
		return regionid;
	}

	public void setRegionid(int regionid) {
		this.regionid = regionid;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

}
