package com.ys.game.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

import com.ys.game.R;
import com.ys.game.activity.SetActivity;
import com.ys.game.adapter.MyAdapter;
import com.ys.game.base.BaseFragment;
import com.ys.game.ui.MyListView;

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

    public static FourFragment newInstance() {
        return new FourFragment();
    }

    @Override
    protected void init() {
        mlv = getView(R.id.mlv_my);
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

            }
        });

        setRL = getView(R.id.rl_set);
        setRL.setOnClickListener(this);
    }

    @Override
    protected void getData() {

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
        }
    }
}
