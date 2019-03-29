package com.example.a40769.myapplication123;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by wz on 2018/12/3.
 */

public class HttpUtil {
    // 基础URL
    // 获得Get请求对象request
    public static HttpGet getHttpGet(String url){
        HttpGet request = new HttpGet(url);
        return request;
    }
    // 获得Post请求对象request
    public static HttpPost getHttpPost(String url){
        HttpPost request = new HttpPost(url);
        return request;
    }

    // 根据请求获得响应对象response
    public static HttpResponse getHttpResponse(HttpPost request) throws ClientProtocolException, IOException {
        HttpResponse response = new DefaultHttpClient().execute(request);
        return response;
    }

    // 发送Post请求，获得响应查询结果
    public static String queryStringForPost(String url){
        // 根据url获得HttpPost对象
        HttpPost request = HttpUtil.getHttpPost(url);
        String result = null;

        try {
            // 获得响应对象
            HttpResponse response = HttpUtil.getHttpResponse(request);
            // 判断是否请求成功
            if(response.getStatusLine().getStatusCode()==200){
                // 获得响应
                result = EntityUtils.toString(response.getEntity());
                //result=new  String(result.getBytes("8859_1"),"GB2312");  这句可要可不要，以你不出现乱码为准


                return result;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            result = "网络异常！";
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            result = "网络异常！";
            return result;
        }
        return null;
    }

}
