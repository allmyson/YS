package com.ys.game.fragment;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ys.game.R;
import com.ys.game.adapter.TeamJLAdapter;
import com.ys.game.base.BaseFragment;
import com.ys.game.dialog.DialogUtil;
import com.ys.game.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename WinnerJLFragment
 * @description -------------------------------------------------------
 * @date 2018/11/23 15:08
 */
public class WinnerJLFragment extends BaseFragment implements View.OnClickListener {
    private ListView lv;
    private List<Object> list;
    private TeamJLAdapter mAdapter;
    private Spinner spinner;
    private ArrayAdapter<String> arrayAdapter;
    private String[] args = new String[]{"全部", "未开奖", "未中奖", "已中奖"};
    private TextView startTV, endTV;
    private long startTime;
    private long endTime;
    @Override
    protected void init() {
        lv = getView(R.id.lv_tzjl);
        list = new ArrayList<>();
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        mAdapter = new TeamJLAdapter(mContext, list, R.layout.item_tzjl);
        lv.setAdapter(mAdapter);
        spinner = getView(R.id.spinner);
        arrayAdapter = new ArrayAdapter<String>(mContext, R.layout.item_text, args);
        spinner.setAdapter(arrayAdapter);
        startTV = getView(R.id.tv_start);
        endTV = getView(R.id.tv_end);
        startTV.setOnClickListener(this);
        endTV.setOnClickListener(this);
        startTV.setText(DateUtil.longToYMD(System.currentTimeMillis()));
        endTV.setText(DateUtil.longToYMD(System.currentTimeMillis()));
    }

    @Override
    protected void getData() {

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
                    }
                });
                break;
            case R.id.tv_end:
                DialogUtil.showDateDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        endTV.setText(getTimeStr(year, month, dayOfMonth));
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
}
