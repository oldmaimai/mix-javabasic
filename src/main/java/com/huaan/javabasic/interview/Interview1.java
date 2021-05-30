package com.huaan.javabasic.interview;

import java.util.ArrayList;
import java.util.List;

public class Interview1 {
    public static void main(String[] args) {
        System.out.println(Math.round(12.5));
        System.out.println(Math.round(-12.6));
        System.out.println(fun(4));

        // 观察者模式
        ObserverPatternTest test = new ObserverPatternTest();
        test.test();
    }

    /**
     * 实现 n!
     *
     * @param num
     * @return
     */
    private static int fun(int num) {
        if (num > 1) {
            return num * fun(num - 1);
        } else {
            return 1;
        }
    }
}

class ObserverPatternTest {
    Observable wechatService = new WechatServer();
    User zhangsan = new User("zhangshan");
    User lisi = new User("lisi");
    User wangwu = new User("wangwu");

    public void test() {
        wechatService.registObserver(zhangsan);
        wechatService.registObserver(lisi);
        wechatService.registObserver(wangwu);

        wechatService.notifyObserver("php is the best");

        wechatService.removeObserver(lisi);
        wechatService.notifyObserver("java is the best");

    }
}
interface Observer {
    void update(String msg);
}

interface Observable {
    void registObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObserver(String msg);
}

class User implements Observer {
    private String name;
    private String msg;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String msg) {
        System.out.println(name + " 收到通知： " + msg);
    }
}

class WechatServer implements Observable {
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void registObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(String msg) {
        observers.forEach(o -> {
            o.update(msg);
        });

    }
}


