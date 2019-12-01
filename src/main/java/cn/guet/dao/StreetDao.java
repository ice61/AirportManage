package cn.guet.dao;

import bean.Car;

/**
 * Created by WIN 10 on 2018/11/28.
 */
public interface StreetDao {

    public int checkTheSize();
    public Car enter(Car car);
    public Car ifExist(String license);
    public Car out(String license);
    public Car firstOut();
}
