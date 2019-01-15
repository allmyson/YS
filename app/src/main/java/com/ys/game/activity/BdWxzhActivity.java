package com.ys.game.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yanzhenjie.nohttp.rest.Response;
import com.yongchun.library.view.ImageSelectorActivity;
import com.ys.game.R;
import com.ys.game.api.FunctionApi;
import com.ys.game.base.BaseActivity;
import com.ys.game.bean.BaseBean;
import com.ys.game.bean.DetailUserInfo;
import com.ys.game.http.HttpListener;
import com.ys.game.sp.UserSP;
import com.ys.game.util.HttpUtil;
import com.ys.game.util.StringUtil;
import com.ys.game.util.YS;

import java.io.File;
import java.util.ArrayList;

//bind wx 收款码
public class BdWxzhActivity extends BaseActivity {
    private Button chooseBtn, sureBtn;
    private ImageView codeIV;
    private String photoUrl;
    private String userId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bd_wxzh;
    }

    @Override
    public void initView() {
        backRL = getView(R.id.rl_back);
        backRL.setOnClickListener(this);
        chooseBtn = getView(R.id.btn_choose);
        sureBtn = getView(R.id.btn_sure);
        chooseBtn.setOnClickListener(this);
        sureBtn.setOnClickListener(this);
        codeIV = getView(R.id.iv_code);
    }

    @Override
    public void getData() {
        userId = UserSP.getUserId(mContext);
        getDetailInfo();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_choose:
                FunctionApi.takePicture(mContext, 1, 2, true, true, true);
                break;
            case R.id.btn_sure:
                if (!StringUtil.isBlank(photoUrl) && new File(photoUrl).exists()) {
                    bindPay();
                } else {
                    show("请选择新的收款二维码后再进行上传");
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
                    Glide.with(mContext).load(photoUrl).placeholder(R.mipmap.bg_default_rect).error(R.mipmap
                            .bg_default_rect).into(codeIV);
                    break;
            }
        }
    }

    private void getDetailInfo() {
        HttpUtil.getDetailUserInfo(mContext, userId, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                DetailUserInfo detailUserInfo = new Gson().fromJson(response.get(), DetailUserInfo.class);
                if (detailUserInfo != null && YS.SUCCESE.equals(detailUserInfo.code) && detailUserInfo.data != null) {
                    Glide.with(mContext).load(StringUtil.valueOf(detailUserInfo.data.payUrl)).placeholder(R.mipmap.bg_default_rect).error(R.mipmap
                            .bg_default_rect).into(codeIV);
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }

    private void bindPay() {
        HttpUtil.bindUserCode(mContext, userId, photoUrl, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                BaseBean baseBean = new Gson().fromJson(response.get(), BaseBean.class);
                if (baseBean != null && YS.SUCCESE.equals(baseBean.code)) {
                    show(StringUtil.valueOf(baseBean.msg));
                } else {
                    show("上传失败");
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }
}
