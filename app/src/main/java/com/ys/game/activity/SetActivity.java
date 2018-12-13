package com.ys.game.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ys.game.R;
import com.ys.game.base.BaseActivity;
import com.ys.game.util.ActivityUtil;
import com.ys.game.util.SPUtil;
import com.ys.game.util.SystemUtil;

public class SetActivity extends BaseActivity {
    private RelativeLayout backRL;
    private Button updateNickBtn, ewmBtn, exitBtn;
    private LinearLayout updateLL;
    private TextView versionNameTV;

    @Override
    public int getLayoutId() {
        return R.layout.activity_set;
    }

    @Override
    public void initView() {
        backRL = getView(R.id.rl_back);
        backRL.setOnClickListener(this);
        updateNickBtn = getView(R.id.btn_updateNick);
        updateNickBtn.setOnClickListener(this);
        updateLL = getView(R.id.ll_update);
        versionNameTV = getView(R.id.tv_versionName);
        versionNameTV.setText("v" + SystemUtil.VersionName(mContext));
        exitBtn = getView(R.id.btn_exit);
        exitBtn.setOnClickListener(this);
        ewmBtn = getView(R.id.btn_ewm);
        ewmBtn.setOnClickListener(this);
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
            case R.id.btn_updateNick:
                startActivity(new Intent(mContext,UpdateInfoActivity.class));
                break;
            case R.id.btn_ewm:
                startActivity(new Intent(mContext,EwmActivity.class));
                break;
            case R.id.ll_update:
                break;
            case R.id.btn_exit:
                SPUtil.clear(mContext);
                ActivityUtil.finish();
                startActivity(new Intent(mContext,LoginActivity.class));
                break;
        }
    }
}
