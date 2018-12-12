package com.ys.game.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ys.game.R;
import com.ys.game.adapter.CqsscFragmentAdapter;
import com.ys.game.base.BaseActivity;
import com.ys.game.bean.GameBean;
import com.ys.game.dialog.DialogUtil;
import com.ys.game.dialog.List3Dialog;
import com.ys.game.fragment.JLFragment;
import com.ys.game.fragment.TZFragment;
import com.ys.game.fragment.ZSFragment;
import com.ys.game.sp.GameSP;
import com.ys.game.ui.LhViewPager;
import com.ys.game.util.YS;

import java.util.ArrayList;
import java.util.List;

/**
 * cqssc
 */
public class CqsscActivity extends BaseActivity {
    private int type;
    private RelativeLayout backRL;
    private TabLayout tabLayout;
    private LhViewPager vp;
    private CqsscFragmentAdapter mAdapter;
    private ImageView logoIV;

    @Override
    public int getLayoutId() {
        return R.layout.activity_cqssc;
    }

    @Override
    public void initView() {
        type = getIntent().getIntExtra("type", YS.TYPE_CQSSC);
        logoIV = getView(R.id.iv_logo);
        logoIV.setOnClickListener(this);
        if (type == YS.TYPE_CQSSC) {
            logoIV.setImageResource(R.mipmap.ic_cqssc);
        } else if (type == YS.TYPE_TXFFC) {
            logoIV.setImageResource(R.mipmap.ic_txffc);
        }
        backRL = getView(R.id.rl_back);
        backRL.setOnClickListener(this);
        tabLayout = getView(R.id.tl_cqssc);
        vp = getView(R.id.vp_cqssc);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mAdapter = new CqsscFragmentAdapter(getSupportFragmentManager(), getList());
        vp.setAdapter(mAdapter);
        vp.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(vp);
        tabLayout.getTabAt(1).select();
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
            case R.id.iv_logo:
                DialogUtil.showGameList(mContext, type, new List3Dialog.ClickListener() {
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


    private List<Fragment> getList() {
        List<Fragment> list = new ArrayList<>();
        list.add(ZSFragment.newInstance());
        list.add(new TZFragment());
        list.add(new JLFragment());
        return list;
    }

    public static void intentToSSC(Context context, int type) {
        Intent intent = new Intent(context, CqsscActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    private void addBean(int type) {
        GameBean gameBean = new GameBean();
        gameBean.type = type;
        gameBean.time = System.currentTimeMillis();
        GameSP.add(mContext, gameBean);
    }
}
