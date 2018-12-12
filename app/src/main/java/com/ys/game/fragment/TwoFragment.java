package com.ys.game.fragment;

import android.view.View;
import android.widget.ImageView;

import com.ys.game.R;
import com.ys.game.activity.CqsscActivity;
import com.ys.game.activity.WinnerActivity;
import com.ys.game.base.BaseFragment;
import com.ys.game.bean.GameBean;
import com.ys.game.sp.GameSP;
import com.ys.game.util.YS;

/**
 * @author lh
 * @version 1.0.0
 * @filename OneFragment
 * @description -------------------------------------------------------
 * @date 2018/10/23 17:09
 */
public class TwoFragment extends BaseFragment implements View.OnClickListener {
    private ImageView cqsscIV, txffcIV, slzIV;

    public static TwoFragment newInstance() {
        return new TwoFragment();
    }

    @Override
    protected void init() {
        cqsscIV = getView(R.id.iv01);
        txffcIV = getView(R.id.iv02);
        slzIV = getView(R.id.iv03);
        cqsscIV.setOnClickListener(this);
        txffcIV.setOnClickListener(this);
        slzIV.setOnClickListener(this);
    }

    @Override
    protected void getData() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_two;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv01:
                CqsscActivity.intentToSSC(mContext, YS.TYPE_CQSSC);
                addBean(YS.TYPE_CQSSC);
                break;
            case R.id.iv02:
                CqsscActivity.intentToSSC(mContext, YS.TYPE_TXFFC);
                addBean(YS.TYPE_TXFFC);
                break;
            case R.id.iv03:
               WinnerActivity.toWinner(mContext);
                addBean(YS.TYPE_ZHDSLZ);
                break;
        }
    }

    private void addBean(int type) {
        GameBean gameBean = new GameBean();
        gameBean.type = type;
        gameBean.time = System.currentTimeMillis();
        GameSP.add(mContext, gameBean);
    }


    private static final int MIN_DELAY_TIME = 1000;  // 两次点击间隔不能少于1000ms
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = true;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= MIN_DELAY_TIME) {
            flag = false;
        }
        lastClickTime = currentClickTime;
        return flag;
    }
}
