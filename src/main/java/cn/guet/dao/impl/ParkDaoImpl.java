package cn.guet.dao.impl;

import bean.Car;
import bean.Parking;
import cn.guet.dao.ParkDao;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

/**
 * Created by WIN 10 on 2018/11/26.
 */
@Repository("parkDao")
public class ParkDaoImpl implements ParkDao {

    Parking p = new Parking(5,new LinkedList<Car>());

    @Override
    public int checkTheRest() {
        return p.theRest;
    }

    @Override
    public int checkTheSize() {
        return p.park.size();
    }

    @Override
    public Car enter(Car car) {
        p.park.addFirst(car);
        p.theRest--;
        return car;
    }

    @Override
    public Car ifExist(String license) {
        //创建一个临时的栈temp用来辅助查找停车场内的车辆
        LinkedList<Car> temp = new LinkedList<>();
        //创建一个栈指向p的park，方便减少代码书写长度
        LinkedList<Car> p1 = p.park;
        Car car;

        /*
        * 当停车场不为空时，取栈顶元素并删除p1中的栈顶元素并添加进临时栈中
        * 如果找到牌照与license相匹配的车辆，先将临时栈中的元素全部加入停车场中，然后返回所匹配的car
        * 如果停车场为空还没有找寻到，则将车辆重新导入停车场后返回null
        * */
        while(p1.size()!=0) {
            car = p1.getFirst();
            if(license.equals(car.getLicense())) {
                while(temp.size()!=0) {
                    p1.addFirst(temp.getFirst());
                    temp.removeFirst();
                }
                return car;
            }
            p1.removeFirst();
            temp.addFirst(car);
        }
        while(temp.size()!=0) {
            p1.addFirst(temp.getFirst());
            temp.removeFirst();
        }
        return null;
    }

    @Override
    public Car out(String license) {
        LinkedList<Car> temp = new LinkedList<>();
        LinkedList<Car> p1 = p.park;
        Car car = null;
        Car car1 = null;
        while (p1.size()!=0) {
            car1 = p1.getFirst();
            p1.removeFirst();
            if(license.equals(car1.getLicense())) {
                car = car1;
                break;
            }
            temp.addFirst(car1);
        }
        while (temp.size()!=0) {
            car1 = temp.getFirst();
            temp.removeFirst();
            if(car != null) {
                car1.setLocation(car1.getLocation()-1);
            }
            p1.addFirst(car1);
        }
        if(car != null)
            p.theRest++;
        return car;
    }


//    Parking p = new Parking(5,new LinkedList<>());
//
//    //此方法用于向停车场中添加车辆
//    @Override
//    public int add(Car car) {
//        p.park.addFirst(car);
//        p.size--;
//        return p.park.size();
//    }
//
//   @Override
//   //check1返回当前停车场内车辆，即为p.park.size()
//   public int check1() {
//        return p.park.size();
//   }
//
//   @Override
//   //check2返回当前停车场的剩余车位，即为p.size
//    public int check2() {
//        return p.size;
//    }
//
//    //查找车辆
//    @Override
//    public Car find(String license) {
//        LinkedList<Car> temp = new LinkedList<>();
//        LinkedList<Car> p1 = p.park;
//        Car car;
//        while(p1.size()!=0) {
//            car = p1.getFirst();
//            if(license.equals(car.getLicense())) {
//                while(temp.size()!=0) {
//                    p1.addFirst(temp.getFirst());
//                    temp.removeFirst();
//                }
//                return car;
//            }
//            p1.removeFirst();
//            temp.addFirst(car);
//        }
//        while(temp.size()!=0) {
//            p1.addFirst(temp.getFirst());
//            temp.removeFirst();
//        }
//        return null;
//    }
//
//    //车辆离开
//    @Override
//    public Car del(String license) {
//        LinkedList<Car> temp = new LinkedList<>();
//        LinkedList<Car> p1 = p.park;
//        Car car = null;
//        while(p1.size()!=0) {
//            car = p1.getFirst();
//            p1.removeFirst();
//            if(license.equals(car.getLicense())) {
//                break;
//            }
//            temp.addFirst(car);
//        }
//        while(temp.size()!=0) {
//            Car car1 = temp.getFirst();
//            car1.setLocation(car1.getLocation()-1);
//            p1.addFirst(car1);
//            temp.removeFirst();
//        }
//        if(car != null)
//            p.size++;
//        return car;
//    }
//
//
//    public void setP(Parking p) {
//        this.p = p;
//    }
}
