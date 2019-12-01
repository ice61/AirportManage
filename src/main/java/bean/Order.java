package bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by WIN 10 on 2018/11/30.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    String name;
    String number;
    String flightNumber;
    String planeNumber;
    String sort;
    int location = -1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getPlaneNumber() {
        return planeNumber;
    }

    public void setPlaneNumber(String planeNumber) {
        this.planeNumber = planeNumber;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
}
