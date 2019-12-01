package cn.guet.service.impl;

import bean.Order;
import bean.Plane;
import cn.guet.dao.OrderDao;
import cn.guet.service.OrderService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;

/**
 * Created by WIN 10 on 2018/12/1.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource(name = "orderDao")
    private OrderDao orderDao;

    @Override
    public Order addOrder(Order order) {
        Order order1 = orderDao.ifExist(order);
        if(order1 == null) {
            String flightNumber = order.getFlightNumber();
            String planeNumber = order.getPlaneNumber();
            String sort = order.getSort();
            Plane plane = orderDao.findPlane(flightNumber,planeNumber);
            int rest = 0;
            if(sort.equals("头等舱"))
                rest = plane.getFirstRest();
            else if(sort.equals("公务舱"))
                rest = plane.getSecondRest();
            else
                rest = plane.getThirdRest();
            if(rest == 0) {
                orderDao.addWait(order);
            }else {
                int[] a = plane.getLocation();
                for(int i = 0; i<a.length; ++i) {
                    if(a[i] == 0) {
                        order.setLocation(i + 1);
                        orderDao.addList(order);
                        break;
                    }
                }
            }
            return order;
        }
        return null;
    }

    @Override
    public Plane detail(String flightNumber, String planeNumber) {
        Plane plane = orderDao.findPlane(flightNumber,planeNumber);
        return plane;
    }

    @Override
    public Order del(String flightNumber, String planeNumber, String number) {
        Order order = new Order();
        order.setFlightNumber(flightNumber);
        order.setPlaneNumber(planeNumber);
        order.setNumber(number);
        Order order1 = orderDao.delFromList(order);
        if(order1 == null) {
            order1 = orderDao.delFromWait(order);
        }else if(orderDao.findPlane(flightNumber,planeNumber).getWait().size() != 0) {
            Order order2 = orderDao.delFromWaitBySort(order1);
            if(order2 != null) {
                order2.setLocation(order1.getLocation());
                orderDao.addList(order2);
            }
        }
        return order1;
    }


}
