package cn.guet.service;

import bean.Plane;
import bean.PlaneSimple;

import java.util.LinkedList;

/**
 * Created by WIN 10 on 2018/11/30.
 */
public interface ManageService {
    public Plane addFlight(PlaneSimple planeSimple);
    public Plane delFlight(String flightNumber,String planeNumber);
    public String searchFlight();
}
