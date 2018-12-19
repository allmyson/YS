package com.ys.game.adapter;

import android.content.Context;

import com.ys.game.R;
import com.ys.game.bean.TeamXfjlBean;
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
public class TeamXFJLAdapter extends CommonAdapter<TeamXfjlBean.DataBeanX.DataBean> {
    public TeamXFJLAdapter(Context context, List<TeamXfjlBean.DataBeanX.DataBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, TeamXfjlBean.DataBeanX.DataBean item, int position) {
        helper.setText(R.id.tv_name, StringUtil.valueOf(item.consumer_name));
        helper.setText(R.id.tv_gameType, StringUtil.valueOf(item.game_name));
        helper.setText(R.id.tv_qs, StringUtil.valueOf(item.periods_num).substring(4));
        helper.setText(R.id.tv_money, StringUtil.valueOf(item.bets_money) + YS.UNIT);
    }
}
