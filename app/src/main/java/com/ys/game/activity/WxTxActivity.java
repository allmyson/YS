package com.ys.game.activity;


import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.rest.Response;
import com.ys.game.R;
import com.ys.game.base.BaseActivity;
import com.ys.game.bean.BaseBean;
import com.ys.game.http.HttpListener;
import com.ys.game.sp.UserSP;
import com.ys.game.util.HttpUtil;
import com.ys.game.util.StringUtil;
import com.ys.game.util.YS;

public class WxTxActivity extends BaseActivity {
    private EditText et;
    private String money;//可用余额
    private TextView yueTV;
    private String userId;
    private EditText psdET;

    @Override
    public int getLayoutId() {
        return R.layout.activity_wx_tx;
    }

    @Override
    public void initView() {
        getView(R.id.btn_sure).setOnClickListener(this);
        psdET = getView(R.id.et_psd);
        yueTV = getView(R.id.tv_yue);
        backRL = getView(R.id.rl_back);
        backRL.setOnClickListener(this);
        et = getView(R.id.et_);
        et.setSelection(et.getText().toString().length());
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_sure:
                if (canTX()) {
                    HttpUtil.tx(mContext, userId, et.getText().toString().trim(), psdET.getText().toString().trim(),
                            new HttpListener<String>() {
                                @Override
                                public void onSucceed(int what, Response<String> response) {
                                    BaseBean baseBean = new Gson().fromJson(response.get(), BaseBean.class);
                                    if (baseBean != null && YS.SUCCESE.equals(baseBean.code)) {
                                        show(baseBean.msg);
                                        finish();
                                    } else {
                                        show("提现申请失败!");
                                    }
                                }
                                @Override
                                public void onFailed(int what, Response<String> response) {

                                }
                            });
                }
                break;
        }
    }

    @Override
    public void getData() {
        userId = UserSP.getUserId(mContext);
        money = getIntent().getStringExtra("money");
        yueTV.setText("可用余额\t\t" + StringUtil.StringToDoubleStr(money) + "YB");
    }

    public static void intentToTX(Context context, String money) {
        Intent intent = new Intent(context, WxTxActivity.class);
        intent.putExtra("money", money);
        context.startActivity(intent);
    }

    private boolean canTX() {
        if (StringUtil.isBlank(et.getText().toString().trim())) {
            show("提现金额不能为空！");
            return false;
        } else if (StringUtil.isBlank(psdET.getText().toString().trim())) {
            show("资金密码不能为空！");
            return false;
        }
        return true;
    }
}
