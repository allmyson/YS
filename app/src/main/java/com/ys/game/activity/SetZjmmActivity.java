package com.ys.game.activity;

import android.view.View;

import com.ys.game.R;
import com.ys.game.base.BaseActivity;

public class SetZjmmActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_set_zjmm;
    }

    @Override
    public void initView() {
        backRL = getView(R.id.rl_back);
        backRL.setOnClickListener(this);

    }

    @Override
    public void getData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){

        }
    }
}
