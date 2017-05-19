package com.example.administrator.my22;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import Threads.Send_content_no;

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
import android.widget.TextView;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
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
import static android.R.id.tabs;



public class MainActivity extends AppCompatActivity  {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout tabs;
    private GoogleCloudMessaging mGcm;
    private String token;
    private String UserNum;
    String refreshedToken = FirebaseInstanceId.getInstance().getToken();
    private static final String TAG = "MyFirebaseIIDService";
    private Device mDevice;
    private ArrayList<SvcTgt> mArraySvcTgt;
    private ArrayList<Device> mArrayDevices;


    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test = (TextView)findViewById(R.id.test);

        new LoginTask().execute();
        new GetHttpTask().execute();


        //new LoginTask().execute();

//        //IoTMakers의 서비스 대상에 대해 요청 하기 위한 API 클래스
//        SvcTgtApi svcTgtApi = new SvcTgtApi(token);
//
//        SvcTgt svcTgt = new SvcTgt();
//        String tgtseq = svcTgt.getSvcTgtSeq(); //서비스대상 일련번호
//
//        //IoTMakers의 디바이스에 요청 하기 위한 API 클래스
//        DeviceApi deviceApi = new DeviceApi(token);
//        Device device = new Device();
//        ArrayList<Device> devices= new ArrayList<Device>();


        mGcm = GoogleCloudMessaging.getInstance(MainActivity.this);

        //new GetDevListTask().execute();





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

            GigaIotOAuth gigaIotOAuth = new GigaIotOAuth("NCr0zIg0b3K6bNPr", "rrOCJukZrNdK4C2i");
            GiGaIotOAuthResponse response = gigaIotOAuth.loginWithPassword("tmd9936", "6535785a**");

            return response;
        }

        protected void onPostExecute(GiGaIotOAuthResponse result) {
            if(result != null && result.getResponseCode().equals(ApiConstants.CODE_OK)) {
                token = result.getAccessToken();
                UserNum = result.getMbrSeq(); //Gate 일련번호
            }else{
                test.setText("안됨");
            }

        }
    }
//    class  GetSvcTgtTask extends AsyncTask<Void,Void,SvcTgtApiResponse>{
//        @Override
//        protected SvcTgtApiResponse doInBackground(Void... params) {
//            try{
//                SvcTgtApi svcTgtApi = new SvcTgtApi(token);
//                return svcTgtApi.getSvcTgtSeqList(UserNum);
//
//            } catch(Exception e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(SvcTgtApiResponse result) {
//            super.onPostExecute(result);
//            if (result != null && result.getResponseCode().equals(ApiConstants.CODE_OK)) {
//                mArraySvcTgt = result.getSvcTgts();
//
//                new GetDevListTask().execute();
//            }else if (result != null && result.getResponseCode().equals(ApiConstants.CODE_NG) && result.getMessage().equals("Unauthorized")) {
//
//                finish();
//            }
//            else{
//                //// TODO: 2017-05-18
//            }
//        }
//    }
//    class GetDevListTask extends AsyncTask<Void,Void,DeviceApiResponse>{
//        @Override
//        protected DeviceApiResponse doInBackground(Void... params) {
//            try {
//                DeviceApi deviceApi = new DeviceApi(token);
//                DeviceApiResponse response = deviceApi.getDeviceList(mArraySvcTgt.get(0).getSvcTgtSeq(), 1, 100);
//                return response;
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(DeviceApiResponse result) {
//            super.onPostExecute(result);
//            if(result != null && result.getResponseCode().equals(ApiConstants.CODE_OK)){
//                test.setText("됨");
////                if(result.getDevices() != null && result.getDevices().size() > 0){
////                    mArrayDevices = result.getDevices();
////                    for(Device device : mArrayDevices){
////                        if(device.getSpotDevId().equals(ApplicationPreference.getInstance().getDeviceID())){
////                            mDevice = device;
////                            break;
////                        }
////                    }
////                    //new GetDevLastLog().execute();
////                }
//            }else{
//                test.setText("안되는");
//            }
//
//        }
//    }
//    class GetDevLastLog extends AsyncTask<Void,Void,TagStrmApiResponse>{
//        @Override
//        protected TagStrmApiResponse doInBackground(Void... params) {
//            TagStrmApi tagStrmApi = new TagStrmApi(ApplicationPreference.getInstance().getPrefAccessToken());
//            TagStrmApiResponse response = tagStrmApi.getTagStrmLog(mDevice.getSvcTgtSeq(), mDevice.getSpotDevSeq());
//            return response;
//        }
//
//        @Override
//        protected void onPostExecute(TagStrmApiResponse result) {
//            super.onPostExecute(result);
//            if(result != null && result.getResponseCode().equals(ApiConstants.CODE_OK)){
//                if (result.getLogs() != null && result.getLogs().size() > 0 && result.getLogs().get(0).getAttributes() != null) {
//                    String tagId = ApplicationPreference.getInstance().getTagID();
//                    if (result.getLogs().get(0).getAttributes().get(tagId) != null) {
//                        String lastData = result.getLogs().get(0).getAttributes().get(tagId).toString();
//                        String lastDataTime = result.getLogs().get(0).getOccDt();
//
//                        test.setText(lastData);
//                        test.setText("최근 조회시간 : " + timeFormatToSimple(lastDataTime));
//                    }
//                }
//            }
//        }
//
//    }
//    public static String timeFormatToSimple(String time){
//
//        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            Date date = df.parse(time);
//
//            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//
//            return sdf.format(date);
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
    class GetHttpTask extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... params)
        {
            String getJson;
            String result;
            try
            {
                getJson = sendGet("https://iotmakers.olleh.com:443/api/v1/streams/tmd993D1494308925360/log/last");
                JSONObject jsonObject = new JSONObject(getJson);
                result= jsonObject.getString("data");

                return result;

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            return null;
        }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);



            test.setText(result);
           // JSONObject attribute = new JSONObject(Data.getString("attributes"));


    }
}
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