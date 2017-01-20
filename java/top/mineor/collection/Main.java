package top.mineor.collection;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mineor on 2017/1/19.
 */
public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Integer ds = list.set(0, 11);
        System.out.println(ds);
    }
}
