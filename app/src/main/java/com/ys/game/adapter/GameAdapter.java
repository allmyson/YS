package com.ys.game.adapter;

import android.content.Context;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.ys.game.R;
import com.ys.game.bean.GzGameBean;
import com.ys.game.util.DateUtil;
import com.ys.game.util.StringUtil;
import com.ys.game.util.YS;

import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename GameAdapter
 * @description -------------------------------------------------------
 * @date 2018/10/23 18:02
 */
public class GameAdapter extends CommonAdapter<GzGameBean> {
    public GameAdapter(Context context, List<GzGameBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, GzGameBean item, int position) {
        TextView numTV = helper.getView(R.id.tv_num);
        String num = String.format("最近查看<font color=\"#FF0000\">%s</font>次", item.count);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            numTV.setText(Html.fromHtml(num, Html.FROM_HTML_MODE_LEGACY));
        } else {
            numTV.setText(Html.fromHtml(num));
        }
        helper.setText(R.id.tv_name, StringUtil.valueOf(item.name));
        ImageView iv = helper.getView(R.id.iv_);
        if (item.type == YS.TYPE_CQSSC) {
            iv.setImageResource(R.mipmap.ic_cqssc);
        } else if (item.type == YS.TYPE_TXFFC) {
            iv.setImageResource(R.mipmap.ic_txffc);
        } else if (item.type == YS.TYPE_ZHDSLZ) {
            iv.setImageResource(R.mipmap.ic_slz);
        }
        helper.setText(R.id.tv_time, DateUtil.getLongDate2(item.endTime));
    }
}
