package com.ys.game.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ys.game.R;
import com.ys.game.adapter.Wxhz_DxdsAdapter;
import com.ys.game.sf.Dhzs_5xUtil;
import com.ys.game.sf.Wxhz_dxdsBean;
import com.ys.game.sf.Wxhz_dxdsUtil;
import com.ys.game.ui.MyListView;
import com.ys.game.util.StringUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename Wxhz_dxdsFragment
 * @description -------------------------------------------------------
 * @date 2018/11/2 18:47
 */
public class Wxhz_dxdsFragment extends BaseZSFragment {
    private MyListView zsLV;
    private Wxhz_DxdsAdapter mAdapter;
    private LinearLayout lineLL;
    private List<Wxhz_dxdsBean> mList;
    private List<Wxhz_dxdsBean> bean100List;
    private List<Wxhz_dxdsBean> bean50List;
    private List<Wxhz_dxdsBean> bean30List;
    private TextView countBigTV,countSmallTV,countSingleTV,countTwoTV;
    private TextView lcBigTV,lcSmallTV,lcSingleTV,lcTwoTV;
    private TextView zdylBigTV,zdylSmallTV,zdylSingleTV,zdylTwoTV;
    private TextView pjylBigTV,pjylSmallTV,pjylSingleTV,pjylTwoTV;
    @Override
    protected void show(int size) {
        mList.clear();
        switch (size) {
            case 30:
                mList.addAll(bean30List);
                setText(allList30);
                break;
            case 50:
                mList.addAll(bean50List);
                setText(allList50);
                break;
            case 100:
                mList.addAll(bean100List);
                setText(allList100);
                break;
        }
        mAdapter.refresh(mList);
        lineLL.setVisibility(View.VISIBLE);
    }

    @Override
    protected void init() {
        zsLV = getView(R.id.lv_zs);
        lineLL = getView(R.id.ll_line);
        mList = new ArrayList<>();
        bean100List = new ArrayList<>();
        bean50List = new ArrayList<>();
        bean30List = new ArrayList<>();
        mAdapter = new Wxhz_DxdsAdapter(mContext, mList, R.layout.item_wxhz_dxds);
        zsLV.setAdapter(mAdapter);
        countBigTV = getView(R.id.tv_countBig);
        countSmallTV = getView(R.id.tv_countSmall);
        countSingleTV = getView(R.id.tv_countSingle);
        countTwoTV = getView(R.id.tv_countTwo);
        lcBigTV = getView(R.id.tv_lcBig);
        lcSmallTV = getView(R.id.tv_lcSmall);
        lcSingleTV = getView(R.id.tv_lcSingle);
        lcTwoTV = getView(R.id.tv_lcTwo);
        zdylBigTV = getView(R.id.tv_zdylBig);
        zdylSmallTV = getView(R.id.tv_zdylSmall);
        zdylSingleTV = getView(R.id.tv_zdylSingle);
        zdylTwoTV = getView(R.id.tv_zdylTwo);
        pjylBigTV = getView(R.id.tv_pjylBig);
        pjylSmallTV = getView(R.id.tv_pjylSmall);
        pjylSingleTV = getView(R.id.tv_pjylSingle);
        pjylTwoTV = getView(R.id.tv_pjylTwo);
    }

    @Override
    protected void getData() {
        getDefaultData();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_wxhz_dxds;
    }
    private List<Object> allList100;
    private List<Object> allList50;
    private List<Object> allList30;
    private void getDefaultData() {
        List<List<Integer>> list100 = Dhzs_5xUtil.list(100);
        List<List<Integer>> list50 = new ArrayList<>();
        List<List<Integer>> list30 = new ArrayList<>();
        for (int i = 50; i < list100.size(); i++) {
            list50.add(list100.get(i));
            if (i >= 70) {
                list30.add(list100.get(i));
            }
        }
        allList100 = Wxhz_dxdsUtil.getListData(list100);
        allList50 = Wxhz_dxdsUtil.getListData(list50);
        allList30 = Wxhz_dxdsUtil.getListData(list30);
        bean100List.addAll((Collection<? extends Wxhz_dxdsBean>) allList100.get(0));
        bean50List.addAll((Collection<? extends Wxhz_dxdsBean>) allList50.get(0));
        bean30List.addAll((Collection<? extends Wxhz_dxdsBean>) allList30.get(0));
    }
    private void setText(List<Object> list){
        List<Integer> countList = (List<Integer>) list.get(1);
        List<Integer> lcList = (List<Integer>) list.get(2);
        List<Integer> zdylList = (List<Integer>) list.get(3);
        List<Integer> pjylList = (List<Integer>) list.get(4);
        countBigTV.setText(StringUtil.valueOf(countList.get(0)));
        countSmallTV.setText(StringUtil.valueOf(countList.get(1)));
        countSingleTV.setText(StringUtil.valueOf(countList.get(2)));
        countTwoTV.setText(StringUtil.valueOf(countList.get(3)));
        lcBigTV.setText(StringUtil.valueOf(lcList.get(0)));
        lcSmallTV.setText(StringUtil.valueOf(lcList.get(1)));
        lcSingleTV.setText(StringUtil.valueOf(lcList.get(2)));
        lcTwoTV.setText(StringUtil.valueOf(lcList.get(3)));
        zdylBigTV.setText(StringUtil.valueOf(zdylList.get(0)));
        zdylSmallTV.setText(StringUtil.valueOf(zdylList.get(1)));
        zdylSingleTV.setText(StringUtil.valueOf(zdylList.get(2)));
        zdylTwoTV.setText(StringUtil.valueOf(zdylList.get(3)));
        pjylBigTV.setText(StringUtil.valueOf(pjylList.get(0)));
        pjylSmallTV.setText(StringUtil.valueOf(pjylList.get(1)));
        pjylSingleTV.setText(StringUtil.valueOf(pjylList.get(2)));
        pjylTwoTV.setText(StringUtil.valueOf(pjylList.get(3)));

    }
}
