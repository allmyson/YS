package com.ys.game.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.ys.game.R;
import com.ys.game.activity.CqsscActivity;
import com.ys.game.activity.WinnerActivity;
import com.ys.game.base.BaseFragment;

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
                startActivity(new Intent(mContext, CqsscActivity.class));
                break;
            case R.id.iv02:
                break;
            case R.id.iv03:
                startActivity(new Intent(mContext, WinnerActivity.class));
                break;
        }
    }
}
