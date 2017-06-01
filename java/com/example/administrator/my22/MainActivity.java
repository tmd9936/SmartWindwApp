package com.example.administrator.my22;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ToxicBakery.viewpager.transforms.CubeInTransformer;
import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.ToxicBakery.viewpager.transforms.StackTransformer;
import com.ToxicBakery.viewpager.transforms.FlipHorizontalTransformer;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.kt.gigaiot_sdk.DeviceApi;
import com.kt.gigaiot_sdk.GigaIotOAuth;
import com.kt.gigaiot_sdk.SvcTgtApi;
import com.kt.gigaiot_sdk.TagStrmApi;
import com.kt.gigaiot_sdk.data.Device;
import com.kt.gigaiot_sdk.data.DeviceApiResponse;
import com.kt.gigaiot_sdk.data.GiGaIotOAuthResponse;
import  com.google.firebase.iid.FirebaseInstanceId;
import  com.google.firebase.iid.FirebaseInstanceIdService;
import com.kt.gigaiot_sdk.data.SvcTgt;
import com.kt.gigaiot_sdk.data.SvcTgtApiResponse;
import com.kt.gigaiot_sdk.data.TagStrm;
import com.kt.gigaiot_sdk.data.TagStrmApiResponse;
import com.kt.gigaiot_sdk.network.ApiConstants;
import com.kt.smcp.gw.ca.comm.exception.SdkException;
import com.kt.smcp.gw.ca.gwfrwk.adap.stdsys.sdk.tcp.BaseInfo;
import com.kt.smcp.gw.ca.gwfrwk.adap.stdsys.sdk.tcp.IMTcpConnector;
import com.kt.smcp.gw.ca.gwfrwk.adap.stdsys.sdk.tcp.LogIf;
import com.kt.smcp.gw.ca.util.IMUtil;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.helpers.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static android.R.attr.fragment;
import static android.R.attr.text;
import static android.R.id.tabs;
import static android.graphics.Color.BLUE;
import static android.graphics.Color.GREEN;


public class MainActivity extends AppCompatActivity  {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout tabs;
    private GoogleCloudMessaging mGcm;
    private String token;
    private String UserNum;
    String refreshedToken = FirebaseInstanceId.getInstance().getToken();
    private static final String TAG = "MyFirebaseIIDService";

    TextView inTemp;
    TextView outRain;
    TextView inGas;
    TextView outDust;
    TextView inDust;
    TextView inHumidity;

    TextView modetxt;

    Handler handler = new Handler();
//    PrograssRunnable runnable;
//    GetHttpTask httpRain;
//    GetHttpTask httpGas;
    GetHttpThread getHttpThread;

    Button sul;
    Button infoBtn;
    Button timeButton;

    String[] total = new String[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGcm = GoogleCloudMessaging.getInstance(MainActivity.this);

        inTemp = (TextView)findViewById(R.id.inTemp);
        outRain = (TextView)findViewById(R.id.outRain);
        inGas = (TextView)findViewById(R.id.inGas);
        outDust = (TextView)findViewById(R.id.outDust);
        inDust = (TextView)findViewById(R.id.inDust);
        inHumidity= (TextView)findViewById(R.id.inHumidity);

        modetxt = (TextView)findViewById(R.id.modetxt);

        sul = (Button)findViewById(R.id.opneButton);
        infoBtn = (Button)findViewById(R.id.infoButton);
        timeButton = (Button)findViewById(R.id.timeButton);


        new LoginTask().execute();
//        timeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getHttpThread = new GetHttpThread();
//                getHttpThread.start();
//            }
//        });
        getHttpThread = new GetHttpThread();
        getHttpThread.start();



//        httpRain = new GetHttpTask("Flood",outRain);
//        httpRain.execute();


        //제목이나 타이틀 넣는 툴바
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);


//        //탭의 이름을 정하는 구간
//        tabs = (TabLayout) findViewById(R.id.tabs);
//        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
//
//
//        // Set up the ViewPager with the sections adapter.
//        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
//        mViewPager = (ViewPager) findViewById(R.id.container);
//        mViewPager.setAdapter(mSectionsPagerAdapter); // 어댑터를 선택
//
//        mViewPager.setPageTransformer(true, new CubeOutTransformer());//페이지 변경 모션
//
//        tabs.setupWithViewPager(mViewPager); //탭이랑 연결하는 함수
//        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
//
//       // tabs.getTabAt(0).setIcon(R.drawable.ic_home_black_24dp); //아이콘  넣기
//
//
//
//
//        //탭 선택어댑터
//        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                mViewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
    }
    class LoginTask extends AsyncTask<Void, Void, GiGaIotOAuthResponse> {
        protected GiGaIotOAuthResponse doInBackground(Void... params) {

            GigaIotOAuth gigaIotOAuth = new GigaIotOAuth("2KZS1wXpqddc4clK", "smtCJwnWHDCCJtZU");
            GiGaIotOAuthResponse response = gigaIotOAuth.loginWithPassword("gkdltjr", "dn808180!");

            return response;
        }

        protected void onPostExecute(GiGaIotOAuthResponse result) {
            if(result != null && result.getResponseCode().equals(ApiConstants.CODE_OK)) {
                token = result.getAccessToken();
                UserNum = result.getMbrSeq(); //Gate 일련번호
            }else{
                inTemp.setText("안됨");
            }

        }
    }
    public void onButtonClickedInfo(View v) {
        Toast.makeText(this, "정보 버튼을 눌렀어요.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), infoActivity.class);
        startActivityForResult(intent,1001);

    }
    public void onButtonClickedSetup(View v){
        Toast.makeText(this, "설정 버튼을 눌렀어요.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), modeActivity.class);
        intent.putExtra("mode",total[7]);
        startActivityForResult(intent,1001);

    }


    class GetHttpThread extends Thread{
        @Override
        public void run() {
            super.run();
            while (true) {
                try {
                    String getJson;
                    getJson = sendGet("https://iotmakers.olleh.com:443/api/v1.1/devices?id=gkdltjD1493939912776");
                    JSONObject jsonObject = new JSONObject(getJson);
                    final JSONArray jsonArray = jsonObject.getJSONArray("data");
                    JSONObject dataArray = jsonArray.getJSONObject(0);
                    //String temp =   dataArray.getString("sensingTags");
                    final JSONArray sensingTags = dataArray.getJSONArray("sensingTags");
                    JSONObject[] info = new JSONObject[8];
                    for (int i = 0; i < info.length; i++) {
                        info[i] = sensingTags.getJSONObject(i);
                    }
                    //String temp = info.getString("value");
                    //String temp2 = info2.getString("value");
                    for (int i = 0; i < info.length; i++) {
                        total[i] = info[i].getString("value");
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            outRain.setText(total[0]);
                            inGas.setText(total[1]);
                            inHumidity.setText(total[2]+"%");
                            inDust.setText(total[3]+"(㎛/㎥)");
                            outDust.setText(total[4]+"(㎛/㎥)");
                            inTemp.setText(total[5]+"℃");
                            if(total[7].equals("1.0")==true) {
                                modetxt.setText("수동모드");
                                modetxt.setTextColor(GREEN);
                            }
                            else {
                                modetxt.setText("                  자동모드");
                                modetxt.setTextColor(BLUE);
                            }
                        }
                    });


//                for(int i=0; i<text.length; i++) {
//                    if(text[i].equals(tag)) {
//                        // result = total[i];
//                    }
//                }
//                JSONObject jsonObject = new JSONObject(getJson);
//                JSONArray jsonArray=jsonObject.getJSONArray("data");
//                JSONObject info = jsonArray.getJSONObject(0);
//                JSONObject attribute = info.getJSONObject("attributes");
//                String temp = attribute.getString(tag);
                    //               result = temp2;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
//    class PrograssRunnable implements Runnable{
//        @Override
//        public void run() {
//            outRain.setText(total[0]);
//            inGas.setText(total[1]);
//            inHumidity.setText(total[2]);
//            inDust.setText(total[3]);
//           // outDust.setText(total[4]);
//            inTemp.setText(total[5]);
//        }
//    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        getHttpThread = new GetHttpThread();
//        getHttpThread.start();
//
//    }

//    class GetHttpTask extends AsyncTask<String,String,String>{
//
//        String tag;
//        TextView textView;
//        GetHttpTask(String tag, TextView textView) {
//            this.tag = tag;
//            this.textView = textView;
//        }
//
//        @Override
//        protected String doInBackground(String... params)
//        {
//            String[] text = {"Flood", "Gas"};
//            String getJson;
//            String result = null;
//            try
//            {
//                getJson = sendGet("https://iotmakers.olleh.com:443/api/v1.1/devices?id=gkdltjD1493939912776");
//                JSONObject jsonObject = new JSONObject(getJson);
//                JSONArray jsonArray=jsonObject.getJSONArray("data");
//                JSONObject dataArray=jsonArray.getJSONObject(0);
//                //String temp =   dataArray.getString("sensingTags");
//                JSONArray sensingTags=dataArray.getJSONArray("sensingTags");
//                JSONObject[] info = new JSONObject[6];
//                for(int i=0; i<info.length; i++) {
//                    info[i] = sensingTags.getJSONObject(i);
//                }
//                //String temp = info.getString("value");
//                //String temp2 = info2.getString("value");
//                for(int i=0; i<info.length; i++) {
//                    total[i] = info[i].getString("value");
//                }
//
//
//                for(int i=0; i<text.length; i++) {
//                    if(text[i].equals(tag)) {
//                       // result = total[i];
//                    }
//                }
////                JSONObject jsonObject = new JSONObject(getJson);
////                JSONArray jsonArray=jsonObject.getJSONArray("data");
////                JSONObject info = jsonArray.getJSONObject(0);
////                JSONObject attribute = info.getJSONObject("attributes");
////                String temp = attribute.getString(tag);
// //               result = temp2;
//            }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//
//            return result;
//        }
//
//    @Override
//    protected void onPostExecute(String result) {
//        super.onPostExecute(result);
//        //textView.setText(result);
//        outRain.setText(total[0]);
//        inGas.setText(total[1]);
//        inHumidity.setText(total[2]);
//        inDust.setText(total[3]);
//        outDust.setText(total[4]);
//        inTemp.setText(total[5]);
//            // JSONObject attribute = new JSONObject(Data.getString("attributes"));
//        this.cancel(true);
//    }
//}
    private String sendGet(String url) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
       // con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + token);
        //검색 안드로이드 url header


        // optional default is GET
        con.setRequestMethod("GET");

        // add request header
        int responseCode = con.getResponseCode();
        //System.out.println("\nSending 'GET' request to URL : " + url);
        //System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }


        in.close();

        //int tempInt = Integer.parseInt(attribute.getString("tempre"));

        // return result
        return response.toString();
        //return response.substring(160,170).toString();

    }
}