package com.ObserverModel;

/**
 * Created by mijia on 16/8/8.
 */
public class ExecutionSub implements ExecutionImpl {
    int a;

    @Override
    public void update() {
        a++;
        System.out.println(this.hashCode() + "," + a);
    }
}
