package cn.guet.controller;

import bean.Plane;
import bean.PlaneSimple;
import cn.guet.service.ManageService;
import org.omg.CORBA.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by WIN 10 on 2018/11/30.
 */
@RequestMapping(value = "/manage")
@Controller
public class ManageController {


    @Resource(name = "manageService")
    ManageService manageService;

    @RequestMapping("/main")
    public String addMain() {
        return "addFlight";
    }

    @RequestMapping("/see")
    public String search() {
        return "searchFlight";
    }

    @RequestMapping("/add")
    public String add(PlaneSimple planeSimple , Model model) {
        Plane plane = manageService.addFlight(planeSimple);
        if(plane == null) {
            model.addAttribute("error","航班已存在，请勿重复添加");
            return "addFlight";
        }else {
            model.addAttribute("Plane",plane);
            return "addSuccess";
        }
    }

    @RequestMapping("/search")
    public void search(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String result = manageService.searchFlight();
        response.getWriter().write(result);
    }

    @RequestMapping("/del")
    public void del(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String flightNumber = request.getParameter("flightNumber");
        String planeNumber = request.getParameter("planeNumber");
        if(manageService.delFlight(flightNumber,planeNumber) != null) {
            response.getWriter().write("success");
        }else
            response.getWriter().write("failure");
    }

}
