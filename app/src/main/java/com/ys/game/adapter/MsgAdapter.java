package com.ys.game.adapter;

import android.content.Context;

import com.ys.game.R;
import com.ys.game.bean.MsgBean;
import com.ys.game.util.StringUtil;

import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename MsgAdapter
 * @description -------------------------------------------------------
 * @date 2018/10/23 17:47
 */
public class MsgAdapter extends CommonAdapter<MsgBean.DataBeanX.DataBean> {
    public MsgAdapter(Context context, List<MsgBean.DataBeanX.DataBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, MsgBean.DataBeanX.DataBean item, int position) {
        helper.setText(R.id.tv_name,"【弈尚游戏】\t\t\t\t"+ StringUtil.valueOf(item.news_type_name));
    }
}
