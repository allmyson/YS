package com.ys.game.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.upsoft.qrlibrary.QRCodeUtil;
import com.ys.game.R;
import com.ys.game.base.BaseActivity;
import com.ys.game.util.Constant;
import com.ys.game.util.L;

import java.io.File;

public class EwmActivity extends BaseActivity {
    private ImageView ewmIV;
    private RelativeLayout backRL;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ewm;
    }

    @Override
    public void initView() {
        backRL = getView(R.id.rl_back);
        backRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ewmIV = getView(R.id.iv_ewm);
    }

    @Override
    public void getData() {
        //二维码
        Bitmap log = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_logo);
        String path = Constant.EWM_PATH + File.separator + "ewm.png";
//        L.e("二维码地址：" + path);
        String shareUrl = "http://www.baidu.com";
        L.e("二维码地址：" + shareUrl);
        QRCodeUtil.createQRImage(shareUrl, 200, 200, log, path);
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        if (bitmap != null) {
            ewmIV.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
