package com.ys.game.fragment;

import android.app.DatePickerDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.rest.Response;
import com.ys.game.R;
import com.ys.game.adapter.WinnerTzjlAdapter;
import com.ys.game.base.BaseFragment;
import com.ys.game.bean.WinnerTZJL;
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
 * @filename WinnerJLFragment
 * @description -------------------------------------------------------
 * @date 2018/11/23 15:08
 */
public class WinnerJLFragment extends BaseFragment implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener {
    private ListView lv;
    private List<WinnerTZJL.DataBeanX.DataBean> list;
    private WinnerTzjlAdapter mAdapter;
    private Spinner spinner;
    private ArrayAdapter<String> arrayAdapter;
    private String[] args = new String[]{"全部", "未开奖", "未中奖", "已中奖"};
    private TextView startTV, endTV;
    private String zjType = "全部";//中奖状态  1000已中奖 1001未中奖  null/"" 未开奖  all 全部
    private long startTime;
    private long endTime;
    private String userId;
    private List<WinnerTZJL.DataBeanX.DataBean> allList;
    private SwipeRefreshLayout srl;
    @Override
    protected void init() {
        lv = getView(R.id.lv_tzjl);
        allList = new ArrayList<>();
        list = new ArrayList<>();
        mAdapter = new WinnerTzjlAdapter(mContext, list, R.layout.item_tzjl);
        lv.setAdapter(mAdapter);
        spinner = getView(R.id.spinner);
        arrayAdapter = new ArrayAdapter<String>(mContext, R.layout.item_text, args);
        spinner.setAdapter(arrayAdapter);
        startTime = DateUtil.getCurrentDayStart();
        endTime = DateUtil.getCurrentDayEnd();
        startTV = getView(R.id.tv_start);
        endTV = getView(R.id.tv_end);
        startTV.setOnClickListener(this);
        endTV.setOnClickListener(this);
        startTV.setText(DateUtil.longToYMD(System.currentTimeMillis()));
        endTV.setText(DateUtil.longToYMD(System.currentTimeMillis()));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                zjType = args[position];
                selectData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        srl = (SwipeRefreshLayout) mView.findViewById(R.id.srl);
        srl.setOnRefreshListener(this);
        srl.setColorSchemeColors(getResources().getColor(R.color.main_color));

        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                View firstView = view.getChildAt(firstVisibleItem);
                if (firstVisibleItem == 0 && (firstView == null || firstView.getTop() == 0)) {
                    /*上滑到listView的顶部时，下拉刷新组件可见*/
                    srl.setEnabled(true);
                } else {
                    /*不是listView的顶部时，下拉刷新组件不可见*/
                    srl.setEnabled(false);
                }
            }
        });
    }

    @Override
    protected void getData() {
        userId = UserSP.getUserId(mContext);
        getWinnderTZJL();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_winner_jl;
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

    private void getWinnderTZJL() {
        HttpUtil.getWinnerTZJL(mContext, userId, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                allList.clear();
                WinnerTZJL winnerTZJL = new Gson().fromJson(response.get(), WinnerTZJL.class);
                if (winnerTZJL != null && YS.SUCCESE.equals(winnerTZJL.code) && winnerTZJL.data != null && winnerTZJL
                        .data.data != null && winnerTZJL.data.data.size() > 0) {
                    allList.addAll(winnerTZJL.data.data);
                }
                selectData();
                srl.setRefreshing(false);
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                srl.setRefreshing(false);
            }
        });
    }


    private void selectData() {
        list.clear();
        if (allList.size() > 0) {
            for (int i = 0; i < allList.size(); i++) {
                if (DateUtil.changeTimeToLong(allList.get(i).bets_time) <= endTime && DateUtil
                        .changeTimeToLong(allList.get(i).bets_time) >= startTime) {
                    if ("全部".equals(zjType)) {
                        list.add(allList.get(i));
                    } else if ("未开奖".equals(zjType)) {
                        if ("1002".equals(String.valueOf(allList.get(i).is_win_code))) {
                            list.add(allList.get(i));
                        }
                    } else if ("未中奖".equals(zjType)) {
                        if ("1001".equals(String.valueOf(allList.get(i).is_win_code))) {
                            list.add(allList.get(i));
                        }
                    } else if ("已中奖".equals(zjType)) {
                        if ("1000".equals(String.valueOf(allList.get(i).is_win_code))) {
                            list.add(allList.get(i));
                        }
                    }
                }
            }
        }
        mAdapter.refresh(list);
    }

    @Override
    public void onRefresh() {
        getWinnderTZJL();
    }
}
