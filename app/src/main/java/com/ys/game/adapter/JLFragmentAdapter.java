package com.ys.game.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename JLFragmentAdapter
 * @description -------------------------------------------------------
 * @date 2018/11/7 18:44
 */
public class JLFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> list_fragment;//fragment列表
    private FragmentManager fm;
    public static String[] titles = new String[]{
            "投注记录", "追号记录"
    };


    public JLFragmentAdapter(FragmentManager fm, List<Fragment> list_fragment) {
        super(fm);
        this.fm = fm;
        this.list_fragment = list_fragment;
    }
    public void refresh(List<Fragment> list_fragment){
        if (this.list_fragment != null) {
            FragmentTransaction ft = fm.beginTransaction();
            for (Fragment f : this.list_fragment) {
                ft.remove(f);
            }
            ft.commit();
            ft = null;
            fm.executePendingTransactions();
        }
        this.list_fragment = list_fragment;
        notifyDataSetChanged();
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



