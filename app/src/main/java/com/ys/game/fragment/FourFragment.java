package com.ys.game.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ys.game.R;
import com.ys.game.activity.CzActivity;
import com.ys.game.activity.KHActivity;
import com.ys.game.activity.LxkfActivity;
import com.ys.game.activity.SafeActivity;
import com.ys.game.activity.SetActivity;
import com.ys.game.activity.TeamGLActivity;
import com.ys.game.activity.TeamJLActivity;
import com.ys.game.activity.TxActivity;
import com.ys.game.activity.XFJLActivity;
import com.ys.game.adapter.MyAdapter;
import com.ys.game.base.BaseFragment;
import com.ys.game.bean.LoginBean;
import com.ys.game.sp.UserSP;
import com.ys.game.ui.MyListView;
import com.ys.game.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename OneFragment
 * @description -------------------------------------------------------
 * @date 2018/10/23 17:09
 */
public class FourFragment extends BaseFragment implements View.OnClickListener {
    private MyListView mlv;
    private List<String> myList;
    private MyAdapter myAdapter;
    private RelativeLayout setRL;
    private Button czBtn, tbBtn;
    private TextView nicknameTV, usernameTV, fdTV, yueTV;
    private LoginBean loginBean;

    public static FourFragment newInstance() {
        return new FourFragment();
    }

    @Override
    protected void init() {
        mlv = getView(R.id.mlv_my);
        getView(R.id.btn_cz).setOnClickListener(this);
        getView(R.id.btn_tb).setOnClickListener(this);
        myList = new ArrayList<>();
        myList.add("消费记录");
        myList.add("安全中心");
        myList.add("开户");
        myList.add("团队管理");
        myList.add("团队记录");
        myList.add("联系客服");
        myAdapter = new MyAdapter(mContext, myList, R.layout.item_msg);
        mlv.setAdapter(myAdapter);
        mlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(mContext, XFJLActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(mContext, SafeActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(mContext, KHActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(mContext, TeamGLActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(mContext, TeamJLActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(mContext, LxkfActivity.class));
                        break;
                }
            }
        });

        setRL = getView(R.id.rl_set);
        setRL.setOnClickListener(this);

        nicknameTV = getView(R.id.tv_nickName);
        usernameTV = getView(R.id.tv_username);
        fdTV = getView(R.id.tv_fd);
        yueTV = getView(R.id.tv_yue);
    }

    @Override
    protected void getData() {
        loginBean = UserSP.getInfo(mContext);
        if (loginBean != null && loginBean.data != null) {
            nicknameTV.setText("昵称：" + StringUtil.valueOf(loginBean.data.consumerName));
            usernameTV.setText("用户名：" + StringUtil.valueOf(loginBean.data.loginName));
            fdTV.setText("返点：" + StringUtil.valueOf(loginBean.data.backNum));
            yueTV.setText("" + StringUtil.StringToDouble(loginBean.data.balance));
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_four;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_set:
                startActivity(new Intent(mContext, SetActivity.class));
                break;
            case R.id.btn_cz:
                startActivity(new Intent(mContext, CzActivity.class));
                break;
            case R.id.btn_tb:
                startActivity(new Intent(mContext, TxActivity.class));
                break;
        }
    }
}
