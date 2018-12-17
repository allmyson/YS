package com.ys.game.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ys.game.R;
import com.ys.game.bean.ZhBean;
import com.ys.game.util.DateUtil;
import com.ys.game.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename ZhAdapter
 * @description -------------------------------------------------------
 * @date 2018/12/17 16:27
 */
public class ZhAdapter extends CommonAdapter<ZhBean> {
    private List<Boolean> checkList;
    private CheckListener checkListener;
    private List<Integer> bsList;

    public ZhAdapter(Context context, List<ZhBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
        checkList = new ArrayList<>();
        bsList = new ArrayList<>();
        for (ZhBean zhBean : mDatas) {
            checkList.add(true);
            bsList.add(zhBean.bs);
        }
    }


    @Override
    public void refresh(List<ZhBean> mDatas) {
        checkList = new ArrayList<>();
        bsList = new ArrayList<>();
        for (ZhBean zhBean : mDatas) {
            checkList.add(true);
            bsList.add(zhBean.bs);
        }
        super.refresh(mDatas);
    }

    @Override
    public void convert(ViewHolder helper, ZhBean item, final int position) {
        helper.setText(R.id.tv_position, (position + 1) + "");
        LinearLayout topLL = helper.getView(R.id.ll_top);
        if (position == 0) {
            topLL.setBackgroundResource(R.drawable.rect_cornor_login2);
        } else {
            topLL.setBackgroundResource(R.drawable.rect_cornor_login3);
        }
        helper.setText(R.id.tv_qs, StringUtil.valueOf(item.qs));
        helper.setText(R.id.tv_time, "截止时间:" + DateUtil.getLongDate2(item.time));
        helper.setText(R.id.et_qsbs, StringUtil.valueOf(bsList.get(position)));
        helper.setText(R.id.tv_totalPrice, "" + item.zhushu * item.price * bsList.get(position));
        ImageView iv = helper.getView(R.id.iv_check);
        if (checkList.get(position)) {
            iv.setVisibility(View.VISIBLE);
        } else {
            iv.setVisibility(View.GONE);
        }
        helper.getView(R.id.rl_check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkList.get(position)) {
                    checkList.set(position, false);
                } else {
                    checkList.set(position, true);
                }
                notifyDataSetChanged();
                if (checkListener != null) {
                    checkListener.check(position);
                }
            }
        });
        final EditText bsET = helper.getView(R.id.et_qsbs);
        bsET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                bsList.set(position, StringUtil.StringToInt(bsET.getText().toString().trim()));
                notifyDataSetChanged();
                if (checkListener != null) {
                    checkListener.check(position);
                }
            }
        });
    }

    public interface CheckListener {
        void check(int position);
    }

    public void setCheckList(List<Boolean> checkList) {
        this.checkList = checkList;
    }

    public List<ZhBean> getResult() {
        List<ZhBean> list = new ArrayList<>();
        for (int i = 0; i < checkList.size(); i++) {
            if (checkList.get(i)) {
                ZhBean zhBean = new ZhBean();
                zhBean.qs = mDatas.get(i).qs;
                zhBean.time = mDatas.get(i).time;
                zhBean.zhushu = mDatas.get(i).zhushu;
                zhBean.bs = bsList.get(i);
                zhBean.price = mDatas.get(i).price;
                list.add(zhBean);
            }
        }
        return list;
    }

    public void setCheckListener(CheckListener checkListener) {
        this.checkListener = checkListener;
    }
}
