package com.huaan.javabasic.interview;

import java.util.Observable;
import java.util.Observer;

public class Interview1_1 {
    public static void main(String[] args) {
        Baby baby = new Baby("小宝宝", 9);
        baby.cry();
    }

}

class Baby extends Observable {
    private String name;
    private int hungery;

    public Baby(String name, int hungery) {
        this.name = name;
        this.hungery = hungery;
        // 增加观察者
        addObserver(new Parent());
    }

    @Override
    public String toString() {
        return "Baby{" +
                "name='" + name + '\'' +
                '}';
    }

    /**
     * baby 哭了
     */
    public void cry() {
        if (hungery < 100) {
            System.out.println(name + "饿了，开始哭泣！");
            // 必须调用这个方法才会真正通知, 查看notifyObservers方法的实现
            setChanged();
            notifyObservers();
        }

    }


}
class Parent implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        // Observable 是被观察者对象，可以知道是哪个被观察被更新了
        if (o instanceof Baby) {
            System.out.println(o + "哭了，需要照顾！");
        }
    }
}
