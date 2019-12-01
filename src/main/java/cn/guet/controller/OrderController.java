package cn.guet.controller;

import bean.Order;
import bean.Plane;
import cn.guet.service.ManageService;
import cn.guet.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by WIN 10 on 2018/12/1.
 */
@RequestMapping("/order")
@Controller
public class OrderController {

    @Resource(name = "manageService")
    private ManageService manageService;

    @Resource(name = "orderService")
    private OrderService orderService;

    @RequestMapping("/main")
    public String addMain() {
        return "order";
    }

    @RequestMapping("/see")
    public String search() {
        return "order";
    }

    @RequestMapping("/search")
    public void searchAll(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String result = manageService.searchFlight();
        response.getWriter().write(result);
    }

    @RequestMapping("/add")
    public String add(Order order, Model model) {
        Order order1 = orderService.addOrder(order);
        if(order1 == null) {
            model.addAttribute("error","请勿重复提交");
            return "order";
        }else {
            model.addAttribute("order",order1);
            return "orderSuccess";
        }
    }

    @RequestMapping("/detail")
    @ResponseBody
    public Plane detail(@RequestBody Order order) {
        String flightNumber = order.getFlightNumber();
        String planeNumber = order.getPlaneNumber();
        Plane plane = orderService.detail(flightNumber,planeNumber);
        return plane;
    }

    @RequestMapping("/del")
    public void del(HttpServletResponse response ,HttpServletRequest request) throws IOException {
        String flightNumber = request.getParameter("flightNumber");
        String planeNumber = request.getParameter("planeNumber");
        String number = request.getParameter("number");
        Order order = orderService.del(flightNumber,planeNumber,number);
        if(order == null) {
            response.getWriter().write("faliure");
        }else {
            response.getWriter().write("success");
        }
    }
}
