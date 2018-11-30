package com.ys.game.activity;

import android.view.View;
import android.widget.TextView;

import com.ys.game.R;
import com.ys.game.base.BaseActivity;
import com.ys.game.util.ClipboardUtils;
import com.ys.game.util.YS;

//联系客服
public class LxkfActivity extends BaseActivity {
    private TextView wxTV,qqTV;

    @Override
    public int getLayoutId() {
        return R.layout.activity_lxkf;
    }

    @Override
    public void initView() {
        backRL = getView(R.id.rl_back);
        backRL.setOnClickListener(this);
        wxTV = getView(R.id.tv_wx);
        qqTV = getView(R.id.tv_qq);
        wxTV.setText("官方微信号："+ YS.KF_WX);
        qqTV.setText("官方QQ号："+ YS.KF_QQ);
        getView(R.id.btn_fz_wx).setOnClickListener(this);
        getView(R.id.btn_fz_qq).setOnClickListener(this);

    }

    @Override
    public void getData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.btn_fz_wx:
                ClipboardUtils.copyText(mContext,YS.KF_WX);
                show("复制成功！");
                break;
            case R.id.btn_fz_qq:
                ClipboardUtils.copyText(mContext,YS.KF_QQ);
                show("复制成功！");
                break;
        }
    }
}
