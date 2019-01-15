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
import com.ys.game.util.KeyBoardUtils;
import com.ys.game.util.StringUtil;
import com.ys.game.util.YS;

public class SetZjmmActivity extends BaseActivity {

    private EditText loginPsdET, psdET, rePsdET;
    private Button sureBtn;
    private String userId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_set_zjmm;
    }

    @Override
    public void initView() {
        backRL = getView(R.id.rl_back);
        backRL.setOnClickListener(this);
        loginPsdET = getView(R.id.et_loginPsd);
        psdET = getView(R.id.et_psd);
        rePsdET = getView(R.id.et_rePsd);
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
                if (isCanUpdate()) {
                    KeyBoardUtils.closeKeybord(loginPsdET, mContext);
                    KeyBoardUtils.closeKeybord(psdET, mContext);
                    KeyBoardUtils.closeKeybord(rePsdET, mContext);
                    update();
                }
                break;
        }
    }

    private boolean isCanUpdate() {
        if (StringUtil.isBlank(loginPsdET.getText().toString().trim())) {
            show("登录密码不能为空");
            return false;
        } else if (StringUtil.isBlank(psdET.getText().toString().trim())) {
            show("资金密码不能为空");
            return false;
        } else if (StringUtil.isBlank(rePsdET.getText().toString().trim())) {
            show("资金密码不能为空");
            return false;
        } else if (!psdET.getText().toString().trim().equals(rePsdET.getText().toString().trim())) {
            show("两次输入的资金密码不一致");
            return false;
        } else {
            return true;
        }
    }

    private void update() {
        HttpUtil.updateZJMM(mContext, userId, loginPsdET.getText().toString().trim(), psdET.getText().toString(), new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                BaseBean baseBean = new Gson().fromJson(response.get(), BaseBean.class);
                if (baseBean != null) {
                    if (YS.SUCCESE.equals(baseBean.code)) {
                        show(StringUtil.valueOf(baseBean.msg));
                        finish();
                    } else {
                        show(StringUtil.valueOf(baseBean.msg));
                    }
                } else {
                    show("修改失败");
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }
}
