package com.ys.game.activity;


import com.ys.game.R;
import com.ys.game.base.BaseActivity;
//wx cz
public class WxCzActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_wx_cz;
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
