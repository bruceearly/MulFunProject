package com.ebest.mijia.heapcleartest.Fragments;

import android.content.Context;
import android.gesture.Gesture;
import android.gesture.GestureOverlayView;
import android.gesture.GestureStore;
import android.gesture.GestureStroke;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ebest.mijia.heapcleartest.MainActivity;
import com.ebest.mijia.heapcleartest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mijia on 16/7/20.
 */
public class MainFragment extends Fragment implements GestureOverlayView.OnGesturePerformedListener, GestureOverlayView.OnGesturingListener {
    FrameLayout frameLayout;
    boolean isshown;
    ViewPager viewpager;
    private GestureOverlayView mDrawGestureView;
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        final MainActivity activity = (MainActivity) getActivity();
        mDrawGestureView = (GestureOverlayView) v.findViewById(R.id.gesture);
        imageView = (ImageView) v.findViewById(R.id.iv);
        imageView.setRotation(180);
        //设置手势可多笔画绘制，默认情况为单笔画绘制
        mDrawGestureView.setGestureStrokeType(GestureOverlayView.GESTURE_STROKE_TYPE_MULTIPLE);
        //设置手势的颜色(蓝色)
        mDrawGestureView.setGestureColor(gestureColor(android.R.color.background_dark));
        //设置还没未能形成手势绘制是的颜色(红色)
        mDrawGestureView.setUncertainGestureColor(gestureColor(android.R.color.background_light));
        //设置手势的粗细
        mDrawGestureView.setGestureStrokeWidth(10);

        mDrawGestureView.setRotation(180);
        /*手势绘制完成后淡出屏幕的时间间隔，即绘制完手指离开屏幕后相隔多长时间手势从屏幕上消失；
         * 可以理解为手势绘制完成手指离开屏幕后到调用onGesturePerformed的时间间隔
         * 默认值为420毫秒，这里设置为2秒
         */
        mDrawGestureView.setFadeOffset(2000);

        //绑定监听器
        mDrawGestureView.addOnGesturePerformedListener(this);
        mDrawGestureView.addOnGesturingListener(this);
        return v;
    }

    List<View> views = new ArrayList<>();

    private int gestureColor(int resId) {
        return getResources().getColor(resId);
    }

    private void showMessage(String s) {
        Toast.makeText(this.getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
//        showMessage("手势绘制完成");
//        GestureStroke gestureStore = new GestureStroke();
        System.out.println("imageView.getWidth()=" + imageView.getWidth());
        System.out.println("imageView.getHeight()=" + imageView.getHeight());
        Bitmap bitmap = gesture.toBitmap(imageView.getWidth(), imageView.getHeight() , 100, Color.RED);
        if (null != bitmap)
            imageView.setImageBitmap(bitmap);
    }

    @Override
    public void onGesturingStarted(GestureOverlayView overlay) {
//        overlay.setGestureStrokeWidth(40);
//        showMessage("正在绘制手势");
    }

    @Override
    public void onGesturingEnded(GestureOverlayView overlay) {
//        showMessage("结束正在绘制手势");
    }
}
