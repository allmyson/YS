package com.ys.game.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.ys.game.R;
import com.ys.game.base.BaseActivity;

public class SafeActivity extends BaseActivity {
    private LinearLayout bdjyzhLL, updatePsdLL, mmbhLL, zjbhLL;

    @Override
    public int getLayoutId() {
        return R.layout.activity_safe;
    }

    @Override
    public void initView() {
        backRL = getView(R.id.rl_back);
        backRL.setOnClickListener(this);
        bdjyzhLL = getView(R.id.ll_bdjyzh);
        updatePsdLL = getView(R.id.ll_updatePsd);
        mmbhLL = getView(R.id.ll_mmbh);
        zjbhLL = getView(R.id.ll_zjbh);
        bdjyzhLL.setOnClickListener(this);
        updatePsdLL.setOnClickListener(this);
        mmbhLL.setOnClickListener(this);
        zjbhLL.setOnClickListener(this);
    }

    @Override
    public void getData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_bdjyzh:
//                startActivity(new Intent(mContext, UpdateJyzhActivity.class));
                startActivity(new Intent(mContext, BdWxzhActivity.class));
                break;
            case R.id.ll_updatePsd:
                startActivity(new Intent(mContext, UpdatePsdActivity.class));
                break;
            case R.id.ll_mmbh:
                startActivity(new Intent(mContext, MmbhActivity.class));
                break;
            case R.id.ll_zjbh:
                if (!isSet()) {
                    startActivity(new Intent(mContext, SetZjmmActivity.class));
                } else {
                    startActivity(new Intent(mContext, UpdateZjmmActivity.class));
                }
                break;
        }
    }

    private boolean isSet() {
        return true;
    }
}
