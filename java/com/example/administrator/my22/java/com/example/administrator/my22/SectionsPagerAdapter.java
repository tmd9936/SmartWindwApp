package com.example.administrator.my22;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    final static int Fragment_Number = 3;


    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    } //page어댑터를 상속받음

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: //0번째 페이지의 인스턴스를 리턴(타이틀)
                return PlaceholderFragment.newInstance();
            case 1:
                return modeFragment.newInstance();
            case 2:
                return ControlFragment.newInstance();
            default:
                return null;
        }
    } //포지션에 맞는 프리그먼트를 리턴하는 함수

    @Override
    public int getCount() {
        return Fragment_Number;
    } //ㅠㅔ이지가 몇개인지 리턴

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "홈";
            case 1:
                return "모드";
            case 2:
                return "설정";
        }
        return null;
    } //페이지의 제목을 리턴하는 함수
}
