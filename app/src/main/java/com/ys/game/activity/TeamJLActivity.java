package com.ys.game.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.ys.game.R;
import com.ys.game.adapter.TeamJLFragmentAdapter;
import com.ys.game.base.BaseActivity;
import com.ys.game.fragment.TeamCzjlFragment;
import com.ys.game.fragment.TeamXfjlFragment;
import com.ys.game.ui.LhViewPager;
import com.ys.game.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

public class TeamJLActivity extends BaseActivity {
    private TabLayout tabLayout;
    private LhViewPager vp;
    private TeamJLFragmentAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_team_jl;
    }

    @Override
    public void initView() {
        backRL = getView(R.id.rl_back);
        backRL.setOnClickListener(this);
        tabLayout = getView(R.id.tl_jl);
        vp = getView(R.id.vp_jl);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        mAdapter = new TeamJLFragmentAdapter(getSupportFragmentManager(), getList());
        vp.setAdapter(mAdapter);
        vp.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(vp);
//        DensityUtil.reflex(tabLayout);
        DensityUtil.setTabWidth(tabLayout,DensityUtil.dp2px(mContext,50));
    }

    @Override
    public void getData() {

    }

    private List<Fragment> getList() {
        List<Fragment> list = new ArrayList<>();
        list.add(new TeamCzjlFragment());
        list.add(new TeamXfjlFragment());
        return list;
    }
}
