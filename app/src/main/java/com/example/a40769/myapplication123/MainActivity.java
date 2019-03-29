package com.example.a40769.myapplication123;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.daniulive.smartplayer.SmartPlayer;

import java.util.List;


public class MainActivity extends AppCompatActivity  {
    CameraUpdate cameraUpdate;
   MapView mMapView = null;
    ImageButton imageButton2;
    ImageButton imageButton1;
    ImageButton imageButton3;
    EditText editText_sou;
    Button button_sou;

    static {
        System.loadLibrary("SmartPlayer");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取地图控件引用
       mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

        MapView mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        final AMap aMap = mapView.getMap();



        imageButton1= (ImageButton) findViewById(R.id.imageButton1_shui);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.qxslyfjq.com/qxsfjqjdjsdq/index.jhtml");    //设置跳转的网站
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        imageButton2= (ImageButton) findViewById(R.id.imageButton2_lan);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.qxslyfjq.com/qxsfjqjdjsly/index.jhtml");    //设置跳转的网站
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        imageButton3= (ImageButton) findViewById(R.id.imageButton3_long);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.qxslyfjq.com/qxsfjqjdjssscl/index.jhtml");    //设置跳转的网站
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


        LatLng latLng = new LatLng(22.7870500000,108.3886300000);
        cameraUpdate= CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng,14,0,30));
        aMap.moveCamera(cameraUpdate);//地图移向指定区域

        editText_sou = (EditText) findViewById(R.id.et_sou);
        button_sou = (Button) findViewById(R.id.bt_sou);
        editText_sou.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }
                    @Override
                    public void afterTextChanged(Editable s) {


                        String content = editText_sou.getText().toString().trim();
                        //判断内容不为空
                        if (null != content && !content.isEmpty()) {
                            //通过Query设置搜索条件,第一个参数为搜索内容,第二个参数为搜索类型，第三个参数为搜索范围（空字符串代表全国）。
                            PoiSearch.Query query = new PoiSearch.Query(content, "", "南宁");
                            PoiSearch poiSearch = new PoiSearch(MainActivity.this, query);
                            poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
                                @Override
                                public void onPoiSearched(PoiResult poiResult, int errcode) {
                                    //判断搜索成功
                                    if (errcode == 1000) {
                                        if (null != poiResult && poiResult.getPois().size() > 0) {
                                            for (int i = 0; i < poiResult.getPois().size(); i++) {
                                                Log.i("MainActivity", "POI 的经纬度=" + poiResult.getPois().get(i).getLatLonPoint());
                                                Log.i("MainActivity", "POI的地址=" + poiResult.getPois().get(i).getSnippet());
                                                Log.i("MainActivity", "POI的名称=" + poiResult.getPois().get(i).getTitle());
                                                final LatLonPoint location = poiResult.getPois().get(i).getLatLonPoint();
                                                final double Latitude = location.getLatitude();
                                                final double Longitude=location.getLongitude();
                                                button_sou.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        InputMethodManager imm = (InputMethodManager)
                                                                getSystemService(Context.INPUT_METHOD_SERVICE);
                                                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                                                        LatLng l = new LatLng(Latitude,Longitude);
                                                        final Marker marker1 = aMap.addMarker(new MarkerOptions().position(l));
                                                        cameraUpdate= CameraUpdateFactory.newCameraPosition(new CameraPosition(l,16,0,30));
                                                        aMap.moveCamera(cameraUpdate);//地图移向指定区域
                                                    }
                                                });

                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onPoiItemSearched(PoiItem poiItem, int i) {

                                }
                            });
                            poiSearch.searchPOIAsyn();
                        }
                    }
                });




//        LatLng latLng1 = new LatLng(22.789488,108.39972);
//        final Marker marker1 = aMap.addMarker(new MarkerOptions().position(latLng1).title("青秀山").snippet("桃花岛"));
//        LatLng latLng2 = new LatLng(22.78,108.39);
//        final Marker marker2 = aMap.addMarker(new MarkerOptions().position(latLng2).title("青秀山").snippet("龙象塔"));
//        LatLng latLng3 = new LatLng(22.787975,108.391716);
//        final Marker marker3 = aMap.addMarker(new MarkerOptions().position(latLng3).title("青秀山").snippet("观音寺"));
//        LatLng latLng4 = new LatLng(22.792139,108.382425);
//        final Marker marker4 = aMap.addMarker(new MarkerOptions().position(latLng4).title("青秀山").snippet("邀月观阑"));
//        LatLng latLng = new LatLng(22.7870500000,108.3886300000);
//        final Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title("青秀山"));
//        marker1.showInfoWindow();
//        cameraUpdate= CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng,14,0,30));
//        aMap.moveCamera(cameraUpdate);//地图移向指定区域

        //////////////////////////////////



    }
    private void openApp(String packageName) {
        PackageInfo pi = null;
        try {
            pi = MainActivity.this.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(pi.packageName);

        List<ResolveInfo> apps = MainActivity.this.getPackageManager().queryIntentActivities(resolveIntent, 0);

        ResolveInfo ri = apps.iterator().next();
        if (ri != null ) {
            packageName = ri.activityInfo.packageName;
            String className = ri.activityInfo.name;

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);

            ComponentName cn = new ComponentName(packageName, className);

            intent.setComponent(cn);
            startActivity(intent);
        }
    }

    public void button1_click(View view)
    {
        //setContentView(R.layout.login);
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);

    }
    public void button2_click(View view)
    {
    openApp("com.baidu.BaiduMap");

    }
    public void button3_click(View view)
    {
        Intent intent = new Intent(MainActivity.this, SmartPlayer.class);
        startActivity(intent);

    }



    public void buttonback_click(View view)
    {
        //setContentView(R.layout.activity_main);
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
        MainActivity.this.finish();
    }


    public void image_click(View view)
    {
        setContentView(R.layout.introduce);

    }

    public void button4_click (View view){
        Intent intent_hello = new Intent(MainActivity.this, panoromaActivity.class);
        startActivity(intent_hello);

    }


}
