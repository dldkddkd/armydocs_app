package com.example.armydocs.Util;

import android.app.Activity;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.armydocs.Tutorial.TutorialFirstPage;
import com.example.armydocs.Tutorial.TutorialFourthPage;
import com.example.armydocs.Tutorial.TutorialSecondPage;
import com.example.armydocs.Tutorial.TutorialThirdPage;

import java.util.ArrayList;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mainPage; //메인페이지의 프레그먼트 저장
    private long time;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);

        mainPage = new ArrayList<>();
        mainPage.add(new TutorialSecondPage());
        mainPage.add(new TutorialFirstPage());
        mainPage.add(new TutorialThirdPage());
    }

    @Override // 메인페이지의 포지션을 꺼낸다.
    public Fragment getItem(int position) { return mainPage.get(position); }

    @Override // 메인페이지의 총 크기
    public int getCount() {
        return mainPage.size();
    }

}
