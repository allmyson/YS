package com.ys.game.activity;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yanzhenjie.nohttp.rest.Response;
import com.ys.game.R;
import com.ys.game.adapter.TeamAdapter;
import com.ys.game.base.BaseActivity;
import com.ys.game.bean.TeamBean;
import com.ys.game.http.HttpListener;
import com.ys.game.sp.UserSP;
import com.ys.game.util.DateUtil;
import com.ys.game.util.HttpUtil;
import com.ys.game.util.StringUtil;
import com.ys.game.util.YS;

import java.util.ArrayList;
import java.util.List;

//团队管理
public class TeamGLActivity extends BaseActivity {
    private ListView teamLV;
    private TeamAdapter teamAdapter;
    private List<TeamBean.DataBean.ListMemberBean> teamList;
    private String userId;
    private TextView nickNameTV, loginNameTV, timeTV, numTV;
    private ImageView headIV;

    @Override
    public int getLayoutId() {
        return R.layout.activity_team_gl;
    }

    @Override
    public void initView() {
        numTV = getView(R.id.tv_num);
        headIV = getView(R.id.iv_head);
        nickNameTV = getView(R.id.tv_nickName);
        loginNameTV = getView(R.id.tv_loginName);
        timeTV = getView(R.id.tv_time);
        backRL = getView(R.id.rl_back);
        backRL.setOnClickListener(this);
        teamLV = getView(R.id.lv_team);
        teamList = new ArrayList<>();
        teamAdapter = new TeamAdapter(mContext, teamList, R.layout.item_team);
        teamLV.setAdapter(teamAdapter);
    }

    @Override
    public void getData() {
        userId = UserSP.getUserId(mContext);
        HttpUtil.getTeamData(mContext, userId, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                teamList.clear();
                TeamBean teamBean = new Gson().fromJson(response.get(), TeamBean.class);
                if (teamBean != null && YS.SUCCESE.equals(teamBean.code) && teamBean.data != null) {
                    if (teamBean.data.manager != null) {
                        nickNameTV.setText("昵称\t" + StringUtil.valueOf(teamBean.data.manager.consumerName));
                        loginNameTV.setText("用户名\t" + StringUtil.valueOf(teamBean.data.manager.loginName));
                        timeTV.setText(DateUtil.changeTimeToYMDHMS(teamBean.data.manager.onlineTime));
                        Glide.with(mContext).load(teamBean.data.manager.consumerImg).error(R.mipmap.bg_default_head2)
                                .into(headIV);
                    }
                    if (teamBean.data.listMember != null) {
                        teamList.addAll(teamBean.data.listMember);
                    }
                }
                numTV.setText("团队成员(" + teamList.size() + "人)");
                teamAdapter.refresh(teamList);
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }
}
