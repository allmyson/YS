package com.ys.game.activity;


import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yanzhenjie.nohttp.rest.Response;
import com.ys.game.R;
import com.ys.game.base.BaseActivity;
import com.ys.game.bean.BaseBean;
import com.ys.game.http.HttpListener;
import com.ys.game.util.HttpUtil;
import com.ys.game.util.StringUtil;
import com.ys.game.util.YS;

//wx cz
public class WxCzActivity extends BaseActivity {
    private ImageView codeIV;

    @Override
    public int getLayoutId() {
        return R.layout.activity_wx_cz;
    }

    @Override
    public void initView() {
        backRL = getView(R.id.rl_back);
        backRL.setOnClickListener(this);
        codeIV = getView(R.id.iv_code);
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
                    Glide.with(mContext).load(StringUtil.valueOf(baseBean.data)).placeholder(R.mipmap.bg_default_rect).error(R.mipmap.bg_default_rect).into(codeIV);
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }
}
