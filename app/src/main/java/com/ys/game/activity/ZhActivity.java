package com.ys.game.activity;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.rest.Response;
import com.ys.game.R;
import com.ys.game.base.BaseActivity;
import com.ys.game.bean.ResultBean;
import com.ys.game.http.HttpListener;
import com.ys.game.util.DateUtil;
import com.ys.game.util.HttpUtil;
import com.ys.game.util.L;
import com.ys.game.util.StringUtil;
import com.ys.game.util.YS;

public class ZhActivity extends BaseActivity {
    private int type;
    private long nextTime = 0;
    private String nextQStr;
    private TextView nextQ;
    private EditText qsbsET, lxqsET, jgqsET, jgbsET;

    @Override
    public int getLayoutId() {
        return R.layout.activity_zh;
    }

    @Override
    public void initView() {
        backRL = getView(R.id.rl_back);
        backRL.setOnClickListener(this);
        nextQ = getView(R.id.tv_nextQ);
        qsbsET = getView(R.id.et_qsbs);
        lxqsET = getView(R.id.et_lxqs);
        jgqsET = getView(R.id.et_jgqs);
        jgbsET = getView(R.id.et_jgbs);
        qsbsET.setSelection(qsbsET.getText().toString().length());
    }

    @Override
    public void getData() {
        type = getIntent().getIntExtra("type", YS.TYPE_CQSSC);
        getResult();
    }

    public static void intentToZh(Context context, int type) {
        Intent intent = new Intent(context, ZhActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    private void getResult() {
        HttpUtil.getKJResult(mContext, type, 1, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                ResultBean resultBean = new Gson().fromJson(response.get(), ResultBean.class);
                if (resultBean != null && YS.SUCCESE.equals(resultBean.code) && resultBean.data != null && resultBean
                        .data.size() > 0) {
                    nextQStr = "" + (StringUtil.StringToLong(resultBean.data.get(0).periodsNum) + 1);
                    String[] ss = resultBean.data.get(0).lotteryNum.split(",");
                    long lastTime = DateUtil.changeTimeToLong(resultBean.data.get(0).lotteryTime);
                    if (type == YS.TYPE_CQSSC) {
                        if (nextTime == 0) {
                            if (DateUtil.isOpen()) {
                                if (DateUtil.isTen()) {
                                    nextTime = lastTime + 10 * 60 * 1000;
                                } else if (DateUtil.isFive()) {
                                    nextTime = lastTime + 5 * 60 * 1000;
                                }
                                nextQStr = "" + (StringUtil.StringToLong(resultBean.data.get(0).periodsNum) + 1);
                                nextQ.setText("第" + nextQStr.substring(4) + "期还剩" +
                                        DateUtil.getRemainTime2(nextTime));
                                start();
                            } else {
                                nextQ.setText("02:00-10:00之间不开奖");
                            }
                        }
                    } else if (type == YS.TYPE_TXFFC) {
                        if (nextTime == 0) {
                            nextTime = lastTime + 1 * 60 * 1000;
                            nextQStr = "" + (StringUtil.StringToLong(resultBean.data.get(0).periodsNum) + 1);
                            nextQ.setText("第" + nextQStr.substring(4) + "期还剩" +
                                    DateUtil.getRemainTime2(nextTime));
                            start();
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
                if (nextTime <= System.currentTimeMillis()) {
                    nextQStr = "" + (StringUtil.StringToLong(nextQStr) + 1);
                    if (type == YS.TYPE_CQSSC) {
                        if (DateUtil.isOpen()) {
                            if (DateUtil.isTen()) {
                                nextTime = nextTime + 10 * 60 * 1000;
                            } else if (DateUtil.isFive()) {
                                nextTime = nextTime + 5 * 60 * 1000;
                            }
                        } else {
                            nextQ.setText("02:00-10:00之间不开奖");
                        }
                    } else if (type == YS.TYPE_TXFFC) {
                        nextTime = nextTime + 1 * 60 * 1000;
                    }
                    L.e("倒计时完成，获取下一轮数据");
                }
                nextQ.setText("第" + (nextQStr.substring(4)) + "期还剩" +
                        DateUtil.getRemainTime2(nextTime));
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
