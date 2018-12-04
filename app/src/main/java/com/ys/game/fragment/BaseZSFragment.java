package com.ys.game.fragment;

import com.ys.game.base.BaseFragment;
import com.ys.game.bean.ResultBean;
import com.ys.game.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename BaseZSFragment
 * @description -------------------------------------------------------
 * @date 2018/11/2 18:28
 */
public abstract class BaseZSFragment extends BaseFragment {
    protected abstract void show(int size);

    protected abstract void refresh(List<ResultBean.DataBean> list);

    protected List<ResultBean.DataBean> list;

    protected List<List<Integer>> getBaseList(List<ResultBean.DataBean> list) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null && !StringUtil.isBlank(list.get(i).lotteryNum)) {
                String[] ss = list.get(i).lotteryNum.split(",");
                List<Integer> list1 = new ArrayList<>();
                for (String s : ss) {
                    list1.add(StringUtil.StringToInt(s));
                }
                result.add(list1);
            }
        }
        return result;
    }


    protected List<List<Integer>> getHEList(List<ResultBean.DataBean> list) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null && !StringUtil.isBlank(list.get(i).lotteryNum)) {
                String[] ss = list.get(i).lotteryNum.split(",");
                List<Integer> list1 = new ArrayList<>();
                for (int j = 3; j < ss.length; j++) {
                    list1.add(StringUtil.StringToInt(ss[j]));
                }
                result.add(list1);
            }
        }
        return result;
    }

    protected List<Integer> getGList(List<ResultBean.DataBean> list) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null && !StringUtil.isBlank(list.get(i).lotteryNum)) {
                String[] ss = list.get(i).lotteryNum.split(",");
                result.add(StringUtil.StringToInt(ss[ss.length - 1]));
            }
        }
        return result;
    }
}
