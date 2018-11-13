package com.ys.game.adapter;

import android.content.Context;
import android.graphics.Color;

import com.ys.game.R;
import com.ys.game.sf.Wxhz_dxdsBean;
import com.ys.game.util.StringUtil;

import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename Wxhz_DxdsAdapter
 * @description -------------------------------------------------------
 * @date 2018/11/5 16:52
 */
public class Wxhz_DxdsAdapter extends CommonAdapter<Wxhz_dxdsBean> {
    public Wxhz_DxdsAdapter(Context context, List<Wxhz_dxdsBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, Wxhz_dxdsBean item, int position) {
        if (position % 2 == 0) {
            helper.getConvertView().setBackgroundColor(Color.WHITE);
        } else {
            helper.getConvertView().setBackgroundColor(Color.parseColor("#faf6f5"));
        }
        helper.setText(R.id.tv_name, StringUtil.valueOf(item.name));
        helper.setText(R.id.tv_wan,StringUtil.valueOf(item.wan));
        helper.setText(R.id.tv_qian,StringUtil.valueOf(item.qian));
        helper.setText(R.id.tv_bai,StringUtil.valueOf(item.bai));
        helper.setText(R.id.tv_shi,StringUtil.valueOf(item.shi));
        helper.setText(R.id.tv_ge,StringUtil.valueOf(item.ge));
        helper.setText(R.id.tv_sum,StringUtil.valueOf(item.sum));
        helper.setText(R.id.tv_big,StringUtil.valueOf(item.big));
        helper.setText(R.id.tv_small,StringUtil.valueOf(item.small));
        helper.setText(R.id.tv_single,StringUtil.valueOf(item.single));
        helper.setText(R.id.tv_two,StringUtil.valueOf(item.two));
        helper.getView(R.id.tv_sum).setBackgroundColor(Color.parseColor("#ffc2ac"));
        if("大".equals(StringUtil.valueOf(item.big))){
            helper.getView(R.id.tv_big).setBackgroundColor(Color.parseColor("#6cb1ff"));
        }else {
            helper.getView(R.id.tv_big).setBackgroundColor(Color.TRANSPARENT);
        }

        if("小".equals(StringUtil.valueOf(item.small))){
            helper.getView(R.id.tv_small).setBackgroundColor(Color.parseColor("#6df3ab"));
        }else {
            helper.getView(R.id.tv_small).setBackgroundColor(Color.TRANSPARENT);
        }

        if("单".equals(StringUtil.valueOf(item.single))){
            helper.getView(R.id.tv_single).setBackgroundColor(Color.parseColor("#f37e9c"));
        }else {
            helper.getView(R.id.tv_single).setBackgroundColor(Color.TRANSPARENT);
        }


        if("双".equals(StringUtil.valueOf(item.two))){
            helper.getView(R.id.tv_two).setBackgroundColor(Color.parseColor("#fdee89"));
        }else {
            helper.getView(R.id.tv_two).setBackgroundColor(Color.TRANSPARENT);
        }
    }
}
