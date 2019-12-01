package cn.guet.dao;

import bean.Order;
import bean.Plane;

import java.util.LinkedList;

/**
 * Created by WIN 10 on 2018/12/1.
 */
public interface OrderDao {
    public Order ifExist(Order order);
    public Order addList(Order order);
    public Order addWait(Order order);
    public Order delFromList(Order order);
    public Order delFromWait(Order order);
    public Order delFromWaitBySort(Order order);
    public int restOfFirst(Order order);
    public int restOfSecond(Order order);
    public int restOfThird(Order order);
    public Plane findPlane(String flightNumber,String planeNumber);
    public LinkedList findAll();

}
