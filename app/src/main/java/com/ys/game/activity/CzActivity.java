package com.ys.game.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class CzActivity extends BaseActivity {
    private EditText et;
    private Button sureBtn;
    private String userId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_cz;
    }

    @Override
    public void initView() {
        backRL = getView(R.id.rl_back);
        backRL.setOnClickListener(this);
        et = getView(R.id.et_);
        et.setSelection(et.getText().toString().length());
        sureBtn = getView(R.id.btn_sure);
        sureBtn.setOnClickListener(this);
    }

    @Override
    public void getData() {
        userId = UserSP.getUserId(mContext);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_sure:
                if (!StringUtil.isBlank(et.getText().toString().trim())) {
                    HttpUtil.cz(mContext, userId, et.getText().toString().trim(), new HttpListener<String>() {
                        @Override
                        public void onSucceed(int what, Response<String> response) {
                            BaseBean baseBean = new Gson().fromJson(response.get(), BaseBean.class);
                            if (baseBean != null && YS.SUCCESE.equals(baseBean.code)) {
                                show(baseBean.msg);
                                finish();
                            } else {
                                show("充值失败！");
                            }
                        }

                        @Override
                        public void onFailed(int what, Response<String> response) {

                        }
                    });
                } else {
                    show("充值金额不能为空！");
                }
                break;
        }
    }
}
