package com.ys.game.adapter;

import android.content.Context;

import com.ys.game.bean.MsgBean;

import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename MsgAdapter2
 * @description -------------------------------------------------------
 * @date 2018/10/25 20:23
 */
public class MsgAdapter2 extends CommonAdapter<MsgBean> {
    public MsgAdapter2(Context context, List<MsgBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, MsgBean item, int position) {

    }
}
