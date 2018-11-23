package com.ys.game.fragment;

import com.ys.game.R;
import com.ys.game.base.BaseFragment;
import com.ys.game.ui.CircleProgressBar;

/**
 * @author lh
 * @version 1.0.0
 * @filename WinnerTZFragment
 * @description -------------------------------------------------------
 * @date 2018/11/23 15:07
 */
public class WinnerTZFragment extends BaseFragment {
    private CircleProgressBar totalCPB, priceCPB;

    @Override
    protected void init() {
        totalCPB = getView(R.id.cpb_total);
        priceCPB = getView(R.id.cpb_price);
        totalCPB.setPercent("29348");
//        totalCPB.setColor(Color.parseColor("#dcdcdc"), Color.parseColor("#ec2851"));
//        priceCPB.setColor(Color.parseColor("#dcdcdc"), Color.parseColor("#5574f7"));
        totalCPB.setProgress(80, true);
        priceCPB.setPercent("125");
        priceCPB.setProgress(50, true);
    }

    @Override
    protected void getData() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_winner_tz;
    }
}
