package com.ys.game.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yanzhenjie.nohttp.rest.Response;
import com.ys.game.R;
import com.ys.game.base.BaseActivity;
import com.ys.game.bean.DetailUserInfo;
import com.ys.game.http.HttpListener;
import com.ys.game.sp.UserSP;
import com.ys.game.util.HttpUtil;
import com.ys.game.util.StringUtil;
import com.ys.game.util.YS;

public class SafeActivity extends BaseActivity {
    private LinearLayout bdjyzhLL, updatePsdLL, mmbhLL, zjbhLL;
    private String userId;
    private TextView zjmmTV, ptjyzhTV;
    private ImageView ptjyzhIV,zjmmIV;
    @Override
    public int getLayoutId() {
        return R.layout.activity_safe;
    }

    @Override
    public void initView() {
        zjmmIV = getView(R.id.iv_zjmm);
        ptjyzhIV = getView(R.id.iv_ptjyzh);
        ptjyzhTV = getView(R.id.tv_ptjyzh);
        zjmmTV = getView(R.id.tv_zjmm);
        backRL = getView(R.id.rl_back);
        backRL.setOnClickListener(this);
        bdjyzhLL = getView(R.id.ll_bdjyzh);
        updatePsdLL = getView(R.id.ll_updatePsd);
        mmbhLL = getView(R.id.ll_mmbh);
        zjbhLL = getView(R.id.ll_zjbh);
        bdjyzhLL.setOnClickListener(this);
        updatePsdLL.setOnClickListener(this);
        mmbhLL.setOnClickListener(this);
        zjbhLL.setOnClickListener(this);
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
            case R.id.ll_bdjyzh:
//                startActivity(new Intent(mContext, UpdateJyzhActivity.class));
                startActivity(new Intent(mContext, BdWxzhActivity.class));
                break;
            case R.id.ll_updatePsd:
                startActivity(new Intent(mContext, UpdatePsdActivity.class));
                break;
            case R.id.ll_mmbh:
                startActivity(new Intent(mContext, MmbhActivity.class));
                break;
            case R.id.ll_zjbh:
//                if (!isSet()) {
                startActivity(new Intent(mContext, SetZjmmActivity.class));
//                } else {
//                    startActivity(new Intent(mContext, UpdateZjmmActivity.class));
//                }
                break;
        }
    }

    private boolean isSet() {
        return true;
    }


    private void getDetailInfo() {
        HttpUtil.getDetailUserInfo(mContext, userId, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                DetailUserInfo detailUserInfo = new Gson().fromJson(response.get(), DetailUserInfo.class);
                if (detailUserInfo != null && YS.SUCCESE.equals(detailUserInfo.code) && detailUserInfo.data != null) {
                    if (!StringUtil.isBlank(detailUserInfo.data.moneyPwd)) {
                        zjmmTV.setText("修改资金密码");
                        zjmmIV.setImageResource(R.mipmap.ic_zjmm_blue);
                    }
                    if (!StringUtil.isBlank(detailUserInfo.data.payUrl)) {
                        ptjyzhTV.setText("修改平台交易账号");
                        ptjyzhIV.setImageResource(R.mipmap.ic_ptjyzh_blue);
                    }
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }
}
