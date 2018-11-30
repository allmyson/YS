package com.ys.game.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ys.game.R;
import com.ys.game.adapter.CommonAdapter;
import com.ys.game.adapter.ViewHolder;
import com.ys.game.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename PlayDialog
 * @description -------------------------------------------------------
 * @date 2018/11/9 15:55
 */
public class ListDialog extends LhDialogFragment {
    private int mTheme;
    private int mStyle;
    private View mContentView;
    private ClickListener clickListener;
    private ListView lv;
    private MyAdapter myAdapter;
    private List<String> list;

    public static ListDialog newInstance(int style, int theme) {
        ListDialog pFragment = new ListDialog();
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
        mContentView = inflater.inflate(R.layout.fragment_list, null, false);
        lv = (ListView) mContentView.findViewById(R.id.lv_);
        if (list == null) {
            list = new ArrayList<>();
        }
        myAdapter = new MyAdapter(getActivity(), list, R.layout.item_problem);
        lv.setAdapter(myAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (clickListener != null) {
                    clickListener.click(myAdapter.getItem(position));
                }
                DialogUtil.removeDialog(getActivity());
            }
        });
        //去掉背景
        getDialog().getWindow().setBackgroundDrawable(new
                ColorDrawable(Color.TRANSPARENT));
        return mContentView;
    }

    public interface ClickListener {
        void click(String type);
    }


    public void setClickListener(ListDialog.ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    class MyAdapter extends CommonAdapter<String> {

        public MyAdapter(Context context, List<String> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(ViewHolder helper, String item, int position) {
            helper.setText(R.id.tv_, StringUtil.valueOf(item));
        }
    }
}
