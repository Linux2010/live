package cn.com.myproject.json;

import java.util.List;

/**
 * Created by liyang-macbook on 2017/8/28.
 */
public class Country1 {
    private String countryID;
    private String countryName;
    private List<Station> station;

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<Station> getStation() {
        return station;
    }

    public void setStation(List<Station> station) {
        this.station = station;
    }
}
