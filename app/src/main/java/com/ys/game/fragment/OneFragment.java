package com.ys.game.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.ys.game.R;
import com.ys.game.activity.MainActivity;
import com.ys.game.activity.MsgDetailActivity;
import com.ys.game.adapter.GameAdapter;
import com.ys.game.adapter.MsgAdapter;
import com.ys.game.base.BaseFragment;
import com.ys.game.bean.MsgBean;
import com.ys.game.ui.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename OneFragment
 * @description -------------------------------------------------------
 * @date 2018/10/23 17:09
 */
public class OneFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout moreLL;
    private MyListView msgMLV, gameMLV;
    private MsgAdapter msgAdapter;
    private List<MsgBean> msgBeanList;
    private List<Object> gameList;
    private GameAdapter gameAdapter;

    public static OneFragment newInstance() {
        return new OneFragment();
    }

    @Override
    protected void init() {
        msgBeanList = new ArrayList<>();
        msgBeanList.add(null);
        msgBeanList.add(null);
        msgBeanList.add(null);
        gameList = new ArrayList<>();
        gameList.add(null);
        gameList.add(null);
        moreLL = getView(R.id.ll_more);
        msgMLV = getView(R.id.mlv_msg);
        gameMLV = getView(R.id.mlv_game);
        msgAdapter = new MsgAdapter(mContext, msgBeanList, R.layout.item_msg);
        msgMLV.setAdapter(msgAdapter);
        gameAdapter = new GameAdapter(mContext, gameList, R.layout.item_game);
        gameMLV.setAdapter(gameAdapter);
        msgMLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MsgDetailActivity.intentToMsg(mContext);
            }
        });
        moreLL.setOnClickListener(this);
    }

    @Override
    protected void getData() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_one;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_more:
                if (mActivity instanceof MainActivity) {
                    ((MainActivity) mActivity).setPositionTab(2);
                }
                break;
        }
    }
}
