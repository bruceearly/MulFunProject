package com.ObserverModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mijia on 16/8/8.
 */
public class EventBus implements ObserverImpl {
   private static EventBus instance;
    List<Object> objects = new ArrayList<>();

    public synchronized static EventBus getInstance() {
        if (instance == null) {
            instance = new EventBus();
        }
        return instance;
    }

    @Override
    public void regist(Object object) {
        if (!objects.contains(object))
            objects.add(object);
    }

    @Override
    public void unRegist(Object object) {
        if (objects.contains(object))
            objects.remove(object);
    }

    @Override
    public void post(Object object) {
        if (null == object) {
            for (int i = 0; i < objects.size(); i++) {
                ((ExecutionSub) objects.get(i)).update();
            }
        } else {
            if (!objects.contains(object))
                throw new IllegalArgumentException("unregist this event");

            ((ExecutionSub) object).update();

        }
    }
}
