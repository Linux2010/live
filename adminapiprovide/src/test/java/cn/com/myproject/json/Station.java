package cn.com.myproject.json;

import java.util.List;

/**
 * Created by liyang-macbook on 2017/8/28.
 */
public class Station {
    private String stationID;
    private String stationName;
    private List<City> city;

    public String getStationID() {
        return stationID;
    }

    public void setStationID(String stationID) {
        this.stationID = stationID;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public List<City> getCity() {
        return city;
    }

    public void setCity(List<City> city) {
        this.city = city;
    }
}
