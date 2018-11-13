package com.ys.game.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename CqsscFragmentAdapter
 * @description -------------------------------------------------------
 * @date 2018/11/1 11:47
 */
public class CqsscFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> list_fragment;                         //fragment列表
    public static String[] titles = new String[]{
            "走势", "投注","记录"
    };


    public CqsscFragmentAdapter(FragmentManager fm, List<Fragment> list_fragment) {
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


