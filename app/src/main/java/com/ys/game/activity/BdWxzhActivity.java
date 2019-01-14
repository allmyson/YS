package com.ys.game.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yongchun.library.view.ImageSelectorActivity;
import com.ys.game.R;
import com.ys.game.api.FunctionApi;
import com.ys.game.base.BaseActivity;

import java.util.ArrayList;

//bind wx 收款码
public class BdWxzhActivity extends BaseActivity {
    private Button chooseBtn,sureBtn;
    private ImageView codeIV;
    private String photoUrl;
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

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.btn_choose:
                FunctionApi.takePicture(mContext, 1, 2, true, true, true);
                break;
            case R.id.btn_sure:
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
                            .bg_head_default).into(codeIV);
                    break;
            }
        }
    }
}
