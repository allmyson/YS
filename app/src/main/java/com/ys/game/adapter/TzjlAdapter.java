package com.ys.game.adapter;

import android.content.Context;

import com.ys.game.R;
import com.ys.game.bean.TzjlBean;
import com.ys.game.util.StringUtil;

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
        helper.setText(R.id.tv_qs, StringUtil.valueOf(item.periods_num));
        helper.setText(R.id.tv_money, "" + StringUtil.StringToDouble(item.bets_money));
        if ("1000".equals(item.is_win_code)) {
            helper.setText(R.id.tv_isZJ, "已中奖");
        } else if ("1001".equals(item.is_win_code)) {
            helper.setText(R.id.tv_isZJ, "未中奖");
        } else if ("1002".equals(item.is_win_code)) {
            helper.setText(R.id.tv_isZJ, "未开奖");
        }

    }
}
