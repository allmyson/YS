package com.ys.game.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ys.game.R;
import com.ys.game.util.StringUtil;

/**
 * @author lh
 * @version 1.0.0
 * @filename TipDialog
 * @description -------------------------------------------------------
 * @date 2018/11/9 15:08
 */
public class TipDialog extends LhDialogFragment {
    private int mTheme;
    private int mStyle;
    private View mContentView;
    private String content;
    private TextView contentTV;
    public static TipDialog newInstance(int style, int theme) {
        TipDialog pFragment = new TipDialog();
        Bundle args = new Bundle();
        args.putInt("style", style);
        args.putInt("theme", theme);
        pFragment.setArguments(args);
        return pFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCancelable(true);// 设置点击屏幕Dialog消失
        mStyle = getArguments().getInt("style");
        mTheme = getArguments().getInt("theme");
        setStyle(mStyle, mTheme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.fragment_tip, null, false);
        contentTV = (TextView) mContentView.findViewById(R.id.tv_content);
        contentTV.setText(StringUtil.valueOf(content));
        //去掉背景
        getDialog().getWindow().setBackgroundDrawable(new
                ColorDrawable(Color.TRANSPARENT));
        return mContentView;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
