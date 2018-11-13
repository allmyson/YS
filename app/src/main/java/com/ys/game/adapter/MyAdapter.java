package com.ys.game.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;

import com.ys.game.R;
import com.ys.game.util.StringUtil;

import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename MyAdapter
 * @description -------------------------------------------------------
 * @date 2018/10/26 10:43
 */
public class MyAdapter extends CommonAdapter<String> {

    public MyAdapter(Context context, List<String> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, String item, int position) {
        helper.setText(R.id.tv_name, StringUtil.valueOf(item));
        ImageView iv = helper.getView(R.id.iv_);
        iv.setColorFilter(Color.parseColor("#999999"));
    }
}
