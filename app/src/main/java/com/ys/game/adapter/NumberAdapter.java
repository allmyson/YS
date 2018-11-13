package com.ys.game.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.ys.game.R;
import com.ys.game.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename NumberAdapter
 * @description -------------------------------------------------------
 * @date 2018/11/8 18:50
 */
public class NumberAdapter extends CommonAdapter<String> {
    private List<Boolean> list;

    public NumberAdapter(Context context, List<String> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
        list = new ArrayList<>();
        for (int i = 0; i < mDatas.size(); i++) {
            list.add(false);
        }
    }

    @Override
    public void convert(ViewHolder helper, String item, int position) {
        helper.setText(R.id.tv_number, StringUtil.valueOf(item));
        TextView tv = helper.getView(R.id.tv_number);
        if (list.get(position)) {
            tv.setBackgroundResource(R.drawable.circle_cornor_main);
            tv.setTextColor(Color.WHITE);
        } else {
            tv.setBackgroundResource(R.drawable.circle_cornor_main2);
            tv.setTextColor(mContext.getResources().getColor(R.color.main_color));
        }
    }

    public void setList(List<Boolean> list) {
        this.list = list;
    }
    public void cancelChooseOne(int position) {
        for (int i = 0; i < list.size(); i++) {
            if (i == position) {
                list.set(i, false);
                break;
            }
        }
        notifyDataSetInvalidated();
    }
    public void chooseOne(int position) {
        for (int i = 0; i < list.size(); i++) {
            if (i == position) {
                list.set(i, true);
                break;
            }
        }
        notifyDataSetInvalidated();
    }

    public void chooseAll() {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, true);
        }
        notifyDataSetInvalidated();
    }

    public void chooseBig() {
        for (int i = 0; i < list.size(); i++) {
            if (i >= 5) {
                list.set(i, true);
            } else {
                list.set(i, false);
            }
        }
        notifyDataSetInvalidated();
    }

    public void chooseSmall() {
        for (int i = 0; i < list.size(); i++) {
            if (i < 5) {
                list.set(i, true);
            } else {
                list.set(i, false);
            }
        }
        notifyDataSetInvalidated();
    }

    public void chooseSingle() {
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 != 0) {
                list.set(i, true);
            } else {
                list.set(i, false);
            }
        }
        notifyDataSetInvalidated();
    }

    public void chooseDouble() {
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 == 0) {
                list.set(i, true);
            } else {
                list.set(i, false);
            }
        }
        notifyDataSetInvalidated();
    }

    public void clear() {
        for (int i = 0; i < mDatas.size(); i++) {
            list.set(i, false);
        }
        notifyDataSetInvalidated();
    }
}
