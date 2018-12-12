package com.ys.game.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.rest.Response;
import com.ys.game.R;
import com.ys.game.activity.CqsscActivity;
import com.ys.game.activity.MainActivity;
import com.ys.game.activity.MsgDetailActivity;
import com.ys.game.activity.WinnerActivity;
import com.ys.game.adapter.GameAdapter;
import com.ys.game.adapter.MsgAdapter;
import com.ys.game.base.BaseFragment;
import com.ys.game.bean.GameBean;
import com.ys.game.bean.GzGameBean;
import com.ys.game.bean.MsgBean;
import com.ys.game.http.HttpListener;
import com.ys.game.sp.GameSP;
import com.ys.game.ui.MyListView;
import com.ys.game.util.HttpUtil;
import com.ys.game.util.YS;

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
    private List<MsgBean.DataBeanX.DataBean> msgBeanList;
    private List<GzGameBean> gameList;
    private GameAdapter gameAdapter;

    public static OneFragment newInstance() {
        return new OneFragment();
    }

    @Override
    protected void init() {
        msgBeanList = new ArrayList<>();
        gameList = new ArrayList<>();
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
                MsgDetailActivity.intentToMsg(mContext, msgAdapter.getItem(position));
            }
        });
        moreLL.setOnClickListener(this);
        gameMLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (gameAdapter.getItem(position).type == YS.TYPE_ZHDSLZ) {
                    WinnerActivity.toWinner(mContext);
                } else {
                    CqsscActivity.intentToSSC(mContext, gameAdapter.getItem(position).type);
                }
                addBean(gameAdapter.getItem(position).type);
            }
        });
    }

    private void addBean(int type) {
        GameBean gameBean = new GameBean();
        gameBean.type = type;
        gameBean.time = System.currentTimeMillis();
        GameSP.add(mContext, gameBean);
    }

    @Override
    protected void getData() {
        getMsg();
    }

    private void getMsg() {
        HttpUtil.selectMsg(mContext, 1, 3, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                msgBeanList.clear();
                MsgBean msgBean = new Gson().fromJson(response.get(), MsgBean.class);
                if (msgBean != null && YS.SUCCESE.equals(msgBean.code) && msgBean.data != null && msgBean.data.data
                        != null && msgBean.data.data.size() > 0) {
                    msgBeanList.addAll(msgBean.data.data);
                }
                msgAdapter.refresh(msgBeanList);
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        gameList.clear();
        gameList.addAll(GameSP.getGzGameList(mContext));
        gameAdapter.refresh(gameList);
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
