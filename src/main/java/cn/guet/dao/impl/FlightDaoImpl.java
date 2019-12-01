package cn.guet.dao.impl;

import bean.Plane;
import bean.PlaneSimple;
import cn.guet.dao.FlightDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.LinkedList;

/**
 * Created by WIN 10 on 2018/11/30.
 */
@Repository("flightDao")
public class FlightDaoImpl implements FlightDao {
    @Resource(name = "allFlight")
    LinkedList<Plane> allFlight;

    @Override
    public Plane ifExist(PlaneSimple planeSimple) {
        String flightNumber = planeSimple.getFlightNumber();
        String planeNumber = planeSimple.getPlaneNumber();
        Plane plane = null;
        Plane plane1 = null;
        LinkedList<Plane> temp = new LinkedList<>();
        while(allFlight.size() != 0) {
            plane1 = allFlight.getFirst();
            if(flightNumber.equals(plane1.getFlightNumber()) && planeNumber.equals(plane1.getPlaneNumber())) {
                plane = plane1;
                break;
            }
            temp.addFirst(plane1);
            allFlight.removeFirst();
        }
        while (temp.size() != 0) {
            plane1 = temp.getFirst();
            temp.removeFirst();
            allFlight.addFirst(plane1);
        }
        return plane;
    }

    @Override
    public Plane addFlight(Plane plane) {
        allFlight.addFirst(plane);
        return plane;
    }

    @Override
    public Plane delFlight(String flightNumber, String planeNumber) {
        Plane plane = null;
        Plane plane1 = null;
        LinkedList<Plane> temp = new LinkedList<>();
        while (allFlight.size() != 0) {
            plane1 = allFlight.getFirst();
            allFlight.removeFirst();
            if(flightNumber.equals(plane1.getFlightNumber()) && planeNumber.equals(plane1.getPlaneNumber())) {
                plane = plane1;
                break;
            }
            temp.addFirst(plane1);
        }
        while (temp.size() != 0) {
            plane1 = temp.getFirst();
            allFlight.addFirst(plane1);
            temp.removeFirst();
        }
        return plane;
    }

    @Override
    public LinkedList findAll() {
        return allFlight;
    }


}
