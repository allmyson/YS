package com.ys.game.adapter;

import android.content.Context;
import android.graphics.Color;

import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename MySNAdapter
 * @description -------------------------------------------------------
 * @date 2018/11/26 15:34
 */
public class MySNAdapter extends CommonAdapter<String> {
    public MySNAdapter(Context context, List<String> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, String item, int position) {

        int raw = (position + 1) / 5;
        int yushu = (position + 1) % 5;
        if (yushu != 0) {
            raw += 1;
        }
        if (raw % 2 == 0) {
            helper.getConvertView().setBackgroundColor(Color.WHITE);
        } else {
            helper.getConvertView().setBackgroundColor(Color.parseColor("#faf6f5"));
        }
    }
}
