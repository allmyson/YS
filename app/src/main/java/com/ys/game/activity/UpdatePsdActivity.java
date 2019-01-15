package com.ys.game.activity;

import android.app.Activity;
import android.content.Intent;
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
import com.ys.game.util.ActivityUtil;
import com.ys.game.util.HttpUtil;
import com.ys.game.util.KeyBoardUtils;
import com.ys.game.util.StringUtil;
import com.ys.game.util.YS;

public class UpdatePsdActivity extends BaseActivity {
    private EditText oldET, newET, reNewET;
    private Button sureBtn;
    private String userId;
    @Override
    public int getLayoutId() {
        return R.layout.activity_update_psd;
    }

    @Override
    public void initView() {
        backRL = getView(R.id.rl_back);
        backRL.setOnClickListener(this);
        oldET = getView(R.id.et_oldPsd);
        newET = getView(R.id.et_newPsd);
        reNewET = getView(R.id.et_reNewPsd);
        sureBtn = getView(R.id.btn_sure);
        sureBtn.setOnClickListener(this);
    }

    @Override
    public void getData() {
        userId = UserSP.getUserId(mContext);
    }

    private boolean isCan() {
        if (StringUtil.isBlank(oldET.getText().toString().trim())) {
            show("旧密码不能为空");
            return false;
        } else if (StringUtil.isBlank(newET.getText().toString().trim()) || StringUtil.isBlank(reNewET.getText().toString().trim())) {
            show("新密码不能为空");
            return false;
        } else if (!newET.getText().toString().trim().equals(reNewET.getText().toString().trim())) {
            show("两次输入密码不一致");
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.btn_sure:
                if(isCan()){
                    KeyBoardUtils.closeKeybord(oldET,mContext);
                    KeyBoardUtils.closeKeybord(newET,mContext);
                    KeyBoardUtils.closeKeybord(reNewET,mContext);
                    update();
                }
                break;
        }
    }

    private void update(){
        HttpUtil.updatePsd(mContext, userId, oldET.getText().toString().trim(), newET.getText().toString().trim(), new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                BaseBean baseBean = new Gson().fromJson(response.get(),BaseBean.class);
                if(baseBean!=null){
                    if(YS.SUCCESE.equals(baseBean.code)){
                        show(StringUtil.valueOf(baseBean.msg));
                        UserSP.clear(mContext);
                        ActivityUtil.finish();
                        startActivity(new Intent(mContext,WelcomeActivity.class));
                    }else {
                        show(StringUtil.valueOf(baseBean.code));
                    }
                }else {
                    show("修改失败");
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }
}
