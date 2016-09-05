package com.ObserverModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by mijia on 16/8/8.
 */
public interface ObserverImpl {

    void regist(Object object);

    void unRegist(Object object);

    void post(Object object);
}
