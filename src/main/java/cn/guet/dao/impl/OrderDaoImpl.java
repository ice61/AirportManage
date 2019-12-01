package cn.guet.dao.impl;

import bean.Order;
import bean.Plane;
import cn.guet.dao.OrderDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.LinkedList;

/**
 * Created by WIN 10 on 2018/12/1.
 */
@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {

    @Resource(name = "allFlight")
    LinkedList<Plane> allFlight;


    @Override
    public Order ifExist(Order order) {
        Plane plane1 = null;
        Order order1 = null;
        Order order2 = null;
        for (Plane plane : allFlight) {
            if(plane.getFlightNumber().equals(order.getFlightNumber()) && plane.getPlaneNumber().equals(order.getPlaneNumber())) {
                plane1 = plane;
                break;
            }
        }
        LinkedList<Order> temp = new LinkedList<>();
        LinkedList<Order> list = plane1.getList();
        LinkedList<Order> wait = plane1.getWait();
        while(list.size() != 0) {
            order1 = list.getFirst();
            if(order.getNumber().equals(order1.getNumber())) {
                order2 = order1;
                break;
            }
            list.removeFirst();
            temp.addFirst(order1);
        }
        while (temp.size() != 0) {
            order1 = temp.getFirst();
            temp.removeFirst();
            list.addFirst(order1);
        }
        if(order2 != null) {
            return order2;
        }else {
            while (wait.size() != 0) {
                order1 = wait.getFirst();
                if(order1.getNumber().equals(order.getNumber())) {
                    order2 = order1;
                }
                wait.removeFirst();
                temp.addLast(order1);
            }
            plane1.setWait(temp);
        }
        return order2;
    }

    @Override
    public Order addList(Order order) {
        for (Plane plane : allFlight) {
            if(plane.getPlaneNumber().equals(order.getPlaneNumber()) && plane.getFlightNumber().equals(order.getFlightNumber())) {
                LinkedList<Order> list = plane.getList();
                list.addFirst(order);
                int[] a = plane.getLocation();
                a[order.getLocation() -1] = 1;
                plane.setSumRest(plane.getSumRest() - 1);
                switch (order.getSort()) {
                    case "头等舱":
                        plane.setFirstRest(plane.getFirstRest()-1);
                        break;
                    case "公务舱":
                        plane.setSecondRest(plane.getSecondRest()-1);
                        break;
                    case "经济舱":
                        plane.setThirdRest(plane.getThirdRest()-1);
                        break;
                }
                break;
            }
        }
        return order;
    }

    @Override
    public Order addWait(Order order) {
        for (Plane plane : allFlight) {
            if(plane.getPlaneNumber().equals(order.getPlaneNumber()) && plane.getFlightNumber().equals(order.getFlightNumber())) {
                LinkedList<Order> wait = plane.getWait();
                wait.addLast(order);
                break;
            }
        }
        return null;
    }

    @Override
    public Order delFromList(Order order) {
        Plane plane1 = null;
        for (Plane plane:allFlight) {
            if(plane.getFlightNumber().equals(order.getFlightNumber()) && plane.getPlaneNumber().equals(order.getPlaneNumber())) {
                plane1 = plane;
                break;
            }
        }
        Order order1 = null;
        Order order2 = null;
        LinkedList<Order> temp = new LinkedList<>();
        LinkedList<Order> list = plane1.getList();
        while (list.size() != 0) {
            order1 = list.getFirst();
            list.removeFirst();
            if(order1.getNumber().equals(order.getNumber())) {
                order2 = order1;
                switch (order2.getSort()) {
                    case "头等舱":
                        plane1.setFirstRest(plane1.getFirstRest()+1);
                        break;
                    case "公务舱":
                        plane1.setSecondRest(plane1.getSecondRest()+1);
                        break;
                    case "经济舱":
                        plane1.setThirdRest(plane1.getThirdRest()+1);
                        break;
                }
                plane1.setSumRest(plane1.getSumRest()+1);
                int[] a = plane1.getLocation();
                a[order2.getLocation()-1] = 0;
                break;
            }
            temp.addFirst(order1);
        }
        while (temp.size() != 0) {
            order1 = temp.getFirst();
            temp.removeFirst();
            list.addFirst(order1);
        }
        return order2;
    }

    @Override
    public Order delFromWait(Order order) {
        Plane plane1 = null;
        for (Plane plane:allFlight) {
            if(plane.getFlightNumber().equals(order.getFlightNumber()) && plane.getPlaneNumber().equals(order.getPlaneNumber())) {
                plane1 = plane;
                break;
            }
        }
        LinkedList<Order> wait = plane1.getWait();
        LinkedList<Order> temp = new LinkedList<>();
        Order order1 = null;
        Order order2 = null;
        while(wait.size() != 0) {
            order1 = wait.getFirst();
            wait.removeFirst();
            if(order1.getNumber().equals(order.getNumber())) {
                order2 = order1;
                break;
            }
            temp.addLast(order1);
        }
        while (wait.size() != 0) {
            temp.addLast(wait.getFirst());
            wait.removeFirst();
        }
        plane1.setWait(temp);
        return order2;
    }

    @Override
    public Order delFromWaitBySort(Order order) {
        Plane plane1 = null;
        for (Plane plane:allFlight) {
            if(plane.getFlightNumber().equals(order.getFlightNumber()) && plane.getPlaneNumber().equals(order.getPlaneNumber())) {
                plane1 = plane;
                break;
            }
        }
        LinkedList<Order> wait = plane1.getWait();
        LinkedList<Order> temp = new LinkedList<>();
        Order order1 = null;
        Order order2 = null;
        while(wait.size() != 0) {
            order1 = wait.getFirst();
            wait.removeFirst();
            if(order1.getSort().equals(order.getSort())) {
                order2 = order1;
                break;
            }
            temp.addLast(order1);
        }
        while (wait.size() != 0) {
            temp.addLast(wait.getFirst());
            wait.removeFirst();
        }
        plane1.setWait(temp);
        return order2;
    }

    @Override
    public int restOfFirst(Order order) {
        int rest = 0;
        for (Plane plane:allFlight) {
            if(plane.getFlightNumber().equals(order.getFlightNumber()) && plane.getPlaneNumber().equals(order.getPlaneNumber())) {
                rest = plane.getFirstRest();
            }
        }
        return rest;
    }

    @Override
    public int restOfSecond(Order order) {
        int rest = 0;
        for (Plane plane:allFlight) {
            if(plane.getFlightNumber().equals(order.getFlightNumber()) && plane.getPlaneNumber().equals(order.getPlaneNumber())) {
                rest = plane.getSecondRest();
            }
        }
        return rest;
    }

    @Override
    public int restOfThird(Order order) {
        int rest = 0;
        for (Plane plane:allFlight) {
            if(plane.getFlightNumber().equals(order.getFlightNumber()) && plane.getPlaneNumber().equals(order.getPlaneNumber())) {
                rest = plane.getThirdRest();
            }
        }
        return rest;
    }

    @Override
    public Plane findPlane(String flightNumber,String planeNumber) {
        Plane plane1 = null;
        for (Plane plane:allFlight) {
            if(flightNumber.equals(plane.getFlightNumber()) && planeNumber.equals(plane.getPlaneNumber())) {
                plane1 = plane;
                break;
            }
        }
        return plane1;
    }

    @Override
    public LinkedList findAll() {
        return allFlight;
    }




}
