package com.ys.game.adapter;

import android.content.Context;
import android.graphics.Color;

import com.ys.game.R;
import com.ys.game.bean.WinnerTZJL;
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
public class WinnerTzjlAdapter extends CommonAdapter<WinnerTZJL.DataBeanX.DataBean> {
    public WinnerTzjlAdapter(Context context, List<WinnerTZJL.DataBeanX.DataBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, WinnerTZJL.DataBeanX.DataBean item, int position) {
        if ("1000".equals(item.game_code)) {
            helper.setImageResource(R.id.iv_, R.mipmap.ic_cqssc);
        } else if ("1001".equals(item.game_code)) {
            helper.setImageResource(R.id.iv_, R.mipmap.ic_txffc);
        } else if ("1002".equals(item.game_code)) {
            helper.setImageResource(R.id.iv_, R.mipmap.ic_slz);
        }
        helper.setText(R.id.tv_name, StringUtil.valueOf(item.game_name));
        helper.setText(R.id.tv_qs, StringUtil.valueOf(item.periods_num).substring(4));
        helper.setText(R.id.tv_money, "" + StringUtil.StringToDoubleTwo(item.bets_money)+ YS.UNIT);
        if ("1000".equals(item.is_win_code)) {
//            helper.setText(R.id.tv_isZJ, "已中奖");
            helper.setText(R.id.tv_isZJ, StringUtil.StringToDoubleTwo(item.win_money) + YS.UNIT);
            helper.setTextColor(R.id.tv_isZJ, Color.parseColor("#fc6a44"));
        } else if ("1001".equals(item.is_win_code)) {
//            helper.setText(R.id.tv_isZJ, "未中奖");
            helper.setText(R.id.tv_isZJ, StringUtil.valueOf(item.is_win_name));
            helper.setTextColor(R.id.tv_isZJ, Color.parseColor("#fc6a44"));
        } else if ("1002".equals(item.is_win_code)) {
//            helper.setText(R.id.tv_isZJ, "未开奖");
            helper.setText(R.id.tv_isZJ, StringUtil.valueOf(item.is_win_name));
            helper.setTextColor(R.id.tv_isZJ, Color.GREEN);
        }

    }
}
