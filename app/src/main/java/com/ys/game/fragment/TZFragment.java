package com.ys.game.fragment;

import android.os.CountDownTimer;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.rest.Response;
import com.ys.game.R;
import com.ys.game.activity.CqsscActivity;
import com.ys.game.adapter.TZAdapter;
import com.ys.game.base.BaseFragment;
import com.ys.game.bean.BaseBean;
import com.ys.game.bean.LoginBean;
import com.ys.game.bean.ResultBean;
import com.ys.game.bean.TZBean;
import com.ys.game.dialog.DialogUtil;
import com.ys.game.dialog.PlayDialog;
import com.ys.game.http.HttpListener;
import com.ys.game.sp.UserSP;
import com.ys.game.ui.AmountView;
import com.ys.game.util.DateUtil;
import com.ys.game.util.HttpUtil;
import com.ys.game.util.L;
import com.ys.game.util.StringUtil;
import com.ys.game.util.YS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lh
 * @version 1.0.0
 * @filename TZFragment
 * @description -------------------------------------------------------
 * @date 2018/11/7 17:11
 */
public class TZFragment extends BaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
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
    private String userId;

    private TextView lastQ, w, q, b, s, g, nextQ;
    private long nextTime = 0;
    private String nextQStr;
    private SwipeRefreshLayout srl;
    private LoginBean loginBean;
    @Override
    protected void init() {
        lastQ = getView(R.id.tv_lastQ);
        nextQ = getView(R.id.tv_nextQ);
        w = getView(R.id.tv_w);
        q = getView(R.id.tv_q);
        b = getView(R.id.tv_b);
        s = getView(R.id.tv_s);
        g = getView(R.id.tv_g);

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
        srl = (SwipeRefreshLayout) mView.findViewById(R.id.srl);
        srl.setOnRefreshListener(this);
        srl.setColorSchemeColors(getResources().getColor(R.color.main_color));
        tzLV.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                View firstView = view.getChildAt(firstVisibleItem);
                if (firstVisibleItem == 0 && (firstView == null || firstView.getTop() == 0)) {
                /*上滑到listView的顶部时，下拉刷新组件可见*/
                    srl.setEnabled(true);
                } else {
                /*不是listView的顶部时，下拉刷新组件不可见*/
                    srl.setEnabled(false);
                }
            }
        });
    }

    @Override
    protected void getData() {
        userId = UserSP.getUserId(mContext);
        loginBean = UserSP.getInfo(mContext);
        if(loginBean!=null&&loginBean.data!=null){
            setYue(StringUtil.StringToDouble(loginBean.data.balance));
        }

        getTZ();
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

    //五星复式
    private int getType(String type) {
        int code = 1000;
        if ("五星直选_复式".equals(type)) {
            code = 1000;
        } else if ("后二星组选_复式".equals(type)) {
            code = 1003;
        } else if ("定位胆_个位".equals(type)) {
            code = 1001;
        } else if ("后二星直选_大小单双".equals(type)) {
            code = 1004;
        } else if ("五星和值_和值大小单双".equals(type)) {
            code = 1002;
        }
        return code;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_zh:
//                show(mAmountView.getCurrentValue() + "");
                break;
            case R.id.btn_tz:
                if (mAdapter.canTZ()) {
//                    show("投注数量" + mAdapter.getTZData().size());
                    tz();
//                    StringBuilder toast = new StringBuilder();
//                    for(int i = 0;i<mAdapter.getTZData().size();i++){
//                        toast.append(mAdapter.getTZData().get(i)+"\n");
//                    }
//                    show(toast.toString());
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


    private void tz() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("periodsNum", nextQStr);//期数
        map.put("gameCode", ((CqsscActivity) getActivity()).getType());//游戏类型
        map.put("lotteryTypeCode", getType(typeStr));
        map.put("complantTypeCode", 1000);//手动下注
        List<String> tzList = mAdapter.getTZData();
        List<Map<String, Object>> betList = new ArrayList<>();
        Map<String, Object> map1 = null;
        for (String str : tzList) {
            map1 = new HashMap<>();
            map1.put("betsNum", str);//投注内容
            map1.put("payMoney", singlePrice);//单价
            map1.put("times", bei);//倍数
            betList.add(map1);
        }
        map.put("betDetail", betList);
        String json = new Gson().toJson(map);
        HttpUtil.tz(mContext, json, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                BaseBean baseBean = new Gson().fromJson(response.get(), BaseBean.class);
                if (baseBean != null && YS.SUCCESE.equals(baseBean.code)) {
                    show("投注成功！");
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }

    private void getTZ() {
        HttpUtil.getKJResult(mContext, ((CqsscActivity) getActivity()).getType(), 1, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                ResultBean resultBean = new Gson().fromJson(response.get(), ResultBean.class);
                if (resultBean != null && YS.SUCCESE.equals(resultBean.code) && resultBean.data != null && resultBean
                        .data.size() > 0) {
                    if (resultBean.data.get(0).periodsNum.length() > 8) {
                        lastQ.setText(resultBean.data.get(0).periodsNum.substring(8) + "期");
                    } else {
                        lastQ.setText(resultBean.data.get(0).periodsNum + "期");
                    }
                    String[] ss = resultBean.data.get(0).lotteryNum.split(",");
                    if (ss != null && ss.length == 5) {
                        w.setText(ss[0]);
                        q.setText(ss[1]);
                        b.setText(ss[2]);
                        s.setText(ss[3]);
                        g.setText(ss[4]);
                    }
                    long lastTime = DateUtil.changeTimeToLong(resultBean.data.get(0).lotteryTime);
                    if(((CqsscActivity) getActivity()).getType()==YS.TYPE_CQSSC) {
                        if (DateUtil.isOpen()) {
                            if (DateUtil.isTen()) {
                                nextTime = lastTime + 10 * 60 * 1000;
                            } else if (DateUtil.isFive()) {
                                nextTime = lastTime + 5 * 60 * 1000;
                            }
                            nextQStr = "" + (StringUtil.StringToLong(resultBean.data.get(0).periodsNum) + 1);
                            nextQ.setText("第" + nextQStr + "期还剩" +
                                    DateUtil.getRemainTime2(nextTime));
                            start();
                        } else {
                            nextQ.setText("02:00-10:00之间不开奖");
                        }
                    }else if(((CqsscActivity) getActivity()).getType()==YS.TYPE_TXFFC){
                        nextTime = lastTime+1*60*1000;
                        nextQStr = "" + (StringUtil.StringToLong(resultBean.data.get(0).periodsNum) + 1);
                        nextQ.setText("第" + nextQStr + "期还剩" +
                                DateUtil.getRemainTime2(nextTime));
                        start();
                    }
                    srl.setRefreshing(false);
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                srl.setRefreshing(false);
            }
        });
    }

    private CountDownTimer countDownTimer;

    private void initTimer() {
        countDownTimer = new CountDownTimer(600 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                nextQ.setText("第" + (nextQStr) + "期还剩" +
                        DateUtil.getRemainTime2(nextTime));
                if(nextTime<=System.currentTimeMillis()){
                    L.e("倒计时完成，获取下一轮数据");
                    getTZ();
                }
            }

            @Override
            public void onFinish() {
//                show("倒计时完成");
            }
        };
    }

    /**
     * 开启倒计时
     */
    public void start() {
        long nowTime = System.currentTimeMillis();
        if (nextTime <= nowTime) {
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            return;
        }
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        initTimer();
        countDownTimer.start();
    }


    /**
     * destroy
     */
    public void cancel() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cancel();
    }

    @Override
    public void onRefresh() {
        getTZ();
    }
}
