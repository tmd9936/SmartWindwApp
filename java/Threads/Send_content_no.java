package Threads;

import android.os.Handler;
import android.widget.TextView;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017-05-17.
 */

public class Send_content_no extends Thread {
    String sResult;
    int temps;
    Handler handler;

    public void run() {
        sResult = " ";

        try {
            URL u = new URL("https://iotmakers.olleh.com:443/api/v1/streams/tmd993D1494308925360/log/last");
            HttpURLConnection huc = (HttpURLConnection) u.openConnection();
            huc.setReadTimeout(4000);
            huc.setConnectTimeout(4000);
            huc.setRequestMethod("POST");
            huc.setDoInput(true);
            huc.setDoOutput(true);
            huc.setRequestProperty("utf-8", "application/x-www-form-urlencoded");

            int resCode = huc.getResponseCode();
            if(resCode==HttpURLConnection.HTTP_OK){
                InputStreamReader reader = new InputStreamReader(huc.getInputStream(), "UTF-8");
                temps = reader.read();
                sResult = temps + sResult;


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}