package com.ys.game.adapter;

import android.content.Context;

import com.ys.game.R;
import com.ys.game.bean.TeamCzjlBean;
import com.ys.game.util.DateUtil;
import com.ys.game.util.StringUtil;
import com.ys.game.util.YS;

import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename TeamCZJLAdapter
 * @description -------------------------------------------------------
 * @date 2018/12/5 15:11
 */
public class TeamCZJLAdapter extends CommonAdapter<TeamCzjlBean.DataBeanX.DataBean> {
    public TeamCZJLAdapter(Context context, List<TeamCzjlBean.DataBeanX.DataBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, TeamCzjlBean.DataBeanX.DataBean item, int position) {
        helper.setText(R.id.tv_name, StringUtil.valueOf(item.consumer_name));
        helper.setText(R.id.tv_time, DateUtil.changeTimeToYMD(item.apply_time));
        helper.setText(R.id.tv_moneyType, StringUtil.valueOf(item.apply_type_name));
        helper.setText(R.id.tv_money, StringUtil.StringToDoubleStr(item.apply_money) + YS.UNIT);
    }
}
