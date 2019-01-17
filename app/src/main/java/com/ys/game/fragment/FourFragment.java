package com.ys.game.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yanzhenjie.nohttp.rest.Response;
import com.ys.game.R;
import com.ys.game.activity.CzActivity;
import com.ys.game.activity.KHActivity;
import com.ys.game.activity.LxkfActivity;
import com.ys.game.activity.SafeActivity;
import com.ys.game.activity.SetActivity;
import com.ys.game.activity.TeamGLActivity;
import com.ys.game.activity.TeamJLActivity;
import com.ys.game.activity.TxActivity;
import com.ys.game.activity.UpdateInfoActivity;
import com.ys.game.activity.WxCzActivity;
import com.ys.game.activity.WxTxActivity;
import com.ys.game.activity.XFJLActivity;
import com.ys.game.adapter.MyAdapter;
import com.ys.game.base.BaseFragment;
import com.ys.game.bean.LoginBean;
import com.ys.game.bean.UserInfo;
import com.ys.game.http.HttpListener;
import com.ys.game.sp.UserSP;
import com.ys.game.ui.MyListView;
import com.ys.game.util.HttpUtil;
import com.ys.game.util.StringUtil;
import com.ys.game.util.YS;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename OneFragment
 * @description -------------------------------------------------------
 * @date 2018/10/23 17:09
 */
public class FourFragment extends BaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private MyListView mlv;
    private List<String> myList;
    private MyAdapter myAdapter;
    private RelativeLayout setRL;
    private Button czBtn, tbBtn;
    private TextView nicknameTV, usernameTV, fdTV, yueTV, levelTV;
    private LoginBean loginBean;
    private ImageView headIV;
    private TextView jrczTV, jrxfTV, jrylTV, canTmoneyTV;
    private String nickName;
    private String photoUrl;

    public static FourFragment newInstance() {
        return new FourFragment();
    }

    private SwipeRefreshLayout srl;
    private String userId;
    private String yue;

    @Override
    protected void init() {
        levelTV = getView(R.id.tv_level);
        jrczTV = getView(R.id.tv_jrcz);
        jrxfTV = getView(R.id.tv_jrxf);
        jrylTV = getView(R.id.tv_jryl);
        canTmoneyTV = getView(R.id.tv_canTmoney);
        headIV = getView(R.id.iv_head);
        mlv = getView(R.id.mlv_my);
        getView(R.id.ll_updateInfo).setOnClickListener(this);
        getView(R.id.btn_cz).setOnClickListener(this);
        getView(R.id.btn_tb).setOnClickListener(this);
        myList = new ArrayList<>();
        myAdapter = new MyAdapter(mContext, myList, R.layout.item_msg);
        mlv.setAdapter(myAdapter);
        mlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = myAdapter.getItem(position);
                if ("消费记录".equals(name)) {
                    startActivity(new Intent(mContext, XFJLActivity.class));
                } else if ("安全中心".equals(name)) {
                    startActivity(new Intent(mContext, SafeActivity.class));
                } else if ("开户".equals(name)) {
                    startActivity(new Intent(mContext, KHActivity.class));
                } else if ("团队管理".equals(name)) {
                    startActivity(new Intent(mContext, TeamGLActivity.class));
                } else if ("团队记录".equals(name)) {
                    startActivity(new Intent(mContext, TeamJLActivity.class));
                } else if ("联系客服".equals(name)) {
                    startActivity(new Intent(mContext, LxkfActivity.class));
                }
            }
        });

        setRL = getView(R.id.rl_set);
        setRL.setOnClickListener(this);

        nicknameTV = getView(R.id.tv_nickName);
        usernameTV = getView(R.id.tv_username);
        fdTV = getView(R.id.tv_fd);
        yueTV = getView(R.id.tv_yue);

        srl = (SwipeRefreshLayout) mView.findViewById(R.id.srl);
        srl.setOnRefreshListener(this);
        srl.setColorSchemeColors(getResources().getColor(R.color.main_color));
    }

    @Override
    protected void getData() {
        userId = UserSP.getUserId(mContext);
        loginBean = UserSP.getInfo(mContext);
        if (loginBean != null && loginBean.data != null) {
            nickName = StringUtil.valueOf(loginBean.data.consumerName);
            photoUrl = loginBean.data.consumerImg;
            yue = "" + StringUtil.StringToDouble(loginBean.data.balance);
            nicknameTV.setText("昵称：" + StringUtil.valueOf(loginBean.data.consumerName));
            usernameTV.setText("用户名：" + StringUtil.valueOf(loginBean.data.loginName));
            fdTV.setText("返点：" + StringUtil.valueOf(loginBean.data.backNum));
            yueTV.setText("" + StringUtil.StringToDoubleStr(loginBean.data.balance) + "YB");
            canTmoneyTV.setText("" + StringUtil.StringToDoubleStr(loginBean.data.balance) + "YB");
            Glide.with(mContext).load(loginBean.data.consumerImg).error(R.mipmap.bg_head_default).into(headIV);
            if ("1000".equals(loginBean.data.levelCode)) {
                //普通会员
                myList.add("消费记录");
                myList.add("安全中心");
                myList.add("联系客服");
                levelTV.setText("会员等级:普通会员");
            } else if ("1001".equals(loginBean.data.levelCode)) {
                //代理会员
                myList.add("消费记录");
                myList.add("安全中心");
                myList.add("开户");
                myList.add("团队管理");
                myList.add("团队记录");
                myList.add("联系客服");
                levelTV.setText("会员等级:代理会员");
            } else {
                myList.add("消费记录");
                myList.add("安全中心");
                myList.add("联系客服");
                levelTV.setText("会员等级:普通会员");
            }
            myAdapter.refresh(myList);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getUserInfo();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_four;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_set:
                startActivity(new Intent(mContext, SetActivity.class));
                break;
            case R.id.btn_cz:
//                startActivity(new Intent(mContext, CzActivity.class));
                startActivity(new Intent(mContext, WxCzActivity.class));
                break;
            case R.id.btn_tb:
//                TxActivity.intentToTX(mContext, yue);
                WxTxActivity.intentToTX(mContext, yue);
                break;
            case R.id.ll_updateInfo:
                UpdateInfoActivity.intentUpdateInfo(mContext, nickName, photoUrl);
                break;
        }
    }

    @Override
    public void onRefresh() {
        getUserInfo();
    }

    private void getUserInfo() {
        HttpUtil.getUserInfo(mContext, userId, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                UserInfo userInfo = new Gson().fromJson(response.get(), UserInfo.class);
                if (userInfo != null && YS.SUCCESE.equals(userInfo.code) && userInfo.data != null) {
                    nickName = StringUtil.valueOf(userInfo.data.consumerName);
                    photoUrl = userInfo.data.consumerImg;
                    yue = "" + StringUtil.StringToDouble(userInfo.data.balance);
                    nicknameTV.setText("昵称：" + StringUtil.valueOf(userInfo.data.consumerName));
                    usernameTV.setText("用户名：" + StringUtil.valueOf(userInfo.data.loginName));
                    fdTV.setText("返点：" + StringUtil.valueOf(userInfo.data.backNum));
                    yueTV.setText("" + StringUtil.StringToDoubleStr(userInfo.data.balance) + YS.UNIT);
                    canTmoneyTV.setText("" + StringUtil.StringToDoubleStr(userInfo.data.balance) + YS.UNIT);
                    Glide.with(mContext).load(StringUtil.valueOf(userInfo.data.consumerImg)).error(R.mipmap.bg_head_default).into(headIV);
//                    BaseHttp.getInstance().loadImag(mContext, userInfo.data.consumerImg, new HttpListener<Bitmap>() {
//                        @Override
//                        public void onSucceed(int what, Response<Bitmap> response) {
//                            Bitmap bitmap = response.get();
//                            if(bitmap!=null){
//                                headIV.setImageBitmap(bitmap);
//                            }else {
//                                headIV.setImageResource(R.mipmap.bg_head_default);
//                            }
//                        }
//
//                        @Override
//                        public void onFailed(int what, Response<Bitmap> response) {
//                            headIV.setImageResource(R.mipmap.bg_head_default);
//                        }
//                    });
                    jrczTV.setText(StringUtil.StringToDoubleStr(userInfo.data.todayCz) + YS.UNIT);
                    jrxfTV.setText(StringUtil.StringToDoubleStr(userInfo.data.todayXf) + YS.UNIT);
                    jrylTV.setText(StringUtil.StringToDoubleStr(userInfo.data.todayYl) + YS.UNIT);
                    levelTV.setText("会员等级:" + StringUtil.valueOf(userInfo.data.levelName));
                }
                srl.setRefreshing(false);
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                srl.setRefreshing(false);
            }
        });
    }
}
