package com.ys.game.activity;

import android.view.KeyEvent;
import android.view.View;

import com.ys.game.R;
import com.ys.game.base.BaseActivity;
import com.ys.game.fragment.WinnerJLFragment;

public class XFJLActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_xfjl;
    }

    @Override
    public void initView() {
        backRL = getView(R.id.rl_back);
        backRL.setOnClickListener(this);
        addFragment(new WinnerJLFragment(),R.id.ll_parent);
    }

    @Override
    public void getData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
