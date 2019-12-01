package cn.guet.service.impl;

import bean.Plane;
import bean.PlaneSimple;
import cn.guet.dao.FlightDao;
import cn.guet.service.ManageService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by WIN 10 on 2018/11/30.
 */
@Service("manageService")
public class ManageServiceImpl implements ManageService {
    @Resource(name = "flightDao")
    private FlightDao flightDao;

    @Override
    public Plane addFlight(PlaneSimple planeSimple) {
        Plane plane = null;
        plane = flightDao.ifExist(planeSimple);
        if(plane == null) {
            plane = new Plane();
            plane.setFlightNumber(planeSimple.getFlightNumber());
            plane.setPlaneNumber(planeSimple.getPlaneNumber());
            plane.setStartPoint(planeSimple.getStartPoint());
            plane.setEndPoint(planeSimple.getEndPoint());
            plane.setStartTime(planeSimple.getStartDate() + " " + planeSimple.getStartTime());
            plane.setEndTime(planeSimple.getEndDate() + " " + planeSimple.getEndTime());
            plane.setLocation(new int[planeSimple.getFirstNum() + planeSimple.getSecondNum() + planeSimple.getThirdNum()]);
            String source = planeSimple.getStartDate();
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(source);
                int day = date.getDay();
                String day1 = null;
                switch (day) {
                    case 0 :
                        day1 = "星期日";
                        break;
                    case 1 :
                        day1 = "星期一";
                        break;
                    case 2 :
                        day1 = "星期二";
                        break;
                    case 3 :
                        day1 = "星期三";
                        break;
                    case 4 :
                        day1 = "星期四";
                        break;
                    case 5 :
                        day1 = "星期五";
                        break;
                    case 6 :
                        day1 = "星期六";
                        break;
                }
                plane.setDay(day1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            plane.setFirstNum(planeSimple.getFirstNum());
            plane.setFirstRest(planeSimple.getFirstNum());
            plane.setSecondNum(planeSimple.getSecondNum());
            plane.setSecondRest(planeSimple.getSecondNum());
            plane.setThirdNum(planeSimple.getThirdNum());
            plane.setThirdRest(planeSimple.getThirdNum());
            plane.setSum(planeSimple.getFirstNum() + planeSimple.getSecondNum() + planeSimple.getThirdNum());
            plane.setSumRest(planeSimple.getFirstNum() + planeSimple.getSecondNum() + planeSimple.getThirdNum());
            plane.setList(new LinkedList<>());
            plane.setWait(new LinkedList<>());
            flightDao.addFlight(plane);
            return plane;
        }
        return null;
    }

    @Override
    public Plane delFlight(String flightNumber, String planeNumber) {
        Plane plane = null;
        plane = flightDao.delFlight(flightNumber,planeNumber);
        return plane;
    }

    @Override
    public String searchFlight() {
        LinkedList<JSONObject> list = new LinkedList<>();
        LinkedList<Plane>  allFlight = flightDao.findAll();
        for (Plane plane:allFlight) {
            JSONObject json = new JSONObject();
            json.put("flightNumber",plane.getFlightNumber());
            json.put("planeNumber",plane.getPlaneNumber());
            json.put("startPoint",plane.getStartPoint());
            json.put("endPoint",plane.getEndPoint());
            json.put("startTime",plane.getStartTime());
            json.put("endTime",plane.getEndTime());
            json.put("day",plane.getDay());
            json.put("sum",plane.getSum());
            json.put("rest",plane.getSumRest());
            list.add(json);
        }
        JSONObject allJson = new JSONObject();
        allJson.put("data",list);
        return allJson.toString();
    }
}
