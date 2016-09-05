package com.ebest.mijia.heapcleartest.Views;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.ebest.mijia.heapcleartest.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mijia on 16/9/2.
 */
public class AnimationGroupView extends View {
    Context context;

    public AnimationGroupView(Context context) {
        super(context);
        init(context);
    }

    public AnimationGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AnimationGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AnimationGroupView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);

    }


    private void init(Context context) {
        System.out.println("init");
        this.context = context;
        getDisplayWH();
    }

    float screenWidth;
    float screenHeight;

    private void getDisplayWH() {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();
        screenHeight = wm.getDefaultDisplay().getHeight();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    int sweepAngle = 240;
    int startAngle = 90;
    Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            postInvalidate();
        }
    };

    List<Float> ptsList = new ArrayList<>();
    float x = -35, y = 0;

    float hookCurrentXCoordinate;
    float hookCurrentYCoordinate;
    final RectF oval2 = new RectF(60, 100, 200, 240);// 设置个新的长方形，扫描测量

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint cyclePaint = new Paint();
        cyclePaint.setColor(getResources().getColor(R.color.lightGreen));
        cyclePaint.setStrokeWidth(5);
        cyclePaint.setAntiAlias(true);
        cyclePaint.setStyle(Paint.Style.STROKE);

        canvas.drawCircle(oval2.centerX(), oval2.centerY(), oval2.right - oval2.centerX(), cyclePaint);


        Paint deepArcPaint = new Paint(cyclePaint);
        deepArcPaint.setColor(getResources().getColor(R.color.deepGreen));


        Paint hookPaint = new Paint(cyclePaint);
        hookPaint.setColor(getResources().getColor(R.color.deepGreen));

        float cx = oval2.centerX();
        float cy = oval2.centerY();
        float yTop = (cy - oval2.top) / 2;
        float yBottom = oval2.bottom;
        float xLeft = (cx - oval2.left) / 2;
        float xRight = (oval2.right - cx) / 2;
        if (hookCurrentXCoordinate == 0) {
            hookCurrentXCoordinate = cx + x;
            hookCurrentYCoordinate = cy;
        }
        //如果有2组以上(每组4个)的元素，再存一组坐标的时候，将上一组的最后一小组（2个）作为下一组的第一组元素，以上线能连起来
        if (ptsList.size() > 0 && ptsList.size() % 4 == 0) {
            int ptsListLength = ptsList.size();
            ptsList.add(ptsList.get(ptsListLength - 2));
            ptsList.add(ptsList.get(ptsListLength - 1));
        }
        ptsList.add(hookCurrentXCoordinate);
        ptsList.add(hookCurrentYCoordinate);


        float[] points = new float[ptsList.toArray(new Float[0]).length];
        int i = 0;
        for (float f : ptsList.toArray(new Float[0])) {
            points[i++] = f;
        }
        canvas.drawLines(points, hookPaint);
        canvas.drawArc(oval2, startAngle, sweepAngle, false, deepArcPaint);

        sweepAngle -= 10;
        startAngle -= 10;
//        postInvalidate();
        if (sweepAngle > -240) {
            postInvalidate();

        } else {
            sweepAngle = 240;
            startAngle = 90;
            System.out.println("-------------------------"+points.length);
        }

//        canvas.drawLines(points, hookPaint);
//        canvas.drawArc(oval2, 90, sweepAngle, false, cyclePaint);
//        if (sweepAngle <= 360 - 30) {
//            sweepAngle += 30;
//
//            x += 70 / 12;
//            hookCurrentXCoordinate = cx + x;
//
//            if (hookCurrentXCoordinate < cx) {
//                hookCurrentYCoordinate += 35 / 6;
//            } else if (hookCurrentXCoordinate == cx) {
//                hookCurrentYCoordinate = cy + 35 - 3;
//            } else {
//                hookCurrentYCoordinate -= 70 / 6;
//            }
//            handler.sendEmptyMessageDelayed(1, 10);
//        } else {
//            sweepAngle = 0;
//            x = -30;
//            y = 0;
//            ptsList.clear();
//            hookCurrentXCoordinate = 0;
//            hookCurrentYCoordinate = 0;
//        }
        //  canvas.drawPath(path, hookPaint);
    }
}
