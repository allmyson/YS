package com.ys.game.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yanzhenjie.nohttp.rest.Response;
import com.yongchun.library.view.ImageSelectorActivity;
import com.ys.game.R;
import com.ys.game.api.FunctionApi;
import com.ys.game.base.BaseActivity;
import com.ys.game.bean.BaseBean;
import com.ys.game.http.HttpListener;
import com.ys.game.sp.UserSP;
import com.ys.game.util.HttpUtil;
import com.ys.game.util.StringUtil;
import com.ys.game.util.YS;

import java.util.ArrayList;

public class UpdateInfoActivity extends BaseActivity {
    private RelativeLayout backRL;
    private ImageView headIV;
    private EditText nickNameET;
    private Button sureBtn;
    private String photoUrl;
    private String userId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_update_info;
    }

    @Override
    public void initView() {
        backRL = getView(R.id.rl_back);
        backRL.setOnClickListener(this);
        sureBtn = getView(R.id.btn_sure);
        sureBtn.setOnClickListener(this);
        headIV = getView(R.id.iv_head);
        headIV.setOnClickListener(this);
        nickNameET = getView(R.id.et_nickName);
    }

    @Override
    public void getData() {
        userId = UserSP.getUserId(mContext);
        nickNameET.setText(getIntent().getStringExtra("nickName"));
        nickNameET.setSelection(nickNameET.getText().length());
        Glide.with(mContext).load(getIntent().getStringExtra("photoUrl")).placeholder(R.mipmap.bg_head_default).error
                (R.mipmap
                .bg_head_default).into(headIV);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.iv_head:
                FunctionApi.takePicture(mContext, 1, 2, true, true, true);
                break;
            case R.id.btn_sure:
                if (!StringUtil.isBlank(nickNameET.getText().toString())) {
                    HttpUtil.updateUserInfo(mContext, userId, nickNameET.getText().toString(), photoUrl, new
                            HttpListener<String>() {
                        @Override
                        public void onSucceed(int what, Response<String> response) {
                            BaseBean baseBean = new Gson().fromJson(response.get(), BaseBean.class);
                            if (baseBean != null && YS.SUCCESE.equals(baseBean.code)) {
                                show(baseBean.msg);
                                finish();
                            } else {
                                show("修改失败！");
                            }
                        }

                        @Override
                        public void onFailed(int what, Response<String> response) {

                        }
                    });
                } else {
                    show("昵称不能为空");
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ImageSelectorActivity.REQUEST_IMAGE://相册图片选取返回
                    ArrayList<String> images = (ArrayList<String>) data.getSerializableExtra(ImageSelectorActivity
                            .REQUEST_OUTPUT);
                    photoUrl = images.get(0);
                    Glide.with(mContext).load(photoUrl).placeholder(R.mipmap.bg_head_default).error(R.mipmap
                            .bg_head_default).into(headIV);
                    break;
            }
        }
    }


    public static void intentUpdateInfo(Context context, String nickName, String photoUrl) {
        Intent intent = new Intent(context, UpdateInfoActivity.class);
        intent.putExtra("nickName", nickName);
        intent.putExtra("photoUrl", photoUrl);
        context.startActivity(intent);
    }
}
