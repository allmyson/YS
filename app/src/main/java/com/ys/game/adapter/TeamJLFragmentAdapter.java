package com.ys.game.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename JLFragmentAdapter
 * @description -------------------------------------------------------
 * @date 2018/11/7 18:44
 */
public class TeamJLFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> list_fragment;                         //fragment列表
    public static String[] titles = new String[]{
            "充值记录", "消费记录"
    };


    public TeamJLFragmentAdapter(FragmentManager fm, List<Fragment> list_fragment) {
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



