package com.example.a40769.myapplication123;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Location extends AppCompatActivity {
    CameraUpdate cameraUpdate;
    MapView mMapView = null;
    String data = null;

    public JSONObject object;
    public JSONObject numob;
    public ListView lv;
    public ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    private Spinner mSpinner;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        MapView mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        final AMap aMap = mapView.getMap();
//        LatLng latLng1 = new LatLng(22.789488,108.39972);
//        final Marker marker1 = aMap.addMarker(new MarkerOptions().position(latLng1).title("青秀山").snippet("桃花岛"));
        LatLng latLng = new LatLng(22.7870500000, 108.3886300000);
        final Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title("青秀山").snippet("青秀山风景区"));
//        LatLng latLng3 = new LatLng(22.787975,108.391716);
//        final Marker marker3 = aMap.addMarker(new MarkerOptions().position(latLng3).title("青秀山").snippet("观音寺"));
//        LatLng latLng4 = new LatLng(22.792139,108.382425);
//        final Marker marker4 = aMap.addMarker(new MarkerOptions().position(latLng4).title("青秀山").snippet("邀月观阑"));
        marker.showInfoWindow();
        cameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng, 14, 0, 30));
        aMap.moveCamera(cameraUpdate);//地图移向指定区域
        ///////////////////////////////////////
        mSpinner = (Spinner) findViewById(R.id.spinner);
        mSpinner.setSelection(0, false);
        //使用过时方法
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //选择列表项的操作
                final String data = (String) mSpinner.getItemAtPosition(position);//从spinner中获取被选择的数据
                Toast.makeText(Location.this, data, Toast.LENGTH_SHORT).show();
                //init();
                mButton = (Button) findViewById(R.id.mbutton);
                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (data.equals("青秀山相思湖") == true) {
                            LatLng latLng1 = new LatLng(22.7884710000, 108.3884890000);
                            final Marker marker1 = aMap.addMarker(new MarkerOptions().position(latLng1).title("青秀山").snippet("相思湖"));
                            cameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng1, 16, 0, 30));
                            marker1.showInfoWindow();
                            aMap.moveCamera(cameraUpdate);//地图移向指定区域
                            init();
                        } else if (data.equals("青秀山龙象塔") == true)//龙象塔
                        {
                            LatLng latLng2 = new LatLng(22.78, 108.39);
                            final Marker marker2 = aMap.addMarker(new MarkerOptions().position(latLng2).title("青秀山").snippet("龙象塔"));
                            cameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng2, 16, 0, 30));
                            marker2.showInfoWindow();
                            aMap.moveCamera(cameraUpdate);//地图移向指定区域
                            init();
                        } else if (data.equals("青秀山桃花岛") == true) {
                            LatLng latLng3 = new LatLng(22.789488, 108.39972);
                            final Marker marker3 = aMap.addMarker(new MarkerOptions().position(latLng3).title("青秀山").snippet("桃花岛"));
                            marker3.showInfoWindow();
                            cameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng3, 16, 0, 30));
                            aMap.moveCamera(cameraUpdate);//地图移向指定区域
                            init();
                        } else if (data.equals("青秀山邀月观阑") == true) {
                            LatLng latLng4 = new LatLng(22.792139, 108.382425);
                            final Marker marker4 = aMap.addMarker(new MarkerOptions().position(latLng4).title("青秀山").snippet("邀月观阑"));
                            marker4.showInfoWindow();
                            cameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng4, 16, 0, 30));
                            aMap.moveCamera(cameraUpdate);//地图移向指定区域
                            init();
                        }
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        ////////////////////////////////

        ////////////////划线
//        List<LatLng> latLngs = new ArrayList<LatLng>();
//        latLngs.add(new LatLng(22.789488,108.39972));
//        latLngs.add(new LatLng(22.78,108.39));
//        latLngs.add(new LatLng(22.787975,108.391716));
//        latLngs.add(new LatLng(22.792139,108.382425));
//        Polyline polyline=aMap.addPolyline(new PolylineOptions().
//                addAll(latLngs).width(10).color(Color.argb(255, 1, 1, 1)));

        AMap.OnMarkerClickListener markerClickListener = new AMap.OnMarkerClickListener() {
            // marker 对象被点击时回调的接口
            // 返回 true 则表示接口已响应事件，否则返回false
            @Override
            public boolean onMarkerClick(Marker marker) {
                System.out.println(marker.getId());
                if ((marker.getId().toString().equals("Marker6") == true))//桃花岛
                {
                    System.out.println("aaa");
                } else if ((marker.getId().toString().equals("Marker7") == true))//龙象塔
                {
                    System.out.println("bbb");
                } else if ((marker.getId().toString().equals("Marker8") == true))//观音寺
                {
                    System.out.println("ccc");
                } else if ((marker.getId().toString().equals("Marker9") == true))//邀月观阑
                {
                    System.out.println("ddd");
                }
                return false;
            }
        };
// 绑定 Marker 被点击事件
        aMap.setOnMarkerClickListener(markerClickListener);
    }

    private void init() {
        list.clear();
        lv = (ListView) findViewById(R.id.lv);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String resultstart = HttpUtil.queryStringForPost("http://2i303858f9.imwork.net/BeginInv");
                if(resultstart!=null){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                String resultData = HttpUtil.queryStringForPost("http://2i303858f9.imwork.net/getAll");
                //String resultData = HttpUtil.queryStringForPost("http://2i303858f9.imwork.net/Myservlet/A");
                //String resultData = HttpUtil.queryStringForPost("http://http://127.0.0.1:8080/getAll");
                //String resultData= "{\"data:\":[{\"id\":\"1\",\"name\":\"曹想\",\"idCardNum\":\"521532199504280506\",\"telNum\":\"13522265245\",\"ticketCardBalance\":\"54\",\"ticketType\":\"学生\",\"reamrks\":\"1678\",\"visitPlace\":\"青秀山云天阁\",\"visitDate\":\"2018-05-06 17:54:57.0\",\"place1\":\"22.792139,108.382425\",\"place2\":\"22.78,108.39\",\"place3\":\"22.789488,108.39972\"},{\"id\":\"2\",\"name\":\"杨威\",\"idCardNum\":\"436752198605051234\",\"telNum\":\"13905894425\",\"ticketCardBalance\":\"65\",\"ticketType\":\"成人\",\"reamrks\":\"2089\",\"visitPlace\":\"青秀山云天阁\",\"visitDate\":\"2018-05-06 16:54:57.0\",\"place1\":\"22.792139,108.382425\",\"place2\":\"22.78,108.39\",\"place3\":\"22.789488,108.39972\"},{\"id\":\"3\",\"name\":\"杨三\",\"idCardNum\":\"436752200905081569\",\"telNum\":\"13596865526\",\"ticketCardBalance\":\"65\",\"ticketType\":\"儿童\",\"reamrks\":\"5B07\",\"visitPlace\":\"青秀山云天阁\",\"visitDate\":\"2018-05-06 16:54:57.0\",\"place1\":\"22.792139,108.382425\",\"place2\":\"22.78,108.39\",\"place3\":\"22.789488,108.39972\"},{\"id\":\"4\",\"name\":\"张铭\",\"idCardNum\":\"526645198808150586\",\"telNum\":\"15899635869\",\"ticketCardBalance\":\"20\",\"ticketType\":\"成人\",\"reamrks\":\"2994\",\"visitPlace\":\"青秀山云天阁\",\"visitDate\":\"2018-05-06 16:54:57.0\",\"place1\":\"22.792139,108.382425\",\"place2\":\"22.78,108.39\",\"place3\":\"22.789488,108.39972\"},{\"id\":\"5\",\"name\":\"康晓峰\",\"idCardNum\":\"236789199608152222\",\"telNum\":\"13585637489\",\"ticketCardBalance\":\"20\",\"ticketType\":\"学生\",\"reamrks\":\"2095\",\"visitPlace\":\"青秀山云天阁\",\"visitDate\":\"2018-05-06 16:54:57.0\",\"place1\":\"22.792139,108.382425\",\"place2\":\"22.78,108.39\",\"place3\":\"22.789488,108.39972\"},{\"id\":\"6\",\"name\":\"杨明才\",\"idCardNum\":\"522165196911110589\",\"telNum\":\"15923675845\",\"ticketCardBalance\":\"89\",\"ticketType\":\"成人\",\"reamrks\":\"3931\",\"visitPlace\":\"青秀山云天阁\",\"visitDate\":\"2018-05-06 16:54:57.0\",\"place1\":\"22.792139,108.382425\",\"place2\":\"22.78,108.39\",\"place3\":\"22.789488,108.39972\"},{\"id\":\"7\",\"name\":\"张静\",\"idCardNum\":\"463585197909091256\",\"telNum\":\"15869324458\",\"ticketCardBalance\":\"18\",\"ticketType\":\"成人\",\"reamrks\":\"3330\",\"visitPlace\":\"青秀山云天阁\",\"visitDate\":\"2018-05-06 16:54:57.0\",\"place1\":\"22.792139,108.382425\",\"place2\":\"22.78,108.39\",\"place3\":\"22.789488,108.39972\"}]}";
                jsonJX(resultData);
                Log.i("MainActivity", resultData);
            }
        }).start();
    }

    private void jsonJX(String date) {
        //判断数据是空
        if (date != null) {
            try {
                JSONObject jsonObject = new JSONObject(date);
                //获取到json数据中里的activity数组内容
                JSONArray resultJsonArray = jsonObject.getJSONArray("data:");
                numob = resultJsonArray.getJSONObject(0);
                String num = numob.getString("num");
                int a = Integer.parseInt(num);
                //遍历
                for (int i = 0; i < a; i++) {
                    object = resultJsonArray.getJSONObject(i);
                    Map<String, Object> map = new HashMap<String, Object>();
                    try {
                        //获取到json数据中的activity数组里的内容name
                        String name = object.getString("name");
                        //获取到json数据中的activity数组里的内容startTime
                        String IDcard = object.getString("idCardNum");
                        String telNum = object.getString("telNum");
                        String ticketCardBalance = object.getString("ticketCardBalance");
                        String ticketType = object.getString("ticketType");
                        String visitPlace = object.getString("visitPlace");
                        String visitDate_1 = object.getString("visitDate");
                        String visitDate = visitDate_1.substring(0, 16);
                        String place1 = object.getString("place1");
                        String place2 = object.getString("place2");
                        String place3 = object.getString("place3");
                        //String num = object.getString("num");
                        //存入map
                        map.put("name", name);
                        map.put("IDcard", IDcard);
                        map.put("telNum", telNum);
                        map.put("ticketCardBalance", ticketCardBalance);
                        map.put("ticketType", ticketType);
                        map.put("visitPlace", visitPlace);
                        map.put("visitDate", visitDate);
                        map.put("place1", place1);
                        map.put("place2", place2);
                        map.put("place3", place3);
                        //ArrayList集合
                        list.add(map);
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Mybaseadapter list_item = new Mybaseadapter();
                    lv.setAdapter(list_item);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            //Toast.makeText(MainActivity.this,list.get(i).toString(),Toast.LENGTH_LONG).show();
                            //使用bundle传递参数
                            Bundle bundle = new Bundle();
                            bundle.putString("name", list.get(i).get("name").toString());
                            bundle.putString("IDcard", list.get(i).get("IDcard").toString());
                            bundle.putString("telNum", list.get(i).get("telNum").toString());
                            bundle.putString("ticketCardBalance", list.get(i).get("ticketCardBalance").toString());
                            bundle.putString("visitDate", list.get(i).get("visitDate").toString());
                            bundle.putString("ticketType", list.get(i).get("ticketType").toString());
                            bundle.putString("visitPlace", list.get(i).get("visitPlace").toString());
                            bundle.putString("place1", list.get(i).get("place1").toString());
                            bundle.putString("place2", list.get(i).get("place2").toString());
                            bundle.putString("place3", list.get(i).get("place3").toString());
                            Intent intent = new Intent();
                            intent.putExtras(bundle);
                            //跳转class页面
                            intent.setClass(Location.this, detailActivity.class);
                            startActivity(intent);
                        }
                    });
                    break;
            }
        }
    };

    public class Mybaseadapter extends BaseAdapter {

        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();

                convertView = getLayoutInflater().inflate(R.layout.listview_item, null);
                //找到布局中的tv
                viewHolder.name = (TextView) convertView.findViewById(R.id.name);
                viewHolder.idCardNumber = (TextView) convertView.findViewById(R.id.IDcard);
                viewHolder.visitDate = (TextView) convertView.findViewById(R.id.visitDate);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            //给布局中得tv赋值
            viewHolder.name.setText(list.get(position).get("name").toString());
            viewHolder.idCardNumber.setText(list.get(position).get("IDcard").toString());
            viewHolder.visitDate.setText(list.get(position).get("visitDate").toString());
            return convertView;
        }
    }

    final static class ViewHolder {
        TextView name;
        TextView idCardNumber;
        TextView visitDate;
    }

    public void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
