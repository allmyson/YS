package com.ys.game.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ys.game.R;
import com.ys.game.bean.TeamBean;
import com.ys.game.util.DateUtil;
import com.ys.game.util.StringUtil;

import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename TeamAdapter
 * @description -------------------------------------------------------
 * @date 2018/11/29 18:20
 */
public class TeamAdapter extends CommonAdapter<TeamBean.DataBean.ListMemberBean> {
    public TeamAdapter(Context context, List<TeamBean.DataBean.ListMemberBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, TeamBean.DataBean.ListMemberBean item, int position) {
        helper.setText(R.id.tv_nickName,"昵称\t" + StringUtil.valueOf(item.consumerName));
        helper.setText(R.id.tv_loginName,"用户名\t" + StringUtil.valueOf(item.loginName));
        helper.setText(R.id.tv_time,DateUtil.changeTimeToYMDHMS(item.onlineTime));
        ImageView headIV = helper.getView(R.id.iv_head);
        Glide.with(mContext).load(item.consumerImg).error(R.mipmap.bg_default_head2).into(headIV);
    }
}
