package bean;

import java.util.LinkedList;

/**
 * Created by WIN 10 on 2018/11/29.
 */
public class Plane {
    private String flightNumber;
    private String planeNumber;
    private String startPoint;
    private String endPoint;
    private String startTime;
    private String endTime;
    private String day;
    private int sum;
    private int[] location;


    private int sumRest;
    private int firstNum;
    private int firstRest;
    private int secondNum;
    private int secondRest;
    private int thirdNum;
    private int thirdRest;
    private LinkedList<Order> list;
    private LinkedList<Order> wait;

    public int[] getLocation() {
        return location;
    }

    public void setLocation(int[] location) {
        this.location = location;
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

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getSumRest() {
        return sumRest;
    }

    public void setSumRest(int sumRest) {
        this.sumRest = sumRest;
    }

    public int getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(int firstNum) {
        this.firstNum = firstNum;
    }

    public int getFirstRest() {
        return firstRest;
    }

    public void setFirstRest(int firstRest) {
        this.firstRest = firstRest;
    }

    public int getSecondNum() {
        return secondNum;
    }

    public void setSecondNum(int secondNum) {
        this.secondNum = secondNum;
    }

    public int getSecondRest() {
        return secondRest;
    }

    public void setSecondRest(int secondRest) {
        this.secondRest = secondRest;
    }

    public int getThirdNum() {
        return thirdNum;
    }

    public void setThirdNum(int thirdNum) {
        this.thirdNum = thirdNum;
    }

    public int getThirdRest() {
        return thirdRest;
    }

    public void setThirdRest(int thirdRest) {
        this.thirdRest = thirdRest;
    }

    public LinkedList<Order> getList() {
        return list;
    }

    public void setList(LinkedList<Order> list) {
        this.list = list;
    }

    public LinkedList<Order> getWait() {
        return wait;
    }

    public void setWait(LinkedList<Order> wait) {
        this.wait = wait;
    }
}
