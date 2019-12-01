package cn.guet.dao.impl;

import bean.Car;
import bean.Streeting;
import cn.guet.dao.StreetDao;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

/**
 * Created by WIN 10 on 2018/11/28.
 */

@Repository("streetDao")
public class StreetDaoImpl implements StreetDao{

    Streeting s = new Streeting(new LinkedList<Car>());

    @Override
    public int checkTheSize() {
        return s.street.size();
    }

    @Override
    public Car enter(Car car) {
        s.street.addLast(car);
        return car;
    }

    @Override
    public Car ifExist(String license) {
        LinkedList<Car> temp = new LinkedList<>();
        LinkedList<Car> s1 = s.street;
        Car car = null;
        while (s1.size()!=0) {
            Car car1 = null;
            car1 = s1.getFirst();
            s1.removeFirst();
            temp.addLast(car1);
            if(license.equals(car1.getLicense())) {
                car = car1;
            }
        }
        s.street = temp;
        return car;
    }

    @Override
    public Car out(String license) {
        LinkedList<Car> temp = new LinkedList<>();
        LinkedList<Car> s1 = s.street;
        Car car = null;
        Car car1 = null;
        while (s1.size()!=0) {
            car1 = s1.getFirst();
            s1.removeFirst();
            if(license.equals(car1.getLicense())) {
                car = car1;
                break;
            }
            temp.addLast(car1);
        }
        while (s1.size()!=0) {
            car1 = s1.getFirst();
            car1.setLocation(car1.getLocation()-1);
            s1.removeFirst();
            temp.addLast(car1);
        }
        s.street = temp;
        return car;
    }

    @Override
    public Car firstOut() {
        Car car = s.street.getFirst();
        s.street.removeFirst();
        for (Car car1:s.street) {
            car1.setLocation(car1.getLocation()-1);
        }
        return car;
    }
}
