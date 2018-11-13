package com.ys.game.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ys.game.R;
import com.ys.game.base.BaseActivity;
import com.ys.game.util.StringUtil;

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
        userET.setText("1");
        psdET.setText("1");
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
                startActivity(new Intent(mContext, MainActivity.class));
            }
        }
    }

    @Override
    protected boolean isShowPermission() {
        return false;
    }
}
