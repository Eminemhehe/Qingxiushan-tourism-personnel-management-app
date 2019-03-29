package com.example.a40769.myapplication123;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.utils.SpatialRelationUtil;
import com.amap.api.maps.utils.overlay.SmoothMoveMarker;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 40769 on 2018/12/14.
 */

public class detailActivity extends AppCompatActivity {
    MapView mMapView = null;
    CameraUpdate cameraUpdate;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        MapView mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        AMap aMap = mapView.getMap();
        // aMap.setTrafficEnabled(true);// 显示实时交通状况
        ///////////////////////////////////////
//        MyLocationStyle myLocationStyle;
//        myLocationStyle = new MyLocationStyle();
//        //初始化定位蓝点样式类
//        //myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE) ;
//        myLocationStyle.interval(100); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
//        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
//        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
//        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW) ;
//        aMap.moveCamera(CameraUpdateFactory.zoomTo(14));

//        LatLng latLng1 = new LatLng(22.789488,108.39972);
//        final Marker marker1 = aMap.addMarker(new MarkerOptions().position(latLng1).title("青秀山").snippet("桃花岛"));
//        LatLng latLng2 = new LatLng(22.78,108.39);
//        final Marker marker2 = aMap.addMarker(new MarkerOptions().position(latLng2).title("青秀山").snippet("龙象塔"));
//        LatLng latLng3 = new LatLng(22.787975,108.391716);
//        final Marker marker3 = aMap.addMarker(new MarkerOptions().position(latLng3).title("青秀山").snippet("观音寺"));
//        LatLng latLng4 = new LatLng(22.792139,108.382425);
//        final Marker marker4 = aMap.addMarker(new MarkerOptions().position(latLng4).title("青秀山").snippet("邀月观阑"));
        //////////////////////////////////

        Bundle bundle = getIntent().getExtras();
        //接收数据
        String message_name = bundle.getString("name");
        String message_IDcard = bundle.getString("IDcard");
        String message_telNum = bundle.getString("telNum");
        String message_ticketCardBalance = bundle.getString("ticketCardBalance");
        String message_ticketType = bundle.getString("ticketType");
        String message_visitPlace = bundle.getString("visitPlace");
        String message_place1 = bundle.getString("place1");
        String message_place2 = bundle.getString("place2");
        String message_place3 = bundle.getString("place3");
        String[] strarr1 = message_place1.split(",");
        double place1_1 = Double.valueOf(strarr1[0].toString());
        double place1_2 = Double.valueOf(strarr1[1].toString());
        String[] strarr2 = message_place2.split(",");
        double place2_1 = Double.valueOf(strarr2[0].toString());
        double place2_2 = Double.valueOf(strarr2[1].toString());
        String[] strarr3 = message_place3.split(",");
        double place3_1 = Double.valueOf(strarr3[0].toString());
        double place3_2 = Double.valueOf(strarr3[1].toString());


        LatLng latLng = new LatLng(22.7870500000,108.3886300000);
        cameraUpdate= CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng,15,0,30));
        LatLng latLng1 = new LatLng(place1_1, place1_2);
        final Marker marker1 = aMap.addMarker(new MarkerOptions().position(latLng1).title("青秀山").snippet("邀月观阑"));
        LatLng latLng3 = new LatLng(place2_1, place2_2);
        final Marker marker3 = aMap.addMarker(new MarkerOptions().position(latLng3).title("青秀山").snippet("龙象塔"));
        LatLng latLng2 = new LatLng(place3_1, place3_2);
        final Marker marker2 = aMap.addMarker(new MarkerOptions().position(latLng2).title("青秀山").snippet("观音寺"));
        aMap.moveCamera(cameraUpdate);

        List<LatLng> points = new ArrayList<LatLng>();
        points.add(new LatLng(place1_1, place1_2));
        points.add(new LatLng(place2_1, place2_2));
        points.add(new LatLng(place3_1, place3_2));
        LatLngBounds bounds =  new LatLngBounds(points.get(0), points.get(points.size() - 2));
        aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));

        SmoothMoveMarker smoothMarker = new SmoothMoveMarker(aMap);
// 设置滑动的图标
        smoothMarker.setDescriptor(BitmapDescriptorFactory.fromResource(R.drawable.ren));

        LatLng drivePoint = points.get(0);
        android.util.Pair<Integer, LatLng> pair = SpatialRelationUtil.calShortestDistancePoint(points, drivePoint);
        points.set(pair.first, drivePoint);
        List<LatLng> subList = points.subList(pair.first, points.size());

// 设置滑动的轨迹左边点
        smoothMarker.setPoints(subList);
// 设置滑动的总时间
        smoothMarker.setTotalDuration(5);
// 开始滑动
        smoothMarker.startSmoothMove();

        List<LatLng> latLngs = new ArrayList<LatLng>();
        latLngs.add(new LatLng(place1_1, place1_2));
        latLngs.add(new LatLng(place2_1, place2_2));
        latLngs.add(new LatLng(place3_1, place3_2));
        Polyline polyline = aMap.addPolyline(new PolylineOptions().
                addAll(latLngs).width(8).color(Color.argb(255, 1, 1, 1)));

        TextView tv_name = (TextView) findViewById(R.id.tv_name);
        tv_name.setText(message_name);
        TextView tv_IDcard = (TextView) findViewById(R.id.tv_IDcard);
        tv_IDcard.setText(message_IDcard);
        TextView tv_telNum = (TextView) findViewById(R.id.tv_tel);
        tv_telNum.setText(message_telNum);
        TextView tv_ticketCardBalance = (TextView) findViewById(R.id.tv_old);
        tv_ticketCardBalance.setText(message_ticketCardBalance);
        TextView tv_ticketType = (TextView) findViewById(R.id.tv_type);
        tv_ticketType.setText(message_ticketType);
        TextView tv_visitPlace = (TextView) findViewById(R.id.tv_vPlace);
        tv_visitPlace.setText(message_visitPlace);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("detail Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
