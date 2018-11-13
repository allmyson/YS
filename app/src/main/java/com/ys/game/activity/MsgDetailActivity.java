package com.ys.game.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.ys.game.R;
import com.ys.game.base.BaseActivity;

public class MsgDetailActivity extends BaseActivity {
    private RelativeLayout backRL;

    @Override
    public int getLayoutId() {
        return R.layout.activity_msg_detail;
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
        switch (v.getId()) {
            case R.id.rl_back:
                finish();
                break;
        }
    }

    public static void intentToMsg(Context context) {
        context.startActivity(new Intent(context, MsgDetailActivity.class));
    }
}
