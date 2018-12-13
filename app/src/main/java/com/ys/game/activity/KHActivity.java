package com.ys.game.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.rest.Response;
import com.ys.game.R;
import com.ys.game.base.BaseActivity;
import com.ys.game.bean.BaseBean;
import com.ys.game.bean.LoginBean;
import com.ys.game.http.HttpListener;
import com.ys.game.sp.UserSP;
import com.ys.game.util.HttpUtil;
import com.ys.game.util.StringUtil;
import com.ys.game.util.YS;

public class KHActivity extends BaseActivity {

    private LoginBean loginBean;
    private String userId;
    private EditText nickNameET, loginNameET, psdET, rePsdET, fdET;
    private TextView currentLoginNameTV;
    private Button sureBtn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_kh;
    }

    @Override
    public void initView() {
        backRL = getView(R.id.rl_back);
        backRL.setOnClickListener(this);
        nickNameET = getView(R.id.et_nickName);
        loginNameET = getView(R.id.et_loginName);
        psdET = getView(R.id.et_psd);
        rePsdET = getView(R.id.et_rePsd);
        fdET = getView(R.id.et_fd);
        currentLoginNameTV = getView(R.id.tv_currentLoginName);
        sureBtn = getView(R.id.btn_sure);
        sureBtn.setOnClickListener(this);
    }

    @Override
    public void getData() {
        loginBean = UserSP.getInfo(mContext);
        if (loginBean != null && loginBean.data != null) {
            userId = loginBean.data.consumerId;
            currentLoginNameTV.setText(StringUtil.valueOf(loginBean.data.loginName));
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_sure:
                if (isCan()) {
                    HttpUtil.kh(mContext, userId, nickNameET.getText().toString().trim(), loginNameET.getText()
                                    .toString().trim(), psdET.getText().toString().trim(), fdET.getText().toString()
                                    .trim(),
                            new HttpListener<String>() {
                                @Override
                                public void onSucceed(int what, Response<String> response) {
                                    BaseBean baseBean = new Gson().fromJson(response.get(), BaseBean.class);
                                    if (baseBean != null && YS.SUCCESE.equals(baseBean.code)) {
                                        show(baseBean.msg);
                                        finish();
                                    } else {
                                        show("开户失败!");
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

    private boolean isCan() {
        if (StringUtil.isBlank(nickNameET.getText().toString().trim())) {
            show("会员昵称不能为空!");
            return false;
        } else if (StringUtil.isBlank(loginNameET.getText().toString().trim())) {
            show("登录用户名不能为空!");
            return false;
        } else if (StringUtil.isBlank(psdET.getText().toString().trim())) {
            show("密码不能为空!");
            return false;
        } else if (StringUtil.isBlank(rePsdET.getText().toString().trim())) {
            show("密码不能为空!");
            return false;
        } else if (StringUtil.isBlank(fdET.getText().toString().trim())) {
            show("返点比例不能为空!");
            return false;
        } else if (!psdET.getText().toString().trim().equals(rePsdET.getText().toString().trim())) {
            show("两次输入密码不一致!");
            return false;
        }
        return true;
    }
}
