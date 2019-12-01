package bean;

import org.springframework.stereotype.Component;

import java.util.LinkedList;

/**
 * Created by WIN 10 on 2018/11/26.
 */

public class Parking {
    //theRest表示剩余的空间
     public int theRest;

     public LinkedList<Car> park;

    public Parking(int theRest, LinkedList<Car> park) {
        this.theRest = theRest;
        this.park = park;
    }

    public Parking() {
    }
}
