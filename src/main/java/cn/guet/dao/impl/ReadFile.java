package cn.guet.dao.impl;


import bean.Plane;
import bean.PlaneSimple;
import cn.guet.dao.FlightDao;
import cn.guet.service.ManageService;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.*;
import java.util.LinkedList;

/**
 * Created by WIN 10 on 2018/12/5.
 */
public class ReadFile implements ServletContextListener {

    private FlightDao flightDao;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ManageService manageService = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext()).getBean(ManageService.class);
        flightDao = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext()).getBean(FlightDao.class);
        String classPath = this.getClass().getResource("/").getPath();
        try {
            BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(classPath + "Flight.txt"),"utf-8"));
            String str = "";
            while((str = bfr.readLine()) != null) {
                String[] arr = str.split(" ");
                PlaneSimple p = new PlaneSimple();
                p.setPlaneNumber(arr[0]);
                p.setFlightNumber(arr[1]);
                p.setStartPoint(arr[2]);
                p.setEndPoint(arr[3]);
                p.setStartDate(arr[4]);
                p.setStartTime(arr[5]);
                p.setEndDate(arr[6]);
                p.setEndTime(arr[7]);
                p.setFirstNum(Integer.valueOf(arr[8]));
                p.setSecondNum(Integer.valueOf(arr[9]));
                p.setThirdNum(Integer.valueOf(arr[10]));
                manageService.addFlight(p);
            }
            bfr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
       //FlightDao flightDao = (FlightDao) WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext()).getBean("");
        LinkedList<Plane> temp = flightDao.findAll();
        String classPath = this.getClass().getResource("/").getPath();
        try {
            BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(classPath + "Flight.txt"),"utf-8"));
            while(temp.size()!= 0) {
                String str = "";
                Plane p = temp.getFirst();
                temp.removeFirst();
                str = p.getPlaneNumber() +" " +  p.getFlightNumber() + " " + p.getStartPoint() +" " +  p.getEndPoint() +" " +  p.getStartTime() + " " + p.getEndTime()
                         + " " + p.getFirstNum() + " " + p.getSecondNum() + " " + p.getThirdNum();
                bfw.write(str);
                bfw.newLine();
            }
            bfw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
