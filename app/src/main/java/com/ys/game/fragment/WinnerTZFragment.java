package com.ys.game.fragment;

import android.os.Build;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ys.game.R;
import com.ys.game.adapter.MySNAdapter;
import com.ys.game.adapter.SnMsgAdapter;
import com.ys.game.base.BaseFragment;
import com.ys.game.ui.CircleProgressBar;
import com.ys.game.ui.MyGridView;
import com.ys.game.util.L;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename WinnerTZFragment
 * @description -------------------------------------------------------
 * @date 2018/11/23 15:07
 */
public class WinnerTZFragment extends BaseFragment {
    private CircleProgressBar totalCPB, priceCPB;
    private ProgressBar fhPB;
    private TextView fhTV;
    private MyGridView myGridView;
    private MySNAdapter mySNAdapter;
    private List<String> mySNList;
    private LinearLayout lineLL;
    private ListView msgLV;
    private SnMsgAdapter snMsgAdapter;
    private List<String> msgList;
    @Override
    protected void init() {
        msgLV = getView(R.id.lv_msg);
        lineLL = getView(R.id.ll_line);
        totalCPB = getView(R.id.cpb_total);
        priceCPB = getView(R.id.cpb_price);
        totalCPB.setPercent("29348");
        totalCPB.setProgress(80, true);
        priceCPB.setPercent("125");
        priceCPB.setProgress(50, true);
        fhPB = getView(R.id.progesss_fh);
        fhTV = getView(R.id.tv_fh);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            fhPB.setProgress(80, true);
        } else {
            fhPB.setProgress(80);
        }
        myGridView = getView(R.id.mgv_sn);
        mySNList = new ArrayList<>();
        mySNList.add(null);
        mySNList.add(null);
        mySNList.add(null);
        mySNList.add(null);
        mySNList.add(null);
        mySNList.add(null);
        mySNList.add(null);
        mySNList.add(null);
        mySNList.add(null);
        mySNList.add(null);
        mySNList.add(null);
        mySNList.add(null);
        mySNList.add(null);
        mySNList.add(null);
        mySNAdapter = new MySNAdapter(mContext, mySNList, R.layout.item_my_sn);
        myGridView.setAdapter(mySNAdapter);

        ViewTreeObserver vto = myGridView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                myGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                L.e("mygridview高度" + myGridView.getHeight());
                FrameLayout.LayoutParams linearParams =(FrameLayout.LayoutParams) lineLL.getLayoutParams();
                linearParams.height = myGridView.getHeight();
                lineLL.setLayoutParams(linearParams);
            }
        });
        msgList = new ArrayList<>();
        msgList.add(null);
        msgList.add(null);
        msgList.add(null);
        msgList.add(null);
        msgList.add(null);
        msgList.add(null);
        msgList.add(null);
        msgList.add(null);
        msgList.add(null);
        msgList.add(null);
        msgList.add(null);
        msgList.add(null);
        msgList.add(null);
        msgList.add(null);
        msgList.add(null);
        msgList.add(null);
        snMsgAdapter = new SnMsgAdapter(mContext,msgList,R.layout.item_sn_msg);
        msgLV.setAdapter(snMsgAdapter);
    }

    @Override
    protected void getData() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_winner_tz;
    }
}
