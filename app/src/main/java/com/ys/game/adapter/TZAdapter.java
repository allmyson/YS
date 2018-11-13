package com.ys.game.adapter;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.ys.game.R;
import com.ys.game.bean.TZBean;
import com.ys.game.sf.ZhUtil;
import com.ys.game.ui.MyGridView;
import com.ys.game.util.StringUtil;
import com.ys.game.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename TZAdapter
 * @description -------------------------------------------------------
 * @date 2018/11/8 18:41
 */
public class TZAdapter extends CommonAdapter<TZBean> {
    private List<List<Boolean>> list;
    private ChangeListener changeListener;
    private int numberSize;

    private void initData() {
        list = new ArrayList<>();
        if (mDatas != null && mDatas.size() > 0) {
            if ("后二星直选_大小单双".equals(mDatas.get(0).typeName) || "五星和值_和值大小单双".equals(mDatas.get(0).typeName)) {
                numberSize = 4;
            } else {
                numberSize = 10;
            }
            for (int i = 0; i < mDatas.size(); i++) {
                List<Boolean> ll = new ArrayList<>();
                for (int j = 0; j < numberSize; j++) {
                    ll.add(false);
                }
                list.add(ll);
            }
        }
    }

    public TZAdapter(Context context, List<TZBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
        initData();
    }

    @Override
    public void refresh(List<TZBean> mDatas) {
        initData();
        super.refresh(mDatas);
    }

    @Override
    public void convert(ViewHolder helper, TZBean item, final int position) {
        if ("后二星直选_大小单双".equals(item.typeName) || "五星和值_和值大小单双".equals(item.typeName)) {
            helper.getView(R.id.ll_choose).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.ll_choose).setVisibility(View.VISIBLE);
        }
        helper.setText(R.id.tv_name, StringUtil.valueOf(item.name));
        MyGridView gridView = helper.getView(R.id.gv_);
        final NumberAdapter numberAdapter = new NumberAdapter(mContext, getNumberList(item.typeName), R.layout
                .item_gv_number);
        numberAdapter.setList(list.get(position));
        gridView.setAdapter(numberAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int p, long id) {
                if (list.get(position).get(p)) {
                    list.get(position).set(p, false);
                    numberAdapter.cancelChooseOne(p);
                } else {
                    numberAdapter.chooseOne(p);
                    list.get(position).set(p, true);
                }
                callback();
            }
        });
        helper.getView(R.id.rl_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberAdapter.chooseAll();
                for (int i = 0; i < 10; i++) {
                    list.get(position).set(i, true);
                }
                callback();
            }
        });
        helper.getView(R.id.rl_big).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberAdapter.chooseBig();
                for (int i = 0; i < 10; i++) {
                    if (i >= 5) {
                        list.get(position).set(i, true);
                    } else {
                        list.get(position).set(i, false);
                    }
                }
                callback();
            }
        });
        helper.getView(R.id.rl_small).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberAdapter.chooseSmall();
                for (int i = 0; i < 10; i++) {
                    if (i < 5) {
                        list.get(position).set(i, true);
                    } else {
                        list.get(position).set(i, false);
                    }
                }
                callback();
            }
        });
        helper.getView(R.id.rl_single).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberAdapter.chooseSingle();
                for (int i = 0; i < 10; i++) {
                    if (i % 2 != 0) {
                        list.get(position).set(i, true);
                    } else {
                        list.get(position).set(i, false);
                    }
                }
                callback();
            }
        });
        helper.getView(R.id.rl_double).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberAdapter.chooseDouble();
                for (int i = 0; i < 10; i++) {
                    if (i % 2 == 0) {
                        list.get(position).set(i, true);
                    } else {
                        list.get(position).set(i, false);
                    }
                }
                callback();
            }
        });
        helper.getView(R.id.rl_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberAdapter.clear();
                for (int i = 0; i < 10; i++) {
                    list.get(position).set(i, false);
                }
                if (changeListener != null) {
                    changeListener.clear();
                }
            }
        });
    }

    private List<String> getNumberList(String typeName) {
        List<String> numberList = new ArrayList<>();
        if ("后二星直选_大小单双".equals(typeName) || "五星和值_和值大小单双".equals(typeName)) {
            numberList.add("大");
            numberList.add("小");
            numberList.add("单");
            numberList.add("双");
        } else {
            for (int i = 0; i < 10; i++) {
                numberList.add(String.valueOf(i));
            }
        }
        return numberList;
    }

    //是否可以投注
    public boolean canTZ() {
        List<String> ll = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            boolean bb = false;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < list.get(i).size(); j++) {
                if (list.get(i).get(j)) {
                    sb.append(1);
                }
            }
            ll.add(sb.toString());
        }
        for (int i = 0; i < ll.size(); i++) {
            if (!"组选".equals(mDatas.get(i).name)) {
                if (ll.get(i).length() < 1) {
                    ToastUtil.show(mContext, "请选择" + mDatas.get(i).name + "号码");
                    return false;
                }
            } else {
                if (ll.get(i).length() < 2) {
                    ToastUtil.show(mContext, mDatas.get(i).name + "至少选择两位");
                    return false;
                }
            }
        }
        return true;
    }

    //得到投注值的组合
    public List<String> getTZData() {
        List<List<Integer>> aa = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            List<Integer> zz = new ArrayList<>();
            for (int j = 0; j < list.get(i).size(); j++) {
                if (list.get(i).get(j)) {
                    zz.add(j);
                }
            }
            aa.add(zz);
        }
        List<String> result = new ArrayList<>();
        if (mDatas.size() == 5) {
            for (int i = 0; i < aa.get(0).size(); i++) {
                for (int j = 0; j < aa.get(1).size(); j++) {
                    for (int k = 0; k < aa.get(2).size(); k++) {
                        for (int m = 0; m < aa.get(3).size(); m++) {
                            for (int n = 0; n < aa.get(4).size(); n++) {
                                StringBuilder sb = new StringBuilder();
                                sb.append(aa.get(0).get(i));
                                sb.append(aa.get(1).get(j));
                                sb.append(aa.get(2).get(k));
                                sb.append(aa.get(3).get(m));
                                sb.append(aa.get(4).get(n));
                                result.add(sb.toString());
                            }
                        }
                    }
                }
            }
        } else if (mDatas.size() == 1) {
            if ("定位胆_个位".equals(mDatas.get(0).typeName)) {
                for (int i = 0; i < aa.get(0).size(); i++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(aa.get(0).get(i));
                    result.add(sb.toString());
                }
            } else if ("后二星组选_复式".equals(mDatas.get(0).typeName)) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < aa.get(0).size(); i++) {
                    sb.append(aa.get(0).get(i));
                }
                List<String> cr = ZhUtil.getCombinationResult(2, ZhUtil.stringFilter(sb.toString()));
                result.addAll(cr);
            } else if ("五星和值_和值大小单双".equals(mDatas.get(0).typeName)) {
                for (int i = 0; i < aa.get(0).size(); i++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(aa.get(0).get(i));
                    result.add(sb.toString());
                }
            }
        } else if (mDatas.size() == 2) {
            for (int i = 0; i < aa.get(0).size(); i++) {
                for (int j = 0; j < aa.get(1).size(); j++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(aa.get(0).get(i));
                    sb.append(aa.get(1).get(j));
                    result.add(sb.toString());
                }
            }
        }
        return result;
    }

    public interface ChangeListener {
        void getData(List<String> result);

        void clear();//归0操作
    }

    public ChangeListener getChangeListener() {
        return changeListener;
    }

    public void setChangeListener(ChangeListener changeListener) {
        this.changeListener = changeListener;
    }

    public void callback() {
        if (changeListener != null && canTZ()) {
            changeListener.getData(getTZData());
        }
    }
}
