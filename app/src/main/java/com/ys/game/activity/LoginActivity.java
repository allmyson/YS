package com.ys.game.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.rest.Response;
import com.ys.game.R;
import com.ys.game.base.BaseActivity;
import com.ys.game.bean.LoginBean;
import com.ys.game.http.HttpListener;
import com.ys.game.sp.UserSP;
import com.ys.game.util.HttpUtil;
import com.ys.game.util.StringUtil;
import com.ys.game.util.YS;

public class LoginActivity extends BaseActivity {
    private EditText userET, psdET;
    private Button loginBtn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        userET = getView(R.id.et_username);
        psdET = getView(R.id.et_psd);
        loginBtn = getView(R.id.btn_login);
        loginBtn.setOnClickListener(this);
        userET.setText("cy");
        psdET.setText("123456");
        userET.setSelection(userET.getText().toString().length());
    }

    @Override
    public void getData() {

    }

    private boolean isCanLogin() {
        String user = userET.getText().toString().trim();
        String psd = psdET.getText().toString().trim();
        if (StringUtil.isBlank(user)) {
            show("用户名不能为空");
            return false;
        } else if (StringUtil.isBlank(psd)) {
            show("密码不能为空");
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {
            if (isCanLogin()) {
                HttpUtil.login(mContext, userET.getText().toString().trim(), psdET.getText()
                        .toString().trim(), new HttpListener<String>() {
                    @Override
                    public void onSucceed(int what, Response<String> response) {
                        LoginBean loginBean = new Gson().fromJson(response.get(), LoginBean.class);
                        if (loginBean != null && YS.SUCCESE.equals(loginBean.code) && loginBean.data != null) {
                            UserSP.saveInfo(mContext, response.get());
                            startActivity(new Intent(mContext, MainActivity.class));
                            finish();
                        } else {
                            if (loginBean != null) {
                                show(StringUtil.valueOf(loginBean.msg));
                            } else {
                                show("登录失败");
                            }
                        }
                    }

                    @Override
                    public void onFailed(int what, Response<String> response) {

                    }
                });
            }
        }
    }

    @Override
    protected boolean isShowPermission() {
        return false;
    }
}
