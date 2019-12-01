package cn.guet.dao;

import bean.Plane;
import bean.PlaneSimple;

import java.util.LinkedList;

/**
 * Created by WIN 10 on 2018/11/30.
 */
public interface FlightDao {

    public Plane ifExist(PlaneSimple planeSimple);
    public Plane addFlight(Plane plane);
    public Plane delFlight(String flightNumber,String planeNumber);
    public LinkedList findAll();
}
