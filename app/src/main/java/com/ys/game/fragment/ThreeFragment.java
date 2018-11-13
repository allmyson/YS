package com.ys.game.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ys.game.R;
import com.ys.game.activity.MsgDetailActivity;
import com.ys.game.adapter.MsgAdapter2;
import com.ys.game.base.BaseFragment;
import com.ys.game.bean.MsgBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename OneFragment
 * @description -------------------------------------------------------
 * @date 2018/10/23 17:09
 */
public class ThreeFragment extends BaseFragment {
    private ListView lv;
    private List<MsgBean> msgBeanList;
    private MsgAdapter2 msgAdapter2;

    public static ThreeFragment newInstance() {
        return new ThreeFragment();
    }

    @Override
    protected void init() {
        lv = getView(R.id.lv_);
        msgBeanList = new ArrayList<>();
        msgBeanList.add(null);
        msgBeanList.add(null);
        msgBeanList.add(null);
        msgBeanList.add(null);
        msgBeanList.add(null);
        msgBeanList.add(null);
        msgAdapter2 = new MsgAdapter2(mContext, msgBeanList, R.layout.item_msg2);
        lv.setAdapter(msgAdapter2);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MsgDetailActivity.intentToMsg(mContext);
            }
        });
    }

    @Override
    protected void getData() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_three;
    }
}
