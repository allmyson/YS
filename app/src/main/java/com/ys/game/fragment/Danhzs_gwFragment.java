package com.ys.game.fragment;

import android.view.View;
import android.widget.LinearLayout;

import com.ys.game.R;
import com.ys.game.adapter.SingleAdapter;
import com.ys.game.sf.Danhzs_gwBean;
import com.ys.game.sf.Danhzs_gwUtil;
import com.ys.game.ui.MyListView;
import com.ys.game.ui.ZsView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename Danhzs_gwFragment
 * @description -------------------------------------------------------
 * @date 2018/11/2 19:12
 */
public class Danhzs_gwFragment extends BaseZSFragment {
    private ZsView zv;
    private MyListView zsLV;
    //    private ZsAdapter zsAdapter;
    private SingleAdapter singleAdapter;
    private List<List<Danhzs_gwBean>> mList;
    private List<List<Danhzs_gwBean>> bean100List;
    private List<List<Danhzs_gwBean>> bean50List;
    private List<List<Danhzs_gwBean>> bean30List;
    private LinearLayout lineLL;

    @Override
    protected void show(int size) {
        mList.clear();
        switch (size) {
            case 30:
                mList.addAll(bean30List);
                zv.setData(list30);
                break;
            case 50:
                mList.addAll(bean50List);
                zv.setData(list50);
                break;
            case 100:
                mList.addAll(bean100List);
                zv.setData(list100);
                break;
        }
        singleAdapter.refresh(mList);
        lineLL.setVisibility(View.VISIBLE);
    }

    @Override
    protected void init() {
        bean100List = new ArrayList<>();
        bean50List = new ArrayList<>();
        bean30List = new ArrayList<>();
        zsLV = getView(R.id.lv_zs);
        mList = new ArrayList<>();
        singleAdapter = new SingleAdapter(mContext, mList, R.layout.item_dhzs5x);
        zsLV.setAdapter(singleAdapter);
        lineLL = getView(R.id.ll_line);
        zv = getView(R.id.zv);
    }

    @Override
    protected void getData() {
        getDefaultData();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_danhzs_gw;
    }

    private List<Integer> list100;
    private List<Integer> list50;
    private List<Integer> list30;

    private void getDefaultData() {
        list100 = Danhzs_gwUtil.list(100);
        list50 = new ArrayList<>();
        list30 = new ArrayList<>();
        for (int i = 50; i < list100.size(); i++) {
            list50.add(list100.get(i));
            if (i >= 70) {
                list30.add(list100.get(i));
            }
        }
        List<List<Danhzs_gwBean>> allList100 = Danhzs_gwUtil.getData(list100);
        List<List<Danhzs_gwBean>> allList50 = Danhzs_gwUtil.getData(list50);
        List<List<Danhzs_gwBean>> allList30 = Danhzs_gwUtil.getData(list30);
        bean100List.addAll(allList100);
        bean50List.addAll(allList50);
        bean30List.addAll(allList30);
    }

}
