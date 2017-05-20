package com.example.administrator.my22;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.logging.Handler;

/**
 * Created by Administrator on 2017-04-06.
 */

public class modeFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    public modeFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static modeFragment newInstance() {
        modeFragment fragment = new modeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //n개의 탭을 만들 Fragment 생성
        View rootView = inflater.inflate(R.layout.fragment_mode, container, false);


        return rootView;
    }
}
