package com.ys.game.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.rest.Response;
import com.ys.game.R;
import com.ys.game.activity.MsgDetailActivity;
import com.ys.game.adapter.MsgAdapter2;
import com.ys.game.base.BaseFragment;
import com.ys.game.bean.MsgBean;
import com.ys.game.http.HttpListener;
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
public class ThreeFragment extends BaseFragment {
    private ListView lv;
    private List<MsgBean.DataBeanX.DataBean> msgBeanList;
    private MsgAdapter2 msgAdapter2;

    public static ThreeFragment newInstance() {
        return new ThreeFragment();
    }

    @Override
    protected void init() {
        lv = getView(R.id.lv_);
        msgBeanList = new ArrayList<>();
        msgAdapter2 = new MsgAdapter2(mContext, msgBeanList, R.layout.item_msg2);
        lv.setAdapter(msgAdapter2);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MsgDetailActivity.intentToMsg(mContext,msgAdapter2.getItem(position));
            }
        });
    }

    @Override
    protected void getData() {
        getMsg();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_three;
    }

    private void getMsg() {
        HttpUtil.selectMsg(mContext, 1, 20, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                msgBeanList.clear();
                MsgBean msgBean = new Gson().fromJson(response.get(), MsgBean.class);
                if (msgBean != null && YS.SUCCESE.equals(msgBean.code) && msgBean.data != null && msgBean.data.data
                        != null && msgBean.data.data.size() > 0) {
                    msgBeanList.addAll(msgBean.data.data);
                }
                msgAdapter2.refresh(msgBeanList);
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }
}
