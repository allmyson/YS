package com.ys.game.activity;

import android.widget.EditText;

import com.ys.game.R;
import com.ys.game.base.BaseActivity;

public class TxActivity extends BaseActivity {
    private EditText et;
    @Override
    public int getLayoutId() {
        return R.layout.activity_tx;
    }

    @Override
    public void initView() {
        backRL = getView(R.id.rl_back);
        backRL.setOnClickListener(this);
        et = getView(R.id.et_);
        et.setSelection(et.getText().toString().length());
    }

    @Override
    public void getData() {

    }
}
