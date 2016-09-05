package com.ebest.mijia.heapcleartest;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.ebest.mijia.heapcleartest.Views.AnimationGroupView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.agv)
    AnimationGroupView agv;

    @BindView(R.id.btn_refresh)
    Button btn;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        agv = (AnimationGroupView) findViewById(R.id.agv);
        ((Button) findViewById(R.id.btn_refresh)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agv.postInvalidate();
            }
        });
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo("ebest.SFA.Android", 0);
            String version = info.versionName;
            return "版本号：" + version;
        } catch (Exception e) {
            e.printStackTrace();
//            return this.getString(R.string.can_not_find_version_name);
        }
        return null;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }
}