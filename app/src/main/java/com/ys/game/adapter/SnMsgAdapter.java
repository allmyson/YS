package com.ys.game.adapter;

import android.content.Context;

import com.ys.game.R;

import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename SnMsgAdapter
 * @description -------------------------------------------------------
 * @date 2018/11/26 17:20
 */
public class SnMsgAdapter extends CommonAdapter<String> {
    public SnMsgAdapter(Context context, List<String> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, String item, int position) {
        helper.setText(R.id.tv_sn_msg,"【购买通知】  玩家ysapp9821于17:28:50成功购买了SN，购买编号为SN106。");
    }
}
