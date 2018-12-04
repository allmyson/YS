package com.ys.game.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.rest.Response;
import com.ys.game.R;
import com.ys.game.activity.CqsscActivity;
import com.ys.game.base.BaseFragment;
import com.ys.game.bean.ResultBean;
import com.ys.game.dialog.DialogUtil;
import com.ys.game.dialog.ZsChoose;
import com.ys.game.http.HttpListener;
import com.ys.game.util.HttpUtil;
import com.ys.game.util.YS;

import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename ZSFragment
 * @description ---------cqssc走势----------------------------------------------
 * @date 2018/11/1 15:37
 */
public class ZSFragment extends BaseFragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private TextView typeTV;
    private RadioGroup radioGroup;
    private RadioButton rb30, rb50, rb100;
    private RelativeLayout setRL;
    private BaseZSFragment currentFragment;
    private BaseZSFragment dhzs_5xFragment, dhzs_heFragment, wxhz_dxdsFragment, dxds_sgFragment, danhzs_gwFragment;

    public static ZSFragment newInstance() {
        return new ZSFragment();
    }

    private FragmentManager manager;

    @Override
    protected void init() {
        typeTV = getView(R.id.tv_type);
        radioGroup = getView(R.id.rg);
        rb30 = getView(R.id.rb30);
        rb50 = getView(R.id.rb50);
        rb100 = getView(R.id.rb100);
        setRL = getView(R.id.rl_set);
        setRL.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        manager = getChildFragmentManager();
//        initFragment();
//        showFragment(dhzs_5xFragment);
    }

    @Override
    protected void getData() {
        getResult();
//        sendMsg();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_zs;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_set:
                DialogUtil.showZsChoose(mContext, typeTV.getText().toString(), new ZsChoose.ClickListener() {
                    @Override
                    public void click(String type) {
                        typeTV.setText(type);
                        if ("单号走势_个位".equals(type)) {
                            showFragment(danhzs_gwFragment);
                        } else if ("多号走势_五星".equals(type)) {
                            showFragment(dhzs_5xFragment);
                        } else if ("多号走势_后二".equals(type)) {
                            showFragment(dhzs_heFragment);
                        } else if ("大小单双_十个".equals(type)) {
                            showFragment(dxds_sgFragment);
                        } else if ("五星和值_大小单双".equals(type)) {
                            showFragment(wxhz_dxdsFragment);
                        }
                        sendMsg();
                    }
                });
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb30:
                if (currentFragment != null) {
                    currentFragment.show(30);
                }
                break;
            case R.id.rb50:
                if (currentFragment != null) {
                    currentFragment.show(50);
                }
                break;
            case R.id.rb100:
                if (currentFragment != null) {
                    currentFragment.show(100);
                }
                break;
        }
    }

    /**
     * 初始化Fragment
     */
    private void initFragment(List<ResultBean.DataBean> list) {
        dhzs_5xFragment = Dhzs_5xFragment.newInstance(list);
        wxhz_dxdsFragment = Wxhz_dxdsFragment.newInstance(list);
        dxds_sgFragment = Dxds_sgFragment.newInstance(list);
        danhzs_gwFragment = Danhzs_gwFragment.newInstance(list);
        dhzs_heFragment = Dhzs_heFragment.newInstance(list);
    }

    /**
     * @param fragment
     */
    private void showFragment(BaseZSFragment fragment) {
        if (currentFragment != fragment) {
            FragmentTransaction transaction = manager.beginTransaction();
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            currentFragment = fragment;
            if (!fragment.isAdded()) {
                transaction.add(R.id.fl_parent, fragment).show(fragment).commit();
            } else {
                transaction.show(fragment).commit();
            }
        }
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                if (radioGroup.getCheckedRadioButtonId() == rb30.getId()) {
//                    show("当前是rb30选中状态");
                    if (currentFragment != null) {
                        currentFragment.show(30);
                    }
                } else {
                    rb30.performClick();
                }
            }
        }
    };

    private void sendMsg() {
        Message msg = new Message();
        msg.what = 100;
        handler.sendMessageDelayed(msg, 500);
    }

    private void getResult() {
        HttpUtil.getKJResult(mContext, ((CqsscActivity) getActivity()).getType(), 100, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                ResultBean resultBean = new Gson().fromJson(response.get(), ResultBean.class);
                if (resultBean != null && YS.SUCCESE.equals(resultBean.code) && resultBean.data != null && resultBean
                        .data.size() > 0) {
                    initFragment(resultBean.data);
                    showFragment(dhzs_5xFragment);
                    sendMsg();
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }
}
