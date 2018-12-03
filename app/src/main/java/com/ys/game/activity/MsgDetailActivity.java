package com.ys.game.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ys.game.R;
import com.ys.game.base.BaseActivity;
import com.ys.game.bean.MsgBean;
import com.ys.game.util.DateUtil;
import com.ys.game.util.StringUtil;

public class MsgDetailActivity extends BaseActivity {
    private RelativeLayout backRL;
    private MsgBean.DataBeanX.DataBean dataBean;

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
        dataBean = (MsgBean.DataBeanX.DataBean) getIntent().getSerializableExtra("data");
        if (dataBean != null) {
            ((TextView) getView(R.id.tv_title)).setText("【弈尚游戏】" + StringUtil.valueOf(dataBean.news_type_name));
            ((TextView) getView(R.id.tv_time)).setText(DateUtil.changeTimeToYMD(dataBean.send_time));
            ((TextView) getView(R.id.tv_content)).setText("\t\t\t" + StringUtil.valueOf(dataBean.news_content));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                finish();
                break;
        }
    }

    public static void intentToMsg(Context context, MsgBean.DataBeanX.DataBean dataBean) {
        Intent intent = new Intent(context, MsgDetailActivity.class);
        intent.putExtra("data", dataBean);
        context.startActivity(intent);
    }
}
