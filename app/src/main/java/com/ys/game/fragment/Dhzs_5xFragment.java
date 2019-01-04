package com.ys.game.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ys.game.R;
import com.ys.game.adapter.ZsAdapter;
import com.ys.game.bean.ResultBean;
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
public class Dhzs_5xFragment extends BaseZSFragment {
    private ListView zsLV;
    private ZsAdapter zsAdapter;
    private List<List<Dhzs_5xBean>> mList;
    private List<List<Dhzs_5xBean>> bean100List;
    private List<List<Dhzs_5xBean>> bean50List;
    private List<List<Dhzs_5xBean>> bean30List;
    private LinearLayout lineLL;
    private List<ResultBean.DataBean> list;

    public static Dhzs_5xFragment newInstance(List<ResultBean.DataBean> list) {
        Dhzs_5xFragment fragment = new Dhzs_5xFragment();
        fragment.setList(list);
        return fragment;
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

    //    private void getDefaultData() {
//        List<List<Integer>> list100 = Dhzs_5xUtil.list(100);
//        List<List<Integer>> list50 = new ArrayList<>();
//        List<List<Integer>> list30 = new ArrayList<>();
//        for (int i = 50; i < list100.size(); i++) {
//            list50.add(list100.get(i));
//            if (i >= 70) {
//                list30.add(list100.get(i));
//            }
//        }
//        List<List<Dhzs_5xBean>> allList100 = Dhzs_5xUtil.getShowData(list100);
//        List<List<Dhzs_5xBean>> allList50 = Dhzs_5xUtil.getShowData(list50);
//        List<List<Dhzs_5xBean>> allList30 = Dhzs_5xUtil.getShowData(list30);
//        bean100List.addAll(allList100);
//        bean50List.addAll(allList50);
//        bean30List.addAll(allList30);
//    }
    private void getDefaultData() {
        List<List<Integer>> list100 = getBaseList(list);
        List<List<Integer>> list50 = new ArrayList<>();
        List<List<Integer>> list30 = new ArrayList<>();

        List<String> nameList100 = new ArrayList<>();
        List<String> nameList50 = new ArrayList<>();
        List<String> nameList30 = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i <= 30) {
                    nameList30.add(list.get(i).periodsNum);
                }
                if (i <= 50) {
                    nameList50.add(list.get(i).periodsNum);
                }
                nameList100.add(list.get(i).periodsNum);
            }
        }

        if (list100.size() <= 50) {
            list50.addAll(list100);
        } else {
            for (int i = 0; i < 50; i++) {
                list50.add(list100.get(i));
            }
        }

        if (list100.size() <= 30) {
            list30.addAll(list100);
        } else {
            for (int i = 0; i < 30; i++) {
                list30.add(list100.get(i));
            }
        }
        List<List<Dhzs_5xBean>> allList100 = Dhzs_5xUtil.getShowData(list100, nameList100);
        List<List<Dhzs_5xBean>> allList50 = Dhzs_5xUtil.getShowData(list50, nameList50);
        List<List<Dhzs_5xBean>> allList30 = Dhzs_5xUtil.getShowData(list30, nameList30);
        bean100List.addAll(allList100);
        bean50List.addAll(allList50);
        bean30List.addAll(allList30);
    }

    @Override
    protected void show(int size) {
        mList.clear();
        switch (size) {
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

    @Override
    protected void refresh(List<ResultBean.DataBean> list) {

    }

    public List<ResultBean.DataBean> getList() {
        return list;
    }

    public void setList(List<ResultBean.DataBean> list) {
        this.list = list;
    }

}
