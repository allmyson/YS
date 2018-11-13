package com.ys.game.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ys.game.R;
import com.ys.game.adapter.Dxds_sgAdapter;
import com.ys.game.sf.Dhzs_5xUtil;
import com.ys.game.sf.Dxds_sgBean;
import com.ys.game.sf.Dxds_sgUtil;
import com.ys.game.ui.MyListView;
import com.ys.game.util.StringUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename Dxds_sgFragment
 * @description ------------大小单双-十个-------------------------------------------
 * @date 2018/11/2 19:13
 */
public class Dxds_sgFragment extends BaseZSFragment {
    private MyListView zsLV;
    private Dxds_sgAdapter mAdapter;
    private LinearLayout lineLL;
    private List<Dxds_sgBean> mList;
    private List<Dxds_sgBean> bean100List;
    private List<Dxds_sgBean> bean50List;
    private List<Dxds_sgBean> bean30List;
    private TextView count1, count2, count3, count4, count5, count6, count7, count8;
    private TextView lc1, lc2, lc3, lc4, lc5, lc6, lc7, lc8;
    private TextView zdyl1, zdyl2, zdyl3, zdyl4, zdyl5, zdyl6, zdyl7, zdyl8;
    private TextView pjyl1, pjyl2, pjyl3, pjyl4, pjyl5, pjyl6, pjyl7, pjyl8;

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
        count1 = getView(R.id.tv_count1);
        count2 = getView(R.id.tv_count2);
        count3 = getView(R.id.tv_count3);
        count4 = getView(R.id.tv_count4);
        count5 = getView(R.id.tv_count5);
        count6 = getView(R.id.tv_count6);
        count7 = getView(R.id.tv_count7);
        count8 = getView(R.id.tv_count8);

        lc1 = getView(R.id.tv_lc1);
        lc2 = getView(R.id.tv_lc2);
        lc3 = getView(R.id.tv_lc3);
        lc4 = getView(R.id.tv_lc4);
        lc5 = getView(R.id.tv_lc5);
        lc6 = getView(R.id.tv_lc6);
        lc7 = getView(R.id.tv_lc7);
        lc8 = getView(R.id.tv_lc8);

        zdyl1 = getView(R.id.tv_zdyl1);
        zdyl2 = getView(R.id.tv_zdyl2);
        zdyl3 = getView(R.id.tv_zdyl3);
        zdyl4 = getView(R.id.tv_zdyl4);
        zdyl5 = getView(R.id.tv_zdyl5);
        zdyl6 = getView(R.id.tv_zdyl6);
        zdyl7 = getView(R.id.tv_zdyl7);
        zdyl8 = getView(R.id.tv_zdyl8);

        pjyl1 = getView(R.id.tv_pjyl1);
        pjyl2 = getView(R.id.tv_pjyl2);
        pjyl3 = getView(R.id.tv_pjyl3);
        pjyl4 = getView(R.id.tv_pjyl4);
        pjyl5 = getView(R.id.tv_pjyl5);
        pjyl6 = getView(R.id.tv_pjyl6);
        pjyl7 = getView(R.id.tv_pjyl7);
        pjyl8 = getView(R.id.tv_pjyl8);

        zsLV = getView(R.id.lv_zs);
        lineLL = getView(R.id.ll_line);
        mList = new ArrayList<>();
        bean100List = new ArrayList<>();
        bean50List = new ArrayList<>();
        bean30List = new ArrayList<>();
        mAdapter = new Dxds_sgAdapter(mContext, mList, R.layout.item_wxhz_dxds);
        zsLV.setAdapter(mAdapter);
    }

    @Override
    protected void getData() {
        getDefaultData();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_dxds_sg;
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
        allList100 = Dxds_sgUtil.getData(list100);
        allList50 = Dxds_sgUtil.getData(list50);
        allList30 = Dxds_sgUtil.getData(list30);
        bean100List.addAll((Collection<? extends Dxds_sgBean>) allList100.get(0));
        bean50List.addAll((Collection<? extends Dxds_sgBean>) allList50.get(0));
        bean30List.addAll((Collection<? extends Dxds_sgBean>) allList30.get(0));
    }

    private void setText(List<Object> allList) {
        List<Integer> countList = (List<Integer>) allList.get(1);
        List<Integer> lcList = (List<Integer>) allList.get(2);
        List<Integer> zdylList = (List<Integer>) allList.get(3);
        List<Integer> pjylList = (List<Integer>) allList.get(4);
        if (countList != null && countList.size() == 8) {
            count1.setText(StringUtil.valueOf(countList.get(0)));
            count2.setText(StringUtil.valueOf(countList.get(1)));
            count3.setText(StringUtil.valueOf(countList.get(2)));
            count4.setText(StringUtil.valueOf(countList.get(3)));
            count5.setText(StringUtil.valueOf(countList.get(4)));
            count6.setText(StringUtil.valueOf(countList.get(5)));
            count7.setText(StringUtil.valueOf(countList.get(6)));
            count8.setText(StringUtil.valueOf(countList.get(7)));
        }

        if (lcList != null && lcList.size() == 8) {
            lc1.setText(StringUtil.valueOf(lcList.get(0)));
            lc2.setText(StringUtil.valueOf(lcList.get(1)));
            lc3.setText(StringUtil.valueOf(lcList.get(2)));
            lc4.setText(StringUtil.valueOf(lcList.get(3)));
            lc5.setText(StringUtil.valueOf(lcList.get(4)));
            lc6.setText(StringUtil.valueOf(lcList.get(5)));
            lc7.setText(StringUtil.valueOf(lcList.get(6)));
            lc8.setText(StringUtil.valueOf(lcList.get(7)));
        }

        if (zdylList != null && zdylList.size() == 8) {
            zdyl1.setText(StringUtil.valueOf(zdylList.get(0)));
            zdyl2.setText(StringUtil.valueOf(zdylList.get(1)));
            zdyl3.setText(StringUtil.valueOf(zdylList.get(2)));
            zdyl4.setText(StringUtil.valueOf(zdylList.get(3)));
            zdyl5.setText(StringUtil.valueOf(zdylList.get(4)));
            zdyl6.setText(StringUtil.valueOf(zdylList.get(5)));
            zdyl7.setText(StringUtil.valueOf(zdylList.get(6)));
            zdyl8.setText(StringUtil.valueOf(zdylList.get(7)));
        }

        if (pjylList != null && pjylList.size() == 8) {
            pjyl1.setText(StringUtil.valueOf(pjylList.get(0)));
            pjyl2.setText(StringUtil.valueOf(pjylList.get(1)));
            pjyl3.setText(StringUtil.valueOf(pjylList.get(2)));
            pjyl4.setText(StringUtil.valueOf(pjylList.get(3)));
            pjyl5.setText(StringUtil.valueOf(pjylList.get(4)));
            pjyl6.setText(StringUtil.valueOf(pjylList.get(5)));
            pjyl7.setText(StringUtil.valueOf(pjylList.get(6)));
            pjyl8.setText(StringUtil.valueOf(pjylList.get(7)));
        }
    }
}
