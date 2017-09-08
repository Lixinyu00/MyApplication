package com.example.user.text;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private ImageView imageView_1;
    private TextView textView_1;
    private String url="http://v.juhe.cn/weather/index?cityname=北京&dtype=&format=2&key=a3b12671d03faa0f2d047e0980b39dbe ";

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            setweather(msg.getData().get("text").toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        btn_1 = (Button) findViewById(R.id.button_1);
        btn_2 = (Button) findViewById(R.id.button_2);
        btn_3 = (Button) findViewById(R.id.button_3);
        btn_4 = (Button) findViewById(R.id.button_4);
        btn_5 = (Button) findViewById(R.id.button_5);
        imageView_1 = (ImageView) findViewById(R.id.iv_1);
        textView_1 = (TextView) findViewById(R.id.tv_1);
    }

    private void initListener() {
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
    }

    /**
     * @author lixinyu
     * @date 2017/8/3 17:01
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_1:
                imageload(1);
                Log.e("123", "onClick:1 ");
                break;
            case R.id.button_2:
                imageload(2);
                Log.e("123", "onClick:2 ");
                break;
            case R.id.button_3:
                request(1);
                Log.e("123", "onClick:3 ");
                break;
            case R.id.button_4:
                request(2);
                Log.e("123", "onClick:4 ");
                break;
            case R.id.button_5:
                try {
                    okrequest(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e("123", "onClick:5 ");
                break;
        }
    }

    private void imageload(int num) {
        switch (num) {
            case 1:
                String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1501836662916&di=bab4ed919c671bf5267ab207d18740f6&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fb03533fa828ba61e5e6d4c0d4b34970a304e5915.jpg";
                Glide.with(this).load(url).placeholder(R.drawable.photo).into(imageView_1);
                break;
            case 2:
                Glide.with(this).load(R.drawable.photo).into(imageView_1);
        }
    }

    private void request(int num) {
        String url_1 = "https://www.baidu.com";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        switch (num) {
            case 1:
                StringRequest stringRequest = new StringRequest(url_1,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                textView_1.setText(s);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        textView_1.setText(volleyError.getMessage());
                    }
                });
                requestQueue.add(stringRequest);
                break;
            case 2:
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                Log.e("456789", "onResponse: "+jsonObject.toString() );
                                setweather(jsonObject.toString());
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
                requestQueue.add(jsonObjectRequest);
        }
    }

    private void okrequest(int num) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(
                new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(final com.squareup.okhttp.Response response) throws IOException {
                        final String res = response.body().string();
                            /*runOnUiThread(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            textView_1.setText(res);
                                        }
                                    }
                            );*/
                        Message message = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putString("text", res);
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                }
        );
    }
    private void setweather(String s){
        String str="\n";
        WeatherBean2 weatherBean=JSON.parseObject(s,WeatherBean2.class);
        List<WeatherBean2.ResultBean.FutureBean> futureBeen=weatherBean.getResult().getFuture();
//        textView_1.setText(weatherBean.getResult().getFuture().getDay_20170809().toString()+
//                weatherBean.getResult().getFuture().getDay_20170810().toString());
       for (int i=0;i<7;i++){
            str+=futureBeen.get(i).toString();
        }
        textView_1.setText(str);
    }
}
