package com.ys.game.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ys.game.R;
import com.ys.game.adapter.ZsAdapter;
import com.ys.game.sf.Dhzs_5xBean;
import com.ys.game.sf.Dhzs_5xUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename Dhzs_5xFragment
 * @description -------------------------------------------------------
 * @date 2018/11/2 18:10
 */
public class Dhzs_heFragment extends BaseZSFragment {
    private ListView zsLV;
    private ZsAdapter zsAdapter;
    private List<List<Dhzs_5xBean>> mList;
    private List<List<Dhzs_5xBean>> bean100List;
    private List<List<Dhzs_5xBean>> bean50List;
    private List<List<Dhzs_5xBean>> bean30List;
    private LinearLayout lineLL;
    public static Dhzs_heFragment newInstance() {
        return new Dhzs_heFragment();
    }

    @Override
    protected void init() {
        bean100List = new ArrayList<>();
        bean50List = new ArrayList<>();
        bean30List = new ArrayList<>();
        zsLV = getView(R.id.lv_zs);
        mList = new ArrayList<>();
        zsAdapter = new ZsAdapter(mContext, mList, R.layout.item_dhzs5x);
        zsLV.setAdapter(zsAdapter);
        lineLL = getView(R.id.ll_line);
    }

    @Override
    protected void getData() {
        getDefaultData();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_dhzs_5x;
    }

    private void getDefaultData() {
        List<List<Integer>> list100 = Dhzs_5xUtil.getHEList(100);
        List<List<Integer>> list50 = new ArrayList<>();
        List<List<Integer>> list30 = new ArrayList<>();
        for (int i = 50; i < list100.size(); i++) {
            list50.add(list100.get(i));
            if (i >= 70) {
                list30.add(list100.get(i));
            }
        }
        List<List<Dhzs_5xBean>> allList100 = Dhzs_5xUtil.getShowData(list100);
        List<List<Dhzs_5xBean>> allList50 = Dhzs_5xUtil.getShowData(list50);
        List<List<Dhzs_5xBean>> allList30 = Dhzs_5xUtil.getShowData(list30);
        bean100List.addAll(allList100);
        bean50List.addAll(allList50);
        bean30List.addAll(allList30);
    }

    @Override
    protected void show(int size) {
        mList.clear();
        switch (size){
            case 30:
                mList.addAll(bean30List);
                break;
            case 50:
                mList.addAll(bean50List);
                break;
            case 100:
                mList.addAll(bean100List);
                break;
        }
        zsAdapter.refresh(mList);
        lineLL.setVisibility(View.VISIBLE);
    }
}
