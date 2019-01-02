package com.ys.game.fragment;

import android.os.Build;
import android.os.CountDownTimer;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.rest.Response;
import com.ys.game.R;
import com.ys.game.adapter.MySNAdapter;
import com.ys.game.adapter.SnMsgAdapter;
import com.ys.game.base.BaseFragment;
import com.ys.game.bean.BaseBean;
import com.ys.game.bean.WinnerBean;
import com.ys.game.bean.WinnerInfo;
import com.ys.game.dialog.DialogUtil;
import com.ys.game.http.HttpListener;
import com.ys.game.sp.UserSP;
import com.ys.game.ui.CircleProgressBar;
import com.ys.game.ui.MyGridView;
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
 * @filename WinnerTZFragment
 * @description -------------------------------------------------------
 * @date 2018/11/23 15:07
 */
public class WinnerTZFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, View
        .OnClickListener {
    private CircleProgressBar totalCPB, priceCPB;
    private ProgressBar fhPB;
    private TextView fhTV;
    private MyGridView myGridView;
    private MySNAdapter mySNAdapter;
    private List<String> mySNList;
    private LinearLayout lineLL;
    private ListView msgLV;
    private SnMsgAdapter snMsgAdapter;
    private List<String> msgList;
    private String userId;
    private TextView yueTV, buyMoneyTV;
    private SwipeRefreshLayout srl;
    private RelativeLayout tipRL;
    private String text = "1，每局游戏将间隔 15 分钟 \n" +
            "2，游戏每局开始以 10 个游戏币出售 SN，即第一个购买者购买第一个 SN 需 10 个币并记录为 SN001 \n" +
            "3，第二个 SN 的购买者则需比上一个购买者多花一个币，即第二个需花 11 个币购买，记录为 SN002，且将拿出这 11 个币的 10%作为前面玩家的红利平均分配给前面所有玩家，拿出 11 个币的 " +
            "1%作为代理玩家的返点奖励，以此类推，直到游戏结束 \n" +
            "4，每个玩家每次只能买一个 SN,一局中购买次数不限 \n" +
            "5，游戏以 12 小时作为结束倒计时，每买一个 SN，倒计时间增加 10 秒，游戏时间结束，最后一个卖到 SN 作为游戏最大胜利者将获得奖池金额的 30% \n" +
            "6，若 SN 卖到 500 个币时，游戏结束，即当玩家花 500 币买到最后一个 SN，游戏结束，该玩家将获得奖池金额的 30% \n" +
            "7，作为激励，将诞生一个随机大奖，金额为奖池的 15%，奖励规则是游戏结束后所有已购买的 SN 号中随机抽取一个做为中奖号 \n" +
            "8，所有奖金（个人返利、最后胜利者、激励奖）将会在该期游戏结束后，系统自动结算后统一发放 ";
    private Button tzBtn;
    private String currentQ;
    private String currentSNPrice;
    private TextView tipTV, timeTV;
    private long currentTimeByServer = 0;//服务器返回的游戏期数的结束时间或者下一期的开始时间

    @Override
    protected void init() {
        tipTV = getView(R.id.tv_tip);
        timeTV = getView(R.id.tv_time);
        tzBtn = getView(R.id.btn_tz);
        tzBtn.setOnClickListener(this);
        tipRL = getView(R.id.rl_tip);
        tipRL.setOnClickListener(this);
        srl = (SwipeRefreshLayout) mView.findViewById(R.id.srl);
        srl.setOnRefreshListener(this);
        srl.setColorSchemeColors(getResources().getColor(R.color.main_color));
        buyMoneyTV = getView(R.id.tv_buyMoney);
        yueTV = getView(R.id.tv_yue);
        msgLV = getView(R.id.lv_msg);
        lineLL = getView(R.id.ll_line);
        totalCPB = getView(R.id.cpb_total);
        priceCPB = getView(R.id.cpb_price);
//        totalCPB.setPercent("29348");
//        totalCPB.setProgress(80, true);
//        priceCPB.setPercent("125");
//        priceCPB.setProgress(50, true);
        fhPB = getView(R.id.progesss_fh);
        fhTV = getView(R.id.tv_fh);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            fhPB.setProgress(80, true);
        } else {
            fhPB.setProgress(80);
        }
        myGridView = getView(R.id.mgv_sn);
        mySNList = new ArrayList<>();
        mySNAdapter = new MySNAdapter(mContext, mySNList, R.layout.item_my_sn);
        myGridView.setAdapter(mySNAdapter);

        ViewTreeObserver vto = myGridView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                myGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                L.e("mygridview高度" + myGridView.getHeight());
                FrameLayout.LayoutParams linearParams = (FrameLayout.LayoutParams) lineLL.getLayoutParams();
                linearParams.height = myGridView.getHeight();
                lineLL.setLayoutParams(linearParams);
            }
        });
        msgList = new ArrayList<>();
        snMsgAdapter = new SnMsgAdapter(mContext, msgList, R.layout.item_sn_msg);
        msgLV.setAdapter(snMsgAdapter);

        msgLV.setOnScrollListener(new AbsListView.OnScrollListener() {
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
        getMainData();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_winner_tz;
    }

    private void getMainData() {
        HttpUtil.getWinnerData(mContext, userId, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                WinnerBean winnerBean = new Gson().fromJson(response.get(), WinnerBean.class);
                mySNList.clear();
                msgList.clear();
                if (winnerBean != null && YS.SUCCESE.equals(winnerBean.code) && winnerBean.data != null) {
                    totalCPB.setPercent(StringUtil.valueOf(winnerBean.data.totleMoney));
                    totalCPB.setProgress(80, true);
                    currentSNPrice = winnerBean.data.snprice;
                    priceCPB.setPercent(StringUtil.valueOf(winnerBean.data.snprice));
                    priceCPB.setProgress(50, true);
                    fhTV.setText(StringUtil.valueOf(winnerBean.data.earnMoney));
                    if (winnerBean.data.listSn != null && winnerBean.data.listSn.size() > 0) {
                        mySNList.addAll(winnerBean.data.listSn);
                    }
                    if (winnerBean.data.listRecord != null && winnerBean.data.listRecord.size() > 0) {
                        msgList.addAll(winnerBean.data.listRecord);
                    }
                    yueTV.setText("可用余额:" + winnerBean.data.freeMoney + YS.UNIT);
                    buyMoneyTV.setText("购买需支付:" + winnerBean.data.snprice + YS.UNIT);
                    currentQ = winnerBean.data.periodNum;
                    getWinnerInfo(winnerBean.data.periodNum);
                }
                mySNAdapter.refresh(mySNList);
                snMsgAdapter.refresh(msgList);
                srl.setRefreshing(false);
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                srl.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        getMainData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_tip:
                DialogUtil.showTip(mContext, text);
                break;
            case R.id.btn_tz:
                tz();
                break;
        }
    }

    private void tz() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("periodsNum", currentQ);//期数
        map.put("gameCode", 1002);//游戏类型
        map.put("lotteryTypeCode", "");
        map.put("complantTypeCode", 1000);//手动下注
        map.put("snmoney", currentSNPrice);//当前SN价格
        String json = new Gson().toJson(map);
        HttpUtil.tz(mContext, json, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                BaseBean baseBean = new Gson().fromJson(response.get(), BaseBean.class);
                if (baseBean != null && YS.SUCCESE.equals(baseBean.code)) {
                    show("投注成功！");
                    getMainData();
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }

    private void getWinnerInfo(String num) {
        if (StringUtil.isBlank(num)) {
            show("游戏期号为空!");
        }
        HttpUtil.getWinnerInfo(mContext, num, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                WinnerInfo winnerInfo = new Gson().fromJson(response.get(), WinnerInfo.class);
                if (winnerInfo != null && YS.SUCCESE.equals(winnerInfo.code) && winnerInfo.data != null) {
                    if (winnerInfo.data.lastGame != null) {
                        String periodNum1 = StringUtil.valueOf(winnerInfo.data.lastGame.periodNum);
                        String periodNum = StringUtil.valueOf(winnerInfo.data.lastGame.periodNum);
                        if (StringUtil.isBlank(periodNum1) && periodNum1.length() > 4) {
                            periodNum = periodNum1.substring(4);
                        }
                        if ("1000".equals(winnerInfo.data.lastGame.gameStatusCode)) {
                            //未开始
                        } else if ("1001".equals(winnerInfo.data.lastGame.gameStatusCode)) {
                            //进行中
                            tipTV.setText("第" + periodNum + "期开奖倒计时");
                        } else {
                            //等待开奖或者已经结束
                            if (winnerInfo.data.nextGame != null) {
                                String nextPeriodNum = winnerInfo.data.nextGame.periodNum;
                                if (StringUtil.isBlank(nextPeriodNum) && nextPeriodNum.length() > 4) {
                                    nextPeriodNum = nextPeriodNum.substring(4);
                                }
                                tipTV.setText("第" + nextPeriodNum + "期开奖倒计时");
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }


    private CountDownTimer countDownTimer;

    private void initTimer() {
        countDownTimer = new CountDownTimer(600 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

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

}
