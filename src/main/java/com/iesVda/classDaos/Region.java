package com.iesVda.classDaos;
public class Region {

    private int regionId;
    private String regionName;

    @Override
    public String toString() {
        return regionName + " (id " + regionId + ")";
    }

    public Region(){}

    public Region(int regionId){
        this.regionId = regionId;
    }

    public Region(int regionId, String regionName) {
        this.regionId = regionId;
        this.regionName = regionName;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
