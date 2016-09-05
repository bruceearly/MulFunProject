package com.ObserverModel;

import java.awt.Event;

/**
 * Created by mijia on 16/8/8.
 */
public class MyTestClass {

    public void test(){}


    public static void main(String[] args) {
        EventBus eventBus = EventBus.getInstance();
        ExecutionSub sub1 = new ExecutionSub();
        ExecutionSub sub2 = new ExecutionSub();
        ExecutionSub sub3 = new ExecutionSub();
        eventBus.regist(sub1);
        eventBus.regist(sub2);
        eventBus.regist(sub3);

        eventBus.post(null);

        eventBus.unRegist(sub3);

        for (int i = 0; i < 3; i++) {
            eventBus.post(sub1);
        }

        eventBus.post(null);

        ThreadLocal<MyTestClass> local = new ThreadLocal<>();
        local.get();
    }
}
