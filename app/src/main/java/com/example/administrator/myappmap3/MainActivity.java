package com.example.administrator.myappmap3;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Context;
import android.content.pm.*;
import android.support.v4.content.*;
import android.location.Location;
import android.location.LocationManager;
import android.widget.*;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.district.*;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.MarkerOptions;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MapView mymapView = null;
    Location location;

    //The minimum distance to change updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0; // 10 meters
    //The minimum time beetwen updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 0;//1000 * 60 * 1; // 1 minute

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        //获取地图控件引用
        mymapView = (MapView) findViewById(R.id.mymapView);
        location = getLocation(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mymapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mymapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mymapView.onPause();
    }

    //Get the Location by GPS or WIFI
    public Location getLocation(Context context)
    {
        if (ContextCompat.checkSelfPermission( context, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission( context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return null ;
        }
        LocationManager locMan = (LocationManager) context
                                  .getSystemService(Context.LOCATION_SERVICE);

        boolean enabledGPS = locMan.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean enabledNetwork = locMan.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (enabledGPS)
        {
            location = locMan.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }

        if (enabledNetwork && location==null)
        {
            location = locMan.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }

        return location;
    }

    public void AddMark_click(View view)
    {
        //ShowMessage("OK","AddMark","AddMark Test");
        BaiduMap oMap;
        oMap = mymapView.getMap();

        //在地图上添加Marker，并显示
        oMap.addOverlays(CreatOverlayOptionsList());
    }

    public List<OverlayOptions> CreatOverlayOptionsList()
    {
        double latitude = 39.963175;
        double longitude = 116.400244;

        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.b_16);

        List<OverlayOptions> lstoption = new ArrayList<OverlayOptions>();

        for (int count = 0; count < 5; count++)
        {
            //定义Maker坐标点
            latitude = latitude + 0.00003;
            longitude = longitude + 0.00003;
            LatLng point = new LatLng(latitude, longitude);

            //构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions()
                    .position(point)
                    .icon(bitmap);

            lstoption.add(option);
        }
        for (int count = 0; count < 4; count++)
        {
            //定义Maker坐标点
            latitude = latitude + 0;
            longitude = longitude + 0.00003;
            LatLng point = new LatLng(latitude, longitude);

            //构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions()
                    .position(point)
                    .icon(bitmap);

            lstoption.add(option);
        }

        for (int count = 0; count < 5; count++)
        {
            //定义Maker坐标点
            latitude = latitude - 0.00003;
            longitude = longitude + 0;
            LatLng point = new LatLng(latitude, longitude);

            //构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions()
                    .position(point)
                    .icon(bitmap);

            lstoption.add(option);
        }

        for (int count = 0; count < 9; count++)
        {
            //定义Maker坐标点
            latitude = latitude + 0;
            longitude = longitude - 0.00003;
            LatLng point = new LatLng(latitude, longitude);

            //构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions()
                    .position(point)
                    .icon(bitmap);

            lstoption.add(option);
        }

        return lstoption;
    }

    public void go_click(View view)
    {

        if (location == null)
        {
            ShowMessage("OK","GO!","未能获取当前位置！");
            return;
        }

        BaiduMap oMap;
        oMap = mymapView.getMap();
        oMap.setMyLocationEnabled(true);
        // 构造定位数据
        MyLocationData locData = new MyLocationData.Builder()
                                    .accuracy(location.getAccuracy())
                                    // 此处设置开发者获取到的方向信息，顺时针0-360
                                    .direction(100).latitude(location.getLatitude())
                                    .longitude(location.getLongitude()).build();
        // 设置定位数据
        oMap.setMyLocationData(locData);
        // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
        MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, null);
        oMap.setMyLocationConfiguration(config);
    }

    ///button:按钮显示的文字
    ///title：标题文字
    ///message：详细内容文字
    public void  ShowMessage(CharSequence button ,CharSequence title,CharSequence message )
    {
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage(message);
        dlgAlert.setTitle(title);
        dlgAlert.setPositiveButton(button,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //dismiss the dialog
                    }
                });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

}
