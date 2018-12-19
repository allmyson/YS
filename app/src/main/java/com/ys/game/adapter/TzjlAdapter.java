package com.ys.game.adapter;

import android.content.Context;
import android.graphics.Color;

import com.ys.game.R;
import com.ys.game.bean.TzjlBean;
import com.ys.game.util.StringUtil;
import com.ys.game.util.YS;

import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename TzjlAdapter
 * @description -------------------------------------------------------
 * @date 2018/11/7 19:30
 */
public class TzjlAdapter extends CommonAdapter<TzjlBean.DataBeanX.DataBean> {
    public TzjlAdapter(Context context, List<TzjlBean.DataBeanX.DataBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, TzjlBean.DataBeanX.DataBean item, int position) {
        if ("1000".equals(item.game_code)) {
            helper.setImageResource(R.id.iv_, R.mipmap.ic_cqssc);
        } else if ("1001".equals(item.game_code)) {
            helper.setImageResource(R.id.iv_, R.mipmap.ic_txffc);
        } else if ("1002".equals(item.game_code)) {
            helper.setImageResource(R.id.iv_, R.mipmap.ic_slz);
        }
        helper.setText(R.id.tv_name, StringUtil.valueOf(item.game_name));
        helper.setText(R.id.tv_qs, StringUtil.valueOf(item.periods_num).substring(4));
        helper.setText(R.id.tv_money, "" + StringUtil.StringToDouble(item.bets_money)+YS.UNIT);
        if ("1000".equals(item.is_win_code)) {
//            helper.setText(R.id.tv_isZJ, "已中奖");
            helper.setText(R.id.tv_isZJ, StringUtil.valueOf(item.win_money) + YS.UNIT);
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
