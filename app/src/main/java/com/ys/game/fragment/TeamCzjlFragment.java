package com.ys.game.fragment;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.rest.Response;
import com.ys.game.R;
import com.ys.game.adapter.TeamCZJLAdapter;
import com.ys.game.base.BaseFragment;
import com.ys.game.bean.TeamCzjlBean;
import com.ys.game.dialog.DialogUtil;
import com.ys.game.http.HttpListener;
import com.ys.game.sp.UserSP;
import com.ys.game.util.DateUtil;
import com.ys.game.util.HttpUtil;
import com.ys.game.util.YS;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename TeamCzjlFragment
 * @description -------------------------------------------------------
 * @date 2018/11/30 14:10
 */
public class TeamCzjlFragment extends BaseFragment implements View.OnClickListener {
    private ListView lv;
    private List<TeamCzjlBean.DataBeanX.DataBean> list;
    private List<TeamCzjlBean.DataBeanX.DataBean> allList;
    private TeamCZJLAdapter mAdapter;
    private Spinner spinner;
    private ArrayAdapter<String> arrayAdapter;
    private String[] args = new String[]{"全部", "未开奖", "未中奖", "已中奖"};
    private TextView startTV, endTV;
    private String userId;
    private long startTime;
    private long endTime;

    @Override
    protected void init() {
        lv = getView(R.id.lv_tzjl);
        list = new ArrayList<>();
        allList = new ArrayList<>();
        mAdapter = new TeamCZJLAdapter(mContext, list, R.layout.item_team_czjl);
        lv.setAdapter(mAdapter);
        spinner = getView(R.id.spinner);
        arrayAdapter = new ArrayAdapter<String>(mContext, R.layout.item_text, args);
        spinner.setAdapter(arrayAdapter);
        startTV = getView(R.id.tv_start);
        endTV = getView(R.id.tv_end);
        startTV.setOnClickListener(this);
        endTV.setOnClickListener(this);
        startTime = DateUtil.getCurrentDayStart();
        endTime = DateUtil.getCurrentDayEnd();
        startTV.setText(DateUtil.longToYMD(System.currentTimeMillis()));
        endTV.setText(DateUtil.longToYMD(System.currentTimeMillis()));
    }

    @Override
    protected void getData() {
        userId = UserSP.getUserId(mContext);
        selectDataFromWeb();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_team_czjl;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_start:
                DialogUtil.showDateDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        startTV.setText(getTimeStr(year, month, dayOfMonth));
                        startTime = DateUtil.getWhichDayStart(startTV.getText().toString());
                        selectData();
                    }
                });
                break;
            case R.id.tv_end:
                DialogUtil.showDateDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        endTV.setText(getTimeStr(year, month, dayOfMonth));
                        endTime = DateUtil.getWhichDayEnd(endTV.getText().toString());
                        selectData();
                    }
                });
                break;
        }
    }

    private String getTimeStr(int year, int month, int dayOfMonth) {
        return year + "-" + (month + 1 > 9 ? month + 1 : ("0" + (month + 1))) + "-" +
                (dayOfMonth > 9 ?
                        dayOfMonth : ("0" + dayOfMonth));
    }


    private void selectDataFromWeb() {
        HttpUtil.getTeamJL(mContext, userId, "1000", new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                allList.clear();
                TeamCzjlBean teamCzjlBean = new Gson().fromJson(response.get(), TeamCzjlBean.class);
                if (teamCzjlBean != null && YS.SUCCESE.equals(teamCzjlBean.code) && teamCzjlBean.data != null &&
                        teamCzjlBean.data.data != null && teamCzjlBean.data.data.size() > 0) {
                    allList.addAll(teamCzjlBean.data.data);
                }
                selectData();
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }

    private void selectData() {
        list.clear();
        if (allList.size() > 0) {
            for (int i = 0; i < allList.size(); i++) {
                    if (DateUtil.changeTimeToLong(allList.get(i).apply_time) <= endTime && DateUtil
                            .changeTimeToLong(allList.get(i).apply_time) >= startTime) {
                        list.add(allList.get(i));
                }
            }
        }
        mAdapter.refresh(list);
    }
}
