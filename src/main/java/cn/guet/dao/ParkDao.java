package cn.guet.dao;

import bean.Car;

/**
 * Created by WIN 10 on 2018/11/26.
 */
public interface ParkDao {

    public int checkTheRest();
    public int checkTheSize();
    public Car enter(Car car);
    public Car ifExist(String license);
    public Car out(String license);
}
