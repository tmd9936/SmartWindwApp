package com.example.administrator.my22;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.HashMap;
import java.util.Map;

import device1.DeviceTask;

/**
 * Created by Administrator on 2017-05-25.
 */

public class modeActivity extends Activity {
    private DeviceTask deviceTask;
    private Map<String, Double> rows = new HashMap<String, Double>();
   // Button btn;
    ToggleButton modetoggle;
    ToggleButton controltoggle;

    LinearLayout controlLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_control);
        Intent intent = getIntent();
        String mode = intent.getStringExtra("mode");

        //btn = (Button)findViewById(R.id.device_control);
        modetoggle = (ToggleButton)findViewById(R.id.modeToggle);
        controltoggle = (ToggleButton)findViewById(R.id.windowControl);
        controlLayout = (LinearLayout)findViewById(R.id.controlLayout);

        if(mode.equals("1.0")==true)
        {
            modetoggle.setChecked(true);
            controltoggle.setEnabled(true);
            controltoggle.setChecked(false);
        }else{
            modetoggle.setChecked(false);
            controltoggle.setEnabled(false);
        }

        modetoggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modetoggle.isChecked()){
                    startDeviceTask();
                    rows.put("WindowMode",1.0);
                    rows.put("WindowControl",0.0);
                    controltoggle.setEnabled(true);
                    controltoggle.setChecked(false);
                }else{
                    startDeviceTask();
                    rows.put("WindowMode",0.0);
                    controltoggle.setEnabled(false);
                }
            }
        });
        controltoggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(controltoggle.isChecked()){
                    startDeviceTask();
                    rows.put("WindowControl",1.0);
                }else{
                    startDeviceTask();
                    rows.put("WindowControl",0.0);
                }
            }
        });

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startDeviceTask();
//                rows.put("WindowMode",1.0);
//            }
//        });

    }
    public void onBackButtonClicked(View v) {
        Toast.makeText(this, "돌아기가 버튼이 눌렸어요.", Toast.LENGTH_LONG).show();
        finish();
    }
    private void startDeviceTask() {
        deviceTask = new DeviceTask(rows);
        deviceTask.execute();
    }

}
