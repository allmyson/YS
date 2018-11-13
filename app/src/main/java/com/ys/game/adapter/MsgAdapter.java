package com.ys.game.adapter;

import android.content.Context;

import com.ys.game.bean.MsgBean;

import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename MsgAdapter
 * @description -------------------------------------------------------
 * @date 2018/10/23 17:47
 */
public class MsgAdapter extends CommonAdapter<MsgBean> {
    public MsgAdapter(Context context, List<MsgBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, MsgBean item, int position) {

    }
}
