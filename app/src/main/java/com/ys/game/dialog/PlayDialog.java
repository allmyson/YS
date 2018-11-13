package com.ys.game.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ys.game.R;

/**
 * @author lh
 * @version 1.0.0
 * @filename PlayDialog
 * @description -------------------------------------------------------
 * @date 2018/11/9 15:55
 */
public class PlayDialog extends LhDialogFragment {
    private int mTheme;
    private int mStyle;
    private View mContentView;
    private Button danhgwBtn;//单号走势个位
    private Button dh_wxBtn;//多号五星
    private Button dh_heBtn;//多号后二
    private Button dxds_sgBtn;//大小单双是个
    private Button wxhz_dxdsBtn;//五星和值大小单双
    private String type;
    private PlayDialog.ClickListener clickListener;
    //五星直选_复式  定位胆_个位  五星和值_和值大小单双  后二星组选_复式 后二星直选_大小单双

    public static PlayDialog newInstance(int style, int theme) {
        PlayDialog pFragment = new PlayDialog();
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
        mContentView = inflater.inflate(R.layout.fragment_play, null, false);
        danhgwBtn = (Button) mContentView.findViewById(R.id.btn_danh_gw);
        dh_wxBtn = (Button) mContentView.findViewById(R.id.btn_dh_wx);
        dh_heBtn = (Button) mContentView.findViewById(R.id.btn_dh_he);
        dxds_sgBtn = (Button) mContentView.findViewById(R.id.btn_dxds_sg);
        wxhz_dxdsBtn = (Button) mContentView.findViewById(R.id.btn_wxhz_dxds);
        danhgwBtn.setOnClickListener(this);
        dh_wxBtn.setOnClickListener(this);
        dh_heBtn.setOnClickListener(this);
        dxds_sgBtn.setOnClickListener(this);
        wxhz_dxdsBtn.setOnClickListener(this);
        initButton();
        //去掉背景
        getDialog().getWindow().setBackgroundDrawable(new
                ColorDrawable(Color.TRANSPARENT));
        return mContentView;
    }

    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        switch (arg0.getId()){
            case R.id.btn_danh_gw:
                type="五星直选_复式";
                initButton();
                if(clickListener!=null){
                    clickListener.click(type);
                }
                DialogUtil.removeDialog(getActivity());
                break;
            case R.id.btn_dh_wx:
                type="定位胆_个位";
                initButton();
                if(clickListener!=null){
                    clickListener.click(type);
                }
                DialogUtil.removeDialog(getActivity());
                break;
            case R.id.btn_dh_he:
                type="五星和值_和值大小单双";
                initButton();
                if(clickListener!=null){
                    clickListener.click(type);
                }
                DialogUtil.removeDialog(getActivity());
                break;
            case R.id.btn_dxds_sg:
                type="后二星组选_复式";
                initButton();
                if(clickListener!=null){
                    clickListener.click(type);
                }
                DialogUtil.removeDialog(getActivity());
                break;
            case R.id.btn_wxhz_dxds:
                type="后二星直选_大小单双";
                initButton();
                if(clickListener!=null){
                    clickListener.click(type);
                }
                DialogUtil.removeDialog(getActivity());
                break;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public interface ClickListener{
        void click(String type);
    }

    public PlayDialog.ClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(PlayDialog.ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    private void initButton(){
        if("五星直选_复式".equals(type)){
            danhgwBtn.setBackgroundResource(R.drawable.radiobutton_background_checked);
            danhgwBtn.setTextColor(Color.WHITE);
            dh_wxBtn.setBackgroundResource(R.drawable.radiobutton_background_unchecked);
            dh_wxBtn.setTextColor(getResources().getColor(R.color.main_color));
            dh_heBtn.setBackgroundResource(R.drawable.radiobutton_background_unchecked);
            dh_heBtn.setTextColor(getResources().getColor(R.color.main_color));
            dxds_sgBtn.setBackgroundResource(R.drawable.radiobutton_background_unchecked);
            dxds_sgBtn.setTextColor(getResources().getColor(R.color.main_color));
            wxhz_dxdsBtn.setBackgroundResource(R.drawable.radiobutton_background_unchecked);
            wxhz_dxdsBtn.setTextColor(getResources().getColor(R.color.main_color));
        }else if("定位胆_个位".equals(type)){
            danhgwBtn.setBackgroundResource(R.drawable.radiobutton_background_unchecked);
            danhgwBtn.setTextColor(getResources().getColor(R.color.main_color));
            dh_wxBtn.setBackgroundResource(R.drawable.radiobutton_background_checked);
            dh_wxBtn.setTextColor(Color.WHITE);
            dh_heBtn.setBackgroundResource(R.drawable.radiobutton_background_unchecked);
            dh_heBtn.setTextColor(getResources().getColor(R.color.main_color));
            dxds_sgBtn.setBackgroundResource(R.drawable.radiobutton_background_unchecked);
            dxds_sgBtn.setTextColor(getResources().getColor(R.color.main_color));
            wxhz_dxdsBtn.setBackgroundResource(R.drawable.radiobutton_background_unchecked);
            wxhz_dxdsBtn.setTextColor(getResources().getColor(R.color.main_color));
        }else if("五星和值_和值大小单双".equals(type)){
            danhgwBtn.setBackgroundResource(R.drawable.radiobutton_background_unchecked);
            danhgwBtn.setTextColor(getResources().getColor(R.color.main_color));
            dh_wxBtn.setBackgroundResource(R.drawable.radiobutton_background_unchecked);
            dh_wxBtn.setTextColor(getResources().getColor(R.color.main_color));
            dh_heBtn.setBackgroundResource(R.drawable.radiobutton_background_checked);
            dh_heBtn.setTextColor(Color.WHITE);
            dxds_sgBtn.setBackgroundResource(R.drawable.radiobutton_background_unchecked);
            dxds_sgBtn.setTextColor(getResources().getColor(R.color.main_color));
            wxhz_dxdsBtn.setBackgroundResource(R.drawable.radiobutton_background_unchecked);
            wxhz_dxdsBtn.setTextColor(getResources().getColor(R.color.main_color));
        }else if("后二星组选_复式".equals(type)){
            danhgwBtn.setBackgroundResource(R.drawable.radiobutton_background_unchecked);
            danhgwBtn.setTextColor(getResources().getColor(R.color.main_color));
            dh_wxBtn.setBackgroundResource(R.drawable.radiobutton_background_unchecked);
            dh_wxBtn.setTextColor(getResources().getColor(R.color.main_color));
            dh_heBtn.setBackgroundResource(R.drawable.radiobutton_background_unchecked);
            dh_heBtn.setTextColor(getResources().getColor(R.color.main_color));
            dxds_sgBtn.setBackgroundResource(R.drawable.radiobutton_background_checked);
            dxds_sgBtn.setTextColor(Color.WHITE);
            wxhz_dxdsBtn.setBackgroundResource(R.drawable.radiobutton_background_unchecked);
            wxhz_dxdsBtn.setTextColor(getResources().getColor(R.color.main_color));
        }else if("后二星直选_大小单双".equals(type)){
            danhgwBtn.setBackgroundResource(R.drawable.radiobutton_background_unchecked);
            danhgwBtn.setTextColor(getResources().getColor(R.color.main_color));
            dh_wxBtn.setBackgroundResource(R.drawable.radiobutton_background_unchecked);
            dh_wxBtn.setTextColor(getResources().getColor(R.color.main_color));
            dh_heBtn.setBackgroundResource(R.drawable.radiobutton_background_unchecked);
            dh_heBtn.setTextColor(getResources().getColor(R.color.main_color));
            dxds_sgBtn.setBackgroundResource(R.drawable.radiobutton_background_unchecked);
            dxds_sgBtn.setTextColor(getResources().getColor(R.color.main_color));
            wxhz_dxdsBtn.setBackgroundResource(R.drawable.radiobutton_background_checked);
            wxhz_dxdsBtn.setTextColor(Color.WHITE);
        }
    }
}
