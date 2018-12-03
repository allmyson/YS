package com.ys.game.adapter;

import android.content.Context;

import com.ys.game.R;
import com.ys.game.bean.MsgBean;
import com.ys.game.util.DateUtil;
import com.ys.game.util.StringUtil;

import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename MsgAdapter2
 * @description -------------------------------------------------------
 * @date 2018/10/25 20:23
 */
public class MsgAdapter2 extends CommonAdapter<MsgBean.DataBeanX.DataBean> {
    public MsgAdapter2(Context context, List<MsgBean.DataBeanX.DataBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, MsgBean.DataBeanX.DataBean item, int position) {
        helper.setText(R.id.tv_title, "【弈尚游戏】\t\t\t\t" + StringUtil.valueOf(item.news_type_name)+"\n"+StringUtil.valueOf(item.news_content));
        helper.setText(R.id.tv_time, DateUtil.changeTimeToYMD(item.send_time));
    }
}
