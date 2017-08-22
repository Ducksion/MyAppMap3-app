package com.example.administrator.myappmap3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
    }

    public void go_click(View view){
        MapView mymapView;
        BaiduMap myBaiduMap;

        // 地图初始化
        mymapView = (MapView)view.findViewById(R.id.mymapView);
        myBaiduMap = mymapView.getMap();
    }
}
