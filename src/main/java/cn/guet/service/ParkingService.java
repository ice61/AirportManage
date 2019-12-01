package cn.guet.service;

import bean.Car;
import cn.guet.dao.ParkDao;

/**
 * Created by WIN 10 on 2018/11/26.
 */

public interface ParkingService {

    public int rest();
    public Car enter(Car car);
    public Car out(String license);
}
