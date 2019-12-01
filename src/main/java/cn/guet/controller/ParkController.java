package cn.guet.controller;

import bean.Car;
import cn.guet.service.ParkingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by WIN 10 on 2018/12/20.
 */

@RequestMapping("/parking")
@Controller
public class ParkController {

    @Resource(name="parkingService")
    ParkingService parkingService;

    @RequestMapping("/main")
    public String checkTheRest(Model model) {
        int rest = parkingService.rest();
        model.addAttribute("rest",Integer.valueOf(rest));
        return "park";
    }

    @RequestMapping("/enter")
    public String enter(Model model,Car car) {
        Car car1 = parkingService.enter(car);
        if(car1 == null) {
            return "failure";
        }else {
            model.addAttribute("Car",car1);
            return "success";
        }
    }

    @RequestMapping("/out")
    public String out(Model model,String license) {
        Car car1 = parkingService.out(license);
        if(car1 == null) {
            model.addAttribute("error","车辆不存在，请重新输入牌照");
            return "out";
        }else {
            model.addAttribute("Car",car1);
            return "outSuccess";
        }
    }

    @RequestMapping("/exit")
    public String exit() {
        return "out";
    }
}
