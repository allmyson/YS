package com.ys.game.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.ys.game.R;
import com.ys.game.sf.Dhzs_5xBean;
import com.ys.game.util.StringUtil;

import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename ZsAdapter
 * @description -------------------------------------------------------
 * @date 2018/11/1 17:33
 */
public class ZsAdapter extends CommonAdapter<List<Dhzs_5xBean>> {
    public ZsAdapter(Context context, List<List<Dhzs_5xBean>> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, List<Dhzs_5xBean> item, int position) {
        if (position % 2 == 0) {
            helper.getConvertView().setBackgroundColor(Color.WHITE);
        } else {
            helper.getConvertView().setBackgroundColor(Color.parseColor("#faf6f5"));
        }
        String typeName = StringUtil.valueOf(item.get(0).name);
        helper.setText(R.id.tv_name, typeName);
        if ("出现次数".equals(typeName)) {
            helper.getConvertView().setBackgroundColor(Color.parseColor("#d6fafc"));
        } else if ("最大连出".equals(typeName)) {
            helper.getConvertView().setBackgroundColor(Color.parseColor("#fff5ec"));
        } else if ("最大遗漏".equals(typeName)) {
            helper.getConvertView().setBackgroundColor(Color.parseColor("#f4fde5"));
        } else if ("平均遗漏".equals(typeName)) {
            helper.getConvertView().setBackgroundColor(Color.parseColor("#f3f5ff"));
        }
        helper.setText(R.id.tv0, StringUtil.valueOf(item.get(0).value));
        helper.setText(R.id.tv1, StringUtil.valueOf(item.get(1).value));
        helper.setText(R.id.tv2, StringUtil.valueOf(item.get(2).value));
        helper.setText(R.id.tv3, StringUtil.valueOf(item.get(3).value));
        helper.setText(R.id.tv4, StringUtil.valueOf(item.get(4).value));
        helper.setText(R.id.tv5, StringUtil.valueOf(item.get(5).value));
        helper.setText(R.id.tv6, StringUtil.valueOf(item.get(6).value));
        helper.setText(R.id.tv7, StringUtil.valueOf(item.get(7).value));
        helper.setText(R.id.tv8, StringUtil.valueOf(item.get(8).value));
        helper.setText(R.id.tv9, StringUtil.valueOf(item.get(9).value));
        if (item.get(0).count > 0) {
            helper.getView(R.id.tv0).setBackgroundResource(R.drawable.circle_cornor_main);
            ((TextView) helper.getView(R.id.tv0)).setTextColor(Color.WHITE);
        } else {
            helper.getView(R.id.tv0).setBackgroundColor(Color.TRANSPARENT);
            ((TextView) helper.getView(R.id.tv0)).setTextColor(Color.parseColor("#333333"));
        }
        if (item.get(1).count > 0) {
            helper.getView(R.id.tv1).setBackgroundResource(R.drawable.circle_cornor_main);
            ((TextView) helper.getView(R.id.tv1)).setTextColor(Color.WHITE);
        } else {
            helper.getView(R.id.tv1).setBackgroundColor(Color.TRANSPARENT);
            ((TextView) helper.getView(R.id.tv1)).setTextColor(Color.parseColor("#333333"));
        }
        if (item.get(2).count > 0) {
            helper.getView(R.id.tv2).setBackgroundResource(R.drawable.circle_cornor_main);
            ((TextView) helper.getView(R.id.tv2)).setTextColor(Color.WHITE);
        } else {
            helper.getView(R.id.tv2).setBackgroundColor(Color.TRANSPARENT);
            ((TextView) helper.getView(R.id.tv2)).setTextColor(Color.parseColor("#333333"));
        }
        if (item.get(3).count > 0) {
            helper.getView(R.id.tv3).setBackgroundResource(R.drawable.circle_cornor_main);
            ((TextView) helper.getView(R.id.tv3)).setTextColor(Color.WHITE);
        } else {
            helper.getView(R.id.tv3).setBackgroundColor(Color.TRANSPARENT);
            ((TextView) helper.getView(R.id.tv3)).setTextColor(Color.parseColor("#333333"));
        }
        if (item.get(4).count > 0) {
            helper.getView(R.id.tv4).setBackgroundResource(R.drawable.circle_cornor_main);
            ((TextView) helper.getView(R.id.tv4)).setTextColor(Color.WHITE);
        } else {
            helper.getView(R.id.tv4).setBackgroundColor(Color.TRANSPARENT);
            ((TextView) helper.getView(R.id.tv4)).setTextColor(Color.parseColor("#333333"));
        }
        if (item.get(5).count > 0) {
            helper.getView(R.id.tv5).setBackgroundResource(R.drawable.circle_cornor_main);
            ((TextView) helper.getView(R.id.tv5)).setTextColor(Color.WHITE);
        } else {
            helper.getView(R.id.tv5).setBackgroundColor(Color.TRANSPARENT);
            ((TextView) helper.getView(R.id.tv5)).setTextColor(Color.parseColor("#333333"));
        }
        if (item.get(6).count > 0) {
            helper.getView(R.id.tv6).setBackgroundResource(R.drawable.circle_cornor_main);
            ((TextView) helper.getView(R.id.tv6)).setTextColor(Color.WHITE);
        } else {
            helper.getView(R.id.tv6).setBackgroundColor(Color.TRANSPARENT);
            ((TextView) helper.getView(R.id.tv6)).setTextColor(Color.parseColor("#333333"));
        }
        if (item.get(7).count > 0) {
            helper.getView(R.id.tv7).setBackgroundResource(R.drawable.circle_cornor_main);
            ((TextView) helper.getView(R.id.tv7)).setTextColor(Color.WHITE);
        } else {
            helper.getView(R.id.tv7).setBackgroundColor(Color.TRANSPARENT);
            ((TextView) helper.getView(R.id.tv7)).setTextColor(Color.parseColor("#333333"));
        }
        if (item.get(8).count > 0) {
            helper.getView(R.id.tv8).setBackgroundResource(R.drawable.circle_cornor_main);
            ((TextView) helper.getView(R.id.tv8)).setTextColor(Color.WHITE);
        } else {
            helper.getView(R.id.tv8).setBackgroundColor(Color.TRANSPARENT);
            ((TextView) helper.getView(R.id.tv8)).setTextColor(Color.parseColor("#333333"));
        }
        if (item.get(9).count > 0) {
            helper.getView(R.id.tv9).setBackgroundResource(R.drawable.circle_cornor_main);
            ((TextView) helper.getView(R.id.tv9)).setTextColor(Color.WHITE);
        } else {
            helper.getView(R.id.tv9).setBackgroundColor(Color.TRANSPARENT);
            ((TextView) helper.getView(R.id.tv9)).setTextColor(Color.parseColor("#333333"));
        }

        helper.setText(R.id.tv00, StringUtil.valueOf(item.get(0).count));
        helper.setText(R.id.tv11, StringUtil.valueOf(item.get(1).count));
        helper.setText(R.id.tv22, StringUtil.valueOf(item.get(2).count));
        helper.setText(R.id.tv33, StringUtil.valueOf(item.get(3).count));
        helper.setText(R.id.tv44, StringUtil.valueOf(item.get(4).count));
        helper.setText(R.id.tv55, StringUtil.valueOf(item.get(5).count));
        helper.setText(R.id.tv66, StringUtil.valueOf(item.get(6).count));
        helper.setText(R.id.tv77, StringUtil.valueOf(item.get(7).count));
        helper.setText(R.id.tv88, StringUtil.valueOf(item.get(8).count));
        helper.setText(R.id.tv99, StringUtil.valueOf(item.get(9).count));
        if (item.get(0).count > 1) {
            helper.getView(R.id.tv00).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tv00).setVisibility(View.GONE);
        }
        if (item.get(1).count > 1) {
            helper.getView(R.id.tv11).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tv11).setVisibility(View.GONE);
        }
        if (item.get(2).count > 1) {
            helper.getView(R.id.tv22).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tv22).setVisibility(View.GONE);
        }
        if (item.get(3).count > 1) {
            helper.getView(R.id.tv33).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tv33).setVisibility(View.GONE);
        }
        if (item.get(4).count > 1) {
            helper.getView(R.id.tv44).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tv44).setVisibility(View.GONE);
        }
        if (item.get(5).count > 1) {
            helper.getView(R.id.tv55).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tv55).setVisibility(View.GONE);
        }
        if (item.get(6).count > 1) {
            helper.getView(R.id.tv66).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tv66).setVisibility(View.GONE);
        }
        if (item.get(7).count > 1) {
            helper.getView(R.id.tv77).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tv77).setVisibility(View.GONE);
        }
        if (item.get(8).count > 1) {
            helper.getView(R.id.tv88).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tv88).setVisibility(View.GONE);
        }
        if (item.get(9).count > 1) {
            helper.getView(R.id.tv99).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tv99).setVisibility(View.GONE);
        }
    }
}
