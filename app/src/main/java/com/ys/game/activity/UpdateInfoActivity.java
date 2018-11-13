package com.ys.game.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.yongchun.library.view.ImageSelectorActivity;
import com.ys.game.R;
import com.ys.game.api.FunctionApi;
import com.ys.game.base.BaseActivity;

import java.util.ArrayList;

public class UpdateInfoActivity extends BaseActivity {
    private RelativeLayout backRL;
    private ImageView headIV;
    private EditText nickNameET;
    private Button sureBtn;

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
                    Glide.with(mContext).load(images.get(0)).placeholder(R.mipmap.bg_head_default).error(R.mipmap
                            .bg_head_default).into(headIV);
                    break;
            }
        }
    }
}
