package com.ys.game.adapter;

import android.content.Context;
import android.text.Html;
import android.widget.TextView;

import com.ys.game.R;

import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename GameAdapter
 * @description -------------------------------------------------------
 * @date 2018/10/23 18:02
 */
public class GameAdapter extends CommonAdapter<Object> {
    public GameAdapter(Context context, List<Object> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, Object item, int position) {
        TextView numTV = helper.getView(R.id.tv_num);
        String num = String.format("最近查看<font color=\"#FF0000\">%s</font>次", 4);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            numTV.setText(Html.fromHtml(num, Html.FROM_HTML_MODE_LEGACY));
        } else {
            numTV.setText(Html.fromHtml(num));
        }
    }
}
