package com.ys.game.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.rest.Response;
import com.ys.game.R;
import com.ys.game.adapter.CommonAdapter;
import com.ys.game.adapter.ViewHolder;
import com.ys.game.bean.ResultBean;
import com.ys.game.http.HttpListener;
import com.ys.game.util.DateUtil;
import com.ys.game.util.HttpUtil;
import com.ys.game.util.YS;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename List2Dialog
 * @description -------------------------------------------------------
 * @date 2018/12/12 15:22
 */
public class List2Dialog extends LhDialogFragment {
    private int mTheme;
    private int mStyle;
    private View mContentView;
    private ListView lv;
    private MyAdapter myAdapter;
    private List<ResultBean.DataBean> list;
    private int type;

    public static List2Dialog newInstance(int style, int theme) {
        List2Dialog pFragment = new List2Dialog();
        Bundle args = new Bundle();
        args.putInt("style", style);
        args.putInt("theme", theme);
        pFragment.setArguments(args);
        return pFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCancelable(true);// 设置点击屏幕Dialog不消失
        mStyle = getArguments().getInt("style");
        mTheme = getArguments().getInt("theme");
        setStyle(mStyle, mTheme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.fragment_list2, null, false);
        lv = (ListView) mContentView.findViewById(R.id.lv_);
        if (list == null) {
            list = new ArrayList<>();
        }
        myAdapter = new MyAdapter(getActivity(), list, R.layout.item_result);
        lv.setAdapter(myAdapter);
        //去掉背景
        getDialog().getWindow().setBackgroundDrawable(new
                ColorDrawable(Color.TRANSPARENT));
//        Dialog dialog = getDialog();
//        if (dialog != null) {
//            DisplayMetrics dm = new DisplayMetrics();
//            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
//            attributes.gravity = Gravity.TOP | Gravity.RIGHT;//对齐方式
//            attributes.y = DensityUtil.dp2px(getContext(), 80);//具体头部距离
//            dialog.getWindow().setAttributes(attributes);
//            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.8), ViewGroup.LayoutParams
//                    .WRAP_CONTENT);
//        }


        getResult();
        return mContentView;
    }


    public List<ResultBean.DataBean> getList() {
        return list;
    }

    public void setList(List<ResultBean.DataBean> list) {
        this.list = list;
    }

    class MyAdapter extends CommonAdapter<ResultBean.DataBean> {

        public MyAdapter(Context context, List<ResultBean.DataBean> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(ViewHolder helper, ResultBean.DataBean item, int position) {
            helper.setText(R.id.tv_name, item.periodsNum.substring(4));
            helper.setText(R.id.tv_result, item.lotteryNum.replaceAll(",", "\t"));
            helper.setText(R.id.tv_time, DateUtil.changeTimeToHMS(item.lotteryTime));
        }
    }


    private void getResult() {
        HttpUtil.getKJResult(getActivity(), type, 50, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                list.clear();
                ResultBean resultBean = new Gson().fromJson(response.get(), ResultBean.class);
                if (resultBean != null && YS.SUCCESE.equals(resultBean.code) && resultBean.data != null && resultBean
                        .data.size() > 0) {
                    list.addAll(resultBean.data);
                }
                myAdapter.refresh(list);
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
