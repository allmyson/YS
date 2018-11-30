package com.ys.game.activity;

import android.widget.ListView;

import com.ys.game.R;
import com.ys.game.adapter.TeamAdapter;
import com.ys.game.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

//团队管理
public class TeamGLActivity extends BaseActivity {
    private ListView teamLV;
    private TeamAdapter teamAdapter;
    private List<Object> teamList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_team_gl;
    }

    @Override
    public void initView() {
        backRL = getView(R.id.rl_back);
        backRL.setOnClickListener(this);
        teamLV = getView(R.id.lv_team);
        teamList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            teamList.add(null);
        }
        teamAdapter = new TeamAdapter(mContext, teamList, R.layout.item_team);
        teamLV.setAdapter(teamAdapter);
    }

    @Override
    public void getData() {

    }
}
