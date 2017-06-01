package com.example.administrator.my22;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Handler;

import com.google.gson.JsonObject;
import com.kt.gigaiot_sdk.data.Device;

import com.kt.gigaiot_sdk.data.TagStrmApiResponse;
import com.kt.gigaiot_sdk.network.ApiConstants;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.logging.LogRecord;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import device1.DeviceTask;

public class PlaceholderFragment extends Fragment {
    TextView InTemp;
    private static final String TAG = MainActivity.class.getSimpleName();
    private Device mDevice;
    //progressRunnable runable;
    String sResult = null;

    Handler handler;
    View rootView;

    public PlaceholderFragment() {
    }


    public static PlaceholderFragment newInstance() {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }//자신읜 인스턴스를 리턴하는 함수

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Fragment 생성
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        InTemp = (TextView)rootView.findViewById(R.id.inTemp);

        return rootView;
    }


}
