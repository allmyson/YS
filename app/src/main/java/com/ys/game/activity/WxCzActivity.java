package com.ys.game.activity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yanzhenjie.nohttp.rest.Response;
import com.ys.game.R;
import com.ys.game.api.FunctionApi;
import com.ys.game.base.BaseActivity;
import com.ys.game.bean.BaseBean;
import com.ys.game.http.HttpListener;
import com.ys.game.util.Constant;
import com.ys.game.util.FileUtil;
import com.ys.game.util.HttpUtil;
import com.ys.game.util.StringUtil;
import com.ys.game.util.YS;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//wx cz
public class WxCzActivity extends BaseActivity {
    private ImageView codeIV;
    private String imagePath;
    private Button saveBtn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_wx_cz;
    }

    @Override
    public void initView() {
        backRL = getView(R.id.rl_back);
        backRL.setOnClickListener(this);
        codeIV = getView(R.id.iv_code);
        codeIV.setOnClickListener(this);
        saveBtn = getView(R.id.btn_save);
        saveBtn.setOnClickListener(this);
    }

    @Override
    public void getData() {
        getBossPay();
    }

    private void getBossPay() {
        HttpUtil.getBossPay(mContext, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                BaseBean baseBean = new Gson().fromJson(response.get(), BaseBean.class);
                if (baseBean != null && YS.SUCCESE.equals(baseBean.code)) {
                    imagePath = StringUtil.valueOf(baseBean.data);
                    Glide.with(mContext).load(StringUtil.valueOf(baseBean.data)).placeholder(R.mipmap.bg_default_rect).error(R.mipmap.bg_default_rect).into(codeIV);
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_code:
                startBrowse();
                break;
            case R.id.btn_save:
                save();
                break;
        }
    }

    private void startBrowse() {
    }

    private void save() {
        codeIV.setDrawingCacheEnabled(true);

        Bitmap bitmap = codeIV.getDrawingCache();
        if (bitmap != null) {
            String path = FileUtil.saveBitmap(bitmap, Constant.TEMPIM_PATH);
            File file = new File(path);
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//android-7.0
                String authority = FunctionApi.getAuthority(mContext);
                uri = FileProvider.getUriForFile(this, authority, file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);//
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            } else {
                uri = Uri.fromFile(file);//
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);//
            }
            try {
                MediaStore.Images.Media.insertImage(mContext.getContentResolver(),
                        path, file.getName(), null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // 最后通知图库更新
            mContext.sendBroadcast(intent);
            show("图片已经保存到相册");
        }
        codeIV.setDrawingCacheEnabled(false);
    }
}
