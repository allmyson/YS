package com.ys.game.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RelativeLayout;

import com.ys.game.R;
import com.ys.game.adapter.CqsscFragmentAdapter;
import com.ys.game.base.BaseActivity;
import com.ys.game.fragment.JLFragment;
import com.ys.game.fragment.TZFragment;
import com.ys.game.fragment.ZSFragment;
import com.ys.game.ui.LhViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * cqssc
 */
public class CqsscActivity extends BaseActivity {
    private RelativeLayout backRL;
    private TabLayout tabLayout;
    private LhViewPager vp;
    private CqsscFragmentAdapter mAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_cqssc;
    }

    @Override
    public void initView() {
        backRL = getView(R.id.rl_back);
        backRL.setOnClickListener(this);
        tabLayout = getView(R.id.tl_cqssc);
        vp = getView(R.id.vp_cqssc);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mAdapter = new CqsscFragmentAdapter(getSupportFragmentManager(), getList());
        vp.setAdapter(mAdapter);
        vp.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(vp);
    }

    @Override
    public void getData() {

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }


    private List<Fragment> getList() {
        List<Fragment> list = new ArrayList<>();
        list.add(ZSFragment.newInstance());
        list.add(new TZFragment());
        list.add(new JLFragment());
        return list;
    }
}
