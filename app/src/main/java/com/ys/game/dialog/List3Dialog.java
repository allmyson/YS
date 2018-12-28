package com.ys.game.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ys.game.R;
import com.ys.game.adapter.CommonAdapter;
import com.ys.game.adapter.ViewHolder;
import com.ys.game.util.DensityUtil;
import com.ys.game.util.YS;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename List3Dialog
 * @description -------------------------------------------------------
 * @date 2018/12/12 16:06
 */
public class List3Dialog extends LhDialogFragment {
    private int mTheme;
    private int mStyle;
    private View mContentView;
    private ListView lv;
    private MyAdapter myAdapter;
    private List<String> list;
    private int type;
    private ClickListener clickListener;

    public static List3Dialog newInstance(int style, int theme) {
        List3Dialog pFragment = new List3Dialog();
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
        mContentView = inflater.inflate(R.layout.fragment_list3, null, false);
        lv = (ListView) mContentView.findViewById(R.id.lv_);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add("重庆时时彩");
        list.add("腾讯分分彩");
        list.add("最后的胜利者");
        myAdapter = new MyAdapter(getActivity(), list, R.layout.item_game_list);
        lv.setAdapter(myAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0 && type == YS.TYPE_CQSSC) {
                    DialogUtil.removeDialog(getContext());
                    return;
                }
                if (position == 1 && type == YS.TYPE_TXFFC) {
                    DialogUtil.removeDialog(getContext());
                    return;
                }
                if (position == 2 && type == YS.TYPE_ZHDSLZ) {
                    DialogUtil.removeDialog(getContext());
                    return;
                }

                if (position == 0) {
                    if (clickListener != null) {
                        clickListener.click(YS.TYPE_CQSSC);
                    }
                } else if (position == 1) {
                    if (clickListener != null) {
                        clickListener.click(YS.TYPE_TXFFC);
                    }
                } else {
                    if (clickListener != null) {
                        clickListener.click(YS.TYPE_ZHDSLZ);
                    }
                }
                DialogUtil.removeDialog(getContext());
            }
        });
        //去掉背景
        getDialog().getWindow().setBackgroundDrawable(new
                ColorDrawable(Color.TRANSPARENT));
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            attributes.gravity = Gravity.TOP | Gravity.RIGHT;//对齐方式
//            attributes.width = DensityUtil.dp2px(getContext(),100);
            attributes.x = DensityUtil.dp2px(getContext(), 10);
            attributes.y = DensityUtil.dp2px(getContext(), 50);//具体头部距离
            dialog.getWindow().setAttributes(attributes);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.5), ViewGroup.LayoutParams
                    .WRAP_CONTENT);
        }


        return mContentView;
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
            helper.setText(R.id.tv_, item);
            if ("重庆时时彩".equals(item)) {
                helper.setImageResource(R.id.iv_, R.mipmap.ic_cqssc);
            } else if ("腾讯分分彩".equals(item)) {
                helper.setImageResource(R.id.iv_, R.mipmap.ic_txffc);
            } else if ("最后的胜利者".equals(item)) {
                helper.setImageResource(R.id.iv_, R.mipmap.ic_slz);
            }
        }
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public interface ClickListener {
        void click(int type);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }
}

