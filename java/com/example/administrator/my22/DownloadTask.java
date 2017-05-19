package com.example.administrator.my22;

import android.os.AsyncTask;
import android.os.Handler;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadTask extends AsyncTask<String, String, String> {
    @Override
    protected String doInBackground(String... urls) {
        String result = null;
        URL url;
        HttpURLConnection urlConnection;
        try {

            url = new URL("https://iotmakers.olleh.com:443/api/v1/streams/tmd993D1494308925360/log/last");
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int data = reader.read();

            while (data != -1) {
                char current = (char) data;
                result += current;
                data = reader.read();
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        try {
            JSONObject jsonObject = new JSONObject(result);

            JSONObject Data = new JSONObject(jsonObject.getString("data"));
            JSONObject attribute = new JSONObject(Data.getString("attributes"));

            int tempInt = Integer.parseInt(attribute.getString("tempre"));

            //mcb.onDataLoaded(tempInt);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
