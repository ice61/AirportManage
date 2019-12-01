package cn.guet.service.impl;

import bean.Car;
import cn.guet.dao.ParkDao;
import cn.guet.dao.StreetDao;
import cn.guet.service.ParkingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by WIN 10 on 2018/11/26.
 */
@Service("parkingService")
public class ParkingServiceImpl implements ParkingService {
    @Resource(name = "parkDao")
    private ParkDao parkDao;
    @Resource(name="streetDao")
    private StreetDao streetDao;

    @Override
    public int rest() {
        return parkDao.checkTheRest();
    }

    @Override
    public Car enter(Car car) {
        if(parkDao.ifExist(car.getLicense()) != null)
            return null;
        if(streetDao.ifExist(car.getLicense()) != null)
            return null;
        if(parkDao.checkTheRest()==0) {
            Date d = new Date();
            String date = d.toLocaleString();
            car.setLocation(streetDao.checkTheSize()+1);
            car.setDate(date);
            car.setSort("便道");
            streetDao.enter(car);
        }else {
            Date d = new Date();
            String date = d.toLocaleString();
            car.setLocation(parkDao.checkTheSize()+1);
            car.setDate(date);
            car.setTime(d.getTime());
            car.setSort("停车场");
            parkDao.enter(car);
        }
        return car;
    }

    @Override
    public Car out(String license) {
        Car car = null;
        car = streetDao.out(license);
        if(car != null) {
            return car;
        }else {
            car = parkDao.out(license);
            if(car == null)
                return null;
            else {
                if(parkDao.checkTheRest()!=0 && streetDao.checkTheSize()!=0) {
                    Car car1 = streetDao.firstOut();
                    car1.setTime(new Date().getTime());
                    car1.setSort("停车场");
                    car1.setLocation(parkDao.checkTheSize()+1);
                    parkDao.enter(car1);
                }
                Date date = new Date();
                long end = date.getTime();
                long start = car.getTime();
                int time = (int) ((end-start)/(1000*60));
                car.setTime(time);
                car.setMoney(time*10);
                return car;
            }
        }
    }


}


