package com.ys.game.fragment;

import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ys.game.R;
import com.ys.game.adapter.TZAdapter;
import com.ys.game.base.BaseFragment;
import com.ys.game.bean.TZBean;
import com.ys.game.dialog.DialogUtil;
import com.ys.game.dialog.PlayDialog;
import com.ys.game.ui.AmountView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename TZFragment
 * @description -------------------------------------------------------
 * @date 2018/11/7 17:11
 */
public class TZFragment extends BaseFragment implements View.OnClickListener {
    private ListView tzLV;
    private List<TZBean> list;
    private TZAdapter mAdapter;
    private Button zhBtn, tzBtn;
    private AmountView mAmountView;
    private TextView moneyZhuTV, yueTV, buyMoneyTV, typeTV;
    private int singlePrice = 1;//单价一元
    private int bei = 1;
    private int zhuNum = 0;
    private String text = "开奖时间:10点至22点,10分钟一期;22点至凌晨2点,5分钟一期;每日期数:120期。\n\n" +
            "玩法:从万位、千位、百位、十位、个位中选择一个5位数号码组成一注,所选号码与开奖号码全部相同,且顺序一致,即为中奖。\n\n" +
            "示例:投注方案:23456;开奖号码:23456";
    private String typeStr = "五星直选_复式";

    @Override
    protected void init() {
        moneyZhuTV = getView(R.id.tv_money_zhu);
        yueTV = getView(R.id.tv_yue);
        buyMoneyTV = getView(R.id.tv_buyMoney);
        typeTV = getView(R.id.tv_type);
        typeTV.setOnClickListener(this);
        tzLV = getView(R.id.lv_tz);
        list = new ArrayList<>();
        list.addAll(getWXFSList(typeStr));
        mAdapter = new TZAdapter(mContext, list, R.layout.item_tz);
        tzLV.setAdapter(mAdapter);
        zhBtn = getView(R.id.btn_zh);
        tzBtn = getView(R.id.btn_tz);
        zhBtn.setOnClickListener(this);
        tzBtn.setOnClickListener(this);
        mAmountView = getView(R.id.amount_view);
        mAmountView.setGoods_storage(50);
        mAmountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                bei = amount;
                setBuyMoney(singlePrice * zhuNum * bei);
            }
        });
        setMoneyZhu(singlePrice, 0);
        setYue(0);
        setBuyMoney(0);
        mAdapter.setChangeListener(new TZAdapter.ChangeListener() {
            @Override
            public void getData(List<String> result) {
                zhuNum = result.size();
                setMoneyZhu(singlePrice, zhuNum);
                setBuyMoney(singlePrice * zhuNum * bei);
            }

            @Override
            public void clear() {
                setMoneyZhu(singlePrice, 0);
                setBuyMoney(0);
            }
        });
        getView(R.id.rl_tip).setOnClickListener(this);
    }

    @Override
    protected void getData() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_tz;
    }

    //五星复式
    private List<TZBean> getWXFSList(String type) {
        List<TZBean> list = new ArrayList<>();
        if ("五星直选_复式".equals(type)) {
            list.add(new TZBean("五星直选_复式", "万位"));
            list.add(new TZBean("五星直选_复式", "千位"));
            list.add(new TZBean("五星直选_复式", "百位"));
            list.add(new TZBean("五星直选_复式", "十位"));
            list.add(new TZBean("五星直选_复式", "个位"));
        } else if ("后二星组选_复式".equals(type)) {
            list.add(new TZBean("后二星组选_复式", "组选"));
        } else if ("定位胆_个位".equals(type)) {
            list.add(new TZBean("定位胆_个位", "个位"));
        } else if ("后二星直选_大小单双".equals(type)) {
            list.add(new TZBean("后二星直选_大小单双", "十位"));
            list.add(new TZBean("后二星直选_大小单双", "个位"));
        } else if ("五星和值_和值大小单双".equals(type)) {
            list.add(new TZBean("五星和值_和值大小单双", "和值"));
        }
        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_zh:
//                show(mAmountView.getCurrentValue() + "");
                break;
            case R.id.btn_tz:
                if (mAdapter.canTZ()) {
                    show("投注数量" + mAdapter.getTZData().size());
                }
                break;
            case R.id.rl_tip:
                DialogUtil.showTip(mContext, text);
                break;
            case R.id.tv_type:
                DialogUtil.showPlay(mContext, typeTV.getText().toString(), new PlayDialog.ClickListener() {
                    @Override
                    public void click(String type) {
                        typeStr = type;
                        typeTV.setText(type);
                        list.clear();
                        list.addAll(getWXFSList(typeStr));
                        mAdapter.refresh(list);
                    }
                });
                break;
        }
    }

    private void setMoneyZhu(int money, int zhu) {
        String moneyZhu = String.format("<font color=\"#fc6a44\">%s</font>元*<font color=\"#fc6a44\">%s</font>注",
                money, zhu);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            moneyZhuTV.setText(Html.fromHtml(moneyZhu, Html.FROM_HTML_MODE_LEGACY));
        } else {
            moneyZhuTV.setText(Html.fromHtml(moneyZhu));
        }
    }

    private void setYue(double money) {
        String yue = String.format("可用余额:<font color=\"#fc6a44\">%s</font>元", money);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            yueTV.setText(Html.fromHtml(yue, Html.FROM_HTML_MODE_LEGACY));
        } else {
            yueTV.setText(Html.fromHtml(yue));
        }
    }

    private void setBuyMoney(double money) {
        String buyMoney = String.format("购买需支付:<font color=\"#fc6a44\">%s</font>元", money);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            buyMoneyTV.setText(Html.fromHtml(buyMoney, Html.FROM_HTML_MODE_LEGACY));
        } else {
            buyMoneyTV.setText(Html.fromHtml(buyMoney));
        }
    }
}
