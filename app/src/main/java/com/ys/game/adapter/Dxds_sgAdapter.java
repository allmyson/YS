package com.ys.game.adapter;

import android.content.Context;
import android.graphics.Color;

import com.ys.game.R;
import com.ys.game.sf.Dxds_sgBean;
import com.ys.game.util.StringUtil;

import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename Dxds_sgAdapter
 * @description -------------------------------------------------------
 * @date 2018/11/6 17:24
 */
public class Dxds_sgAdapter extends CommonAdapter<Dxds_sgBean> {
    public Dxds_sgAdapter(Context context, List<Dxds_sgBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, Dxds_sgBean item, int position) {
        if (position % 2 == 0) {
            helper.getConvertView().setBackgroundColor(Color.WHITE);
        } else {
            helper.getConvertView().setBackgroundColor(Color.parseColor("#faf6f5"));
        }
        helper.setText(R.id.tv_name, StringUtil.valueOf(item.name));
        helper.setText(R.id.tv_wan,StringUtil.valueOf(item.shi));
        helper.setText(R.id.tv_qian,StringUtil.valueOf(item.ge));
        helper.setText(R.id.tv_bai,StringUtil.valueOf(item.shiBig));
        helper.setText(R.id.tv_shi,StringUtil.valueOf(item.shiSmall));
        helper.setText(R.id.tv_ge,StringUtil.valueOf(item.shiSingle));
        helper.setText(R.id.tv_sum,StringUtil.valueOf(item.shiTwo));
        helper.setText(R.id.tv_big,StringUtil.valueOf(item.geBig));
        helper.setText(R.id.tv_small,StringUtil.valueOf(item.geSmall));
        helper.setText(R.id.tv_single,StringUtil.valueOf(item.geSingle));
        helper.setText(R.id.tv_two,StringUtil.valueOf(item.geTwo));
        if("大".equals(StringUtil.valueOf(item.shiBig))){
            helper.getView(R.id.tv_bai).setBackgroundColor(Color.parseColor("#6cb1ff"));
        }else {
            helper.getView(R.id.tv_bai).setBackgroundColor(Color.TRANSPARENT);
        }
        if("小".equals(StringUtil.valueOf(item.shiSmall))){
            helper.getView(R.id.tv_shi).setBackgroundColor(Color.parseColor("#6df3ab"));
        }else {
            helper.getView(R.id.tv_shi).setBackgroundColor(Color.TRANSPARENT);
        }
        if("单".equals(StringUtil.valueOf(item.shiSingle))){
            helper.getView(R.id.tv_ge).setBackgroundColor(Color.parseColor("#f37e9c"));
        }else {
            helper.getView(R.id.tv_ge).setBackgroundColor(Color.TRANSPARENT);
        }
        if("双".equals(StringUtil.valueOf(item.shiTwo))){
            helper.getView(R.id.tv_sum).setBackgroundColor(Color.parseColor("#fdee89"));
        }else {
            helper.getView(R.id.tv_sum).setBackgroundColor(Color.TRANSPARENT);
        }


        if("大".equals(StringUtil.valueOf(item.geBig))){
            helper.getView(R.id.tv_big).setBackgroundColor(Color.parseColor("#6cb1ff"));
        }else {
            helper.getView(R.id.tv_big).setBackgroundColor(Color.TRANSPARENT);
        }
        if("小".equals(StringUtil.valueOf(item.geSmall))){
            helper.getView(R.id.tv_small).setBackgroundColor(Color.parseColor("#6df3ab"));
        }else {
            helper.getView(R.id.tv_small).setBackgroundColor(Color.TRANSPARENT);
        }
        if("单".equals(StringUtil.valueOf(item.geSingle))){
            helper.getView(R.id.tv_single).setBackgroundColor(Color.parseColor("#f37e9c"));
        }else {
            helper.getView(R.id.tv_single).setBackgroundColor(Color.TRANSPARENT);
        }
        if("双".equals(StringUtil.valueOf(item.geTwo))){
            helper.getView(R.id.tv_two).setBackgroundColor(Color.parseColor("#fdee89"));
        }else {
            helper.getView(R.id.tv_two).setBackgroundColor(Color.TRANSPARENT);
        }
    }
}
