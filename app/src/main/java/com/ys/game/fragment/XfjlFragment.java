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
import com.ys.game.adapter.TzjlAdapter;
import com.ys.game.base.BaseFragment;
import com.ys.game.bean.TzjlBean;
import com.ys.game.dialog.DialogUtil;
import com.ys.game.http.HttpListener;
import com.ys.game.sp.UserSP;
import com.ys.game.util.DateUtil;
import com.ys.game.util.HttpUtil;
import com.ys.game.util.YS;

import java.util.ArrayList;
import java.util.List;

public class XfjlFragment extends BaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private ListView lv;
    private List<TzjlBean.DataBeanX.DataBean> allList;
    private List<TzjlBean.DataBeanX.DataBean> list;
    private TzjlAdapter mAdapter;
    private Spinner spinner;
    private ArrayAdapter<String> arrayAdapter;
    private String[] args = new String[]{"全部", "未开奖", "未中奖", "已中奖"};
    private TextView startTV, endTV;
    public static final int TYPE_SDXZ = 1000;//手动下注
    public static final int TYPE_ZH = 1001;//追号下注
    private int currentType = TYPE_SDXZ;
    private String userId;
    private long startTime;
    private long endTime;
    private String zjType = "全部";//中奖状态  1000已中奖 1001未中奖  null/"" 未开奖  all 全部
    private SwipeRefreshLayout srl;

    public static XfjlFragment newInstance() {
        XfjlFragment fragment = new XfjlFragment();
        return fragment;
    }


    @Override
    protected void init() {
        lv = getView(R.id.lv_tzjl);
        allList = new ArrayList<>();
        list = new ArrayList<>();
        mAdapter = new TzjlAdapter(mContext, list, R.layout.item_tzjl);
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
        getTZJL();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_tzjl;
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
                        endTime = DateUtil.getWhichDayStart(endTV.getText().toString());
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

    private void getTZJL() {
        HttpUtil.getTZJL(mContext, userId, 1, 20, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                allList.clear();
                TzjlBean bean = new Gson().fromJson(response.get(), TzjlBean.class);
                if (bean != null && YS.SUCCESE.equals(bean.code) && bean.data != null && bean.data.data != null &&
                        bean.data.data.size() > 0) {
                   allList.addAll(bean.data.data);
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
//                if (currentType == StringUtil.StringToInt(allList.get(i).complant_type_code)) {
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
//                }
            }
        }
        mAdapter.refresh(list);
    }

    @Override
    public void onRefresh() {
        getTZJL();
    }
}
