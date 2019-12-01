package cn.guet.service;

import bean.Order;
import bean.Plane;

/**
 * Created by WIN 10 on 2018/12/1.
 */
public interface OrderService {
    public Order addOrder(Order order);
    public Plane detail(String flightNumber,String planeNumber);
    public Order del(String flightNumber,String planeNumber,String number);
}
