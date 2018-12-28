package com.ys.game.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RelativeLayout;

import com.ys.game.R;
import com.ys.game.adapter.WinnerFragmentAdapter;
import com.ys.game.base.BaseActivity;
import com.ys.game.bean.GameBean;
import com.ys.game.dialog.DialogUtil;
import com.ys.game.dialog.List3Dialog;
import com.ys.game.fragment.WinnerJLFragment;
import com.ys.game.fragment.WinnerTZFragment;
import com.ys.game.sp.GameSP;
import com.ys.game.ui.LhViewPager;
import com.ys.game.util.YS;

import java.util.ArrayList;
import java.util.List;

//最后的胜利者
public class WinnerActivity extends BaseActivity {
    private RelativeLayout backRL;
    private TabLayout tabLayout;
    private LhViewPager vp;
    private WinnerFragmentAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_winner;
    }

    @Override
    public void initView() {
        backRL = getView(R.id.rl_back);
        backRL.setOnClickListener(this);
        tabLayout = getView(R.id.tl_cqssc);
        vp = getView(R.id.vp_cqssc);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mAdapter = new WinnerFragmentAdapter(getSupportFragmentManager(), getList());
        vp.setAdapter(mAdapter);
        vp.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(vp);
        getView(R.id.iv_right).setOnClickListener(this);
    }

    @Override
    public void getData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.iv_right:
                DialogUtil.showGameList(mContext, YS.TYPE_ZHDSLZ, new List3Dialog.ClickListener() {
                    @Override
                    public void click(int type) {
                        if (type == YS.TYPE_ZHDSLZ) {
                            WinnerActivity.toWinner(mContext);
                        } else {
                            CqsscActivity.intentToSSC(mContext, type);
                        }
                        addBean(type);
                        finish();
                    }
                });
                break;
        }
    }

    private void addBean(int type) {
        GameBean gameBean = new GameBean();
        gameBean.type = type;
        gameBean.time = System.currentTimeMillis();
        GameSP.add(mContext, gameBean);
    }

    private List<Fragment> getList() {
        List<Fragment> list = new ArrayList<>();
        list.add(new WinnerTZFragment());
        list.add(new WinnerJLFragment());
        return list;
    }

    public static void toWinner(Context context) {
        Intent intent = new Intent(context, WinnerActivity.class);
        context.startActivity(intent);
    }

}
