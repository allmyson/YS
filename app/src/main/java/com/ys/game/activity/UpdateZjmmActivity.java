package com.ys.game.activity;

import android.view.View;
import android.widget.TextView;

import com.ys.game.R;
import com.ys.game.base.BaseActivity;
import com.ys.game.dialog.DialogUtil;
import com.ys.game.dialog.ListDialog;

import java.util.ArrayList;
import java.util.List;

public class UpdateZjmmActivity extends BaseActivity {
    private TextView tv1, tv2, tv3;
    @Override
    public int getLayoutId() {
        return R.layout.activity_update_zjmm;
    }

    @Override
    public void initView() {
        backRL = getView(R.id.rl_back);
        backRL.setOnClickListener(this);
        tv1 = getView(R.id.tv_problem1);
        tv2 = getView(R.id.tv_problem2);
        tv3 = getView(R.id.tv_problem3);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_problem1:
                DialogUtil.showProblem(mContext, getList(1), new ListDialog.ClickListener() {
                    @Override
                    public void click(String type) {
                        tv1.setText(type);
                    }
                });
                break;

            case R.id.tv_problem2:
                DialogUtil.showProblem(mContext, getList(2), new ListDialog.ClickListener() {
                    @Override
                    public void click(String type) {
                        tv2.setText(type);
                    }
                });
                break;

            case R.id.tv_problem3:
                DialogUtil.showProblem(mContext, getList(3), new ListDialog.ClickListener() {
                    @Override
                    public void click(String type) {
                        tv3.setText(type);
                    }
                });
                break;
        }
    }

    @Override
    public void getData() {

    }

    private List<String> getList(int type) {
        List<String> list = new ArrayList<>();
        if (type == 1) {
            list.add("我最爱看哪部美剧?");
            list.add("我最喜欢吃的美食是?");
            list.add("我最喜欢的球队是?");
            list.add("我最喜欢玩的游戏是?");
            list.add("我最喜欢的颜色是?");
            list.add("我最爱看的电影是?");
        } else if (type == 2) {
            list.add("我的座右铭是?");
            list.add("我的幸运数字是?");
            list.add("我父亲的姓名是?");
            list.add("我母亲的姓名是?");
            list.add("我配偶的名字是?");
        } else if (type == 3) {
            list.add("我的出生地是?");
            list.add("我毕业的学校是?");
            list.add("我父亲的生日是?");
            list.add("我母亲的生日是?");
            list.add("我配偶的生日是?");
        }
        return list;
    }
}
