package com.ys.game.activity;

import com.ys.game.R;
import com.ys.game.base.BaseActivity;

public class UpdateJyzhActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_update_jyzh;
    }

    @Override
    public void initView() {
        backRL = getView(R.id.rl_back);
        backRL.setOnClickListener(this);
    }

    @Override
    public void getData() {

    }
}
