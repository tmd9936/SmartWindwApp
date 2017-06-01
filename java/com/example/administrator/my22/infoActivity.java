package com.example.administrator.my22;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Administrator on 2017-05-25.
 */

public class infoActivity extends Activity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mode);
    }
    public void onBackButtonClicked(View v) {
        Toast.makeText(this, "돌아기가 버튼이 눌렸어요.", Toast.LENGTH_LONG).show();
        finish();
    }
}

