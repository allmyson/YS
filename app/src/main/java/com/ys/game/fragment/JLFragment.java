package com.ys.game.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.ys.game.R;
import com.ys.game.adapter.JLFragmentAdapter;
import com.ys.game.base.BaseFragment;
import com.ys.game.ui.LhViewPager;
import com.ys.game.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename JLFragment
 * @description -------------------------------------------------------
 * @date 2018/11/7 18:06
 */
public class JLFragment extends BaseFragment {
    private TabLayout tabLayout;
    private LhViewPager vp;
    private JLFragmentAdapter mAdapter;
    @Override
    protected void init() {
        tabLayout = getView(R.id.tl_jl);
        vp = getView(R.id.vp_jl);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        mAdapter = new JLFragmentAdapter(getChildFragmentManager(), getList());
        vp.setAdapter(mAdapter);
        vp.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(vp);
//        DensityUtil.reflex(tabLayout);
        DensityUtil.setTabWidth(tabLayout,DensityUtil.dp2px(mContext,50));
    }

    @Override
    protected void getData() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_jl;
    }

    private List<Fragment> getList() {
        List<Fragment> list = new ArrayList<>();
        list.add(TzjlFragment.newInstance(TzjlFragment.TYPE_SDXZ));
        list.add(TzjlFragment.newInstance(TzjlFragment.TYPE_ZH));
        return list;
    }

    public void refresh(){
//        show("JLFragment刷新");
        ((TzjlFragment) mAdapter.getItem(0)).onRefresh();
        ((TzjlFragment) mAdapter.getItem(1)).onRefresh();
    }
}
