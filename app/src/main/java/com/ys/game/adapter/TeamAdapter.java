package com.ys.game.adapter;

import android.content.Context;

import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename TeamAdapter
 * @description -------------------------------------------------------
 * @date 2018/11/29 18:20
 */
public class TeamAdapter extends CommonAdapter<Object> {
    public TeamAdapter(Context context, List<Object> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, Object item, int position) {

    }
}
