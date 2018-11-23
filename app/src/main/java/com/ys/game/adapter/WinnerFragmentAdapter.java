package com.ys.game.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename WinnerFragmentAdapter
 * @description -------------------------------------------------------
 * @date 2018/11/23 14:31
 */
public class WinnerFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> list_fragment;                         //fragment列表
    public static String[] titles = new String[]{
            "投注","记录"
    };


    public WinnerFragmentAdapter(FragmentManager fm, List<Fragment> list_fragment) {
        super(fm);
        this.list_fragment = list_fragment;
    }

    @Override
    public Fragment getItem(int position) {
        return list_fragment.get(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    //此方法用来显示tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {
//        return titles[position % titles.length];
        return titles[position];
    }
}


