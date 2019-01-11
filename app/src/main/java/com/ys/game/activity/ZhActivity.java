package com.ys.game.activity;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yanzhenjie.nohttp.rest.Response;
import com.ys.game.R;
import com.ys.game.adapter.ZhAdapter;
import com.ys.game.base.BaseActivity;
import com.ys.game.bean.BaseBean;
import com.ys.game.bean.LoginBean;
import com.ys.game.bean.ResultBean;
import com.ys.game.bean.UserInfo;
import com.ys.game.bean.ZhBean;
import com.ys.game.http.HttpListener;
import com.ys.game.sp.UserSP;
import com.ys.game.util.DateUtil;
import com.ys.game.util.HttpUtil;
import com.ys.game.util.L;
import com.ys.game.util.SPUtil;
import com.ys.game.util.StringUtil;
import com.ys.game.util.YS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZhActivity extends BaseActivity {
    private int type;//重庆时时彩，腾讯分分彩
    private int smallType;//小游戏类型
    private ArrayList<String> data;
    private long nextTime = 0;
    private String nextQStr;
    private TextView nextQ;
    private EditText qsbsET, lxqsET, jgqsET, jgbsET;
    private ListView zhLV;
    private List<ZhBean> zhBeanList;
    private ZhAdapter zhAdapter;
    private Button createOrderBtn;
    private TextView tipTV;
    private String userId;
    private LoginBean loginBean;
    private double yue = 0;
    private Button zhBtn;
    public static final int TYPE_ADD = 200;
    public static final int TYPE_CHENG = 201;
    private int beiType = TYPE_CHENG;
    private ImageView beiTypeIV;

    @Override
    public int getLayoutId() {
        return R.layout.activity_zh;
    }

    @Override
    public void initView() {
        beiTypeIV = getView(R.id.iv_beiType);
        beiTypeIV.setOnClickListener(this);
        zhBtn = getView(R.id.btn_zh);
        zhBtn.setOnClickListener(this);
        tipTV = getView(R.id.tv_tip);
        createOrderBtn = getView(R.id.btn_createOrder);
        createOrderBtn.setOnClickListener(this);
        zhLV = getView(R.id.lv_zh);
        backRL = getView(R.id.rl_back);
        backRL.setOnClickListener(this);
        nextQ = getView(R.id.tv_nextQ);
        qsbsET = getView(R.id.et_qsbs);
        lxqsET = getView(R.id.et_lxqs);
        jgqsET = getView(R.id.et_jgqs);
        jgbsET = getView(R.id.et_jgbs);
        qsbsET.setSelection(qsbsET.getText().toString().length());

        zhBeanList = new ArrayList<>();
        zhAdapter = new ZhAdapter(mContext, zhBeanList, R.layout.item_zh);
        zhLV.setAdapter(zhAdapter);
        zhAdapter.setCheckListener(new ZhAdapter.CheckListener() {
            @Override
            public void check(int position) {
                List<ZhBean> list = zhAdapter.getResult();
                setTip(list, yue + "");
            }
        });
        lxqsET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (StringUtil.StringToInt(lxqsET.getText().toString().trim()) > 10) {
                    lxqsET.setText("" + 10);
                    lxqsET.setSelection(lxqsET.getText().toString().length());
                }
            }
        });
    }

    @Override
    public void getData() {
        userId = UserSP.getUserId(mContext);
        loginBean = UserSP.getInfo(mContext);
        if (loginBean != null && loginBean.data != null) {
            yue = StringUtil.StringToDouble(loginBean.data.balance);
            setTip(zhAdapter.getResult(), "" + yue);
        }
        type = getIntent().getIntExtra("type", YS.TYPE_CQSSC);
        smallType = getIntent().getIntExtra("smallType", 1000);
//        data = getIntent().getStringArrayListExtra("data");
        String json = (String) SPUtil.get(mContext, "zh_data", "");
        if (!StringUtil.isBlank(json)) {
            data = new Gson().fromJson(json, new TypeToken<ArrayList<String>>() {
            }.getType());
        } else {
            data = new ArrayList<>();
        }
        getResult();
        getUserInfo();
    }

    public static void intentToZh(Context context, int type, int smallType, ArrayList<String> data) {
        Intent intent = new Intent(context, ZhActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("smallType", smallType);
        //android.os.TransactionTooLargeException: data parcel size 2800408 bytes
//        intent.putStringArrayListExtra("data", data);
        String json = new Gson().toJson(data);
        SPUtil.put(context, "zh_data", json);
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

    private List<ZhBean> createList() {
        int qsbs = StringUtil.StringToInt(qsbsET.getText().toString().trim());//起始倍数
        int qs = StringUtil.StringToInt(lxqsET.getText().toString().trim());//连续期数
        int jgqs = StringUtil.StringToInt(jgqsET.getText().toString().trim());//间隔期数
        int jgbs = StringUtil.StringToInt(jgbsET.getText().toString().trim());//间隔倍数
        List<ZhBean> list = new ArrayList<>();
        for (int i = 1; i <= qs; i++) {
            ZhBean zhBean = new ZhBean();
            zhBean.qs = "" + (StringUtil.StringToLong(nextQStr) + (i - 1));
            if (beiType == TYPE_CHENG) {
                zhBean.bs = (int) (qsbs * Math.pow(jgbs, (int) Math.ceil((double) i / jgqs) - 1));
            } else if (beiType == TYPE_ADD) {
                zhBean.bs = qsbs + jgbs * ((int) Math.ceil((double) i / jgqs) - 1);
            } else {
                zhBean.bs = (int) (qsbs * Math.pow(jgbs, (int) Math.ceil((double) i / jgqs) - 1));
            }
            zhBean.zhushu = data.size();
            if (type == YS.TYPE_CQSSC) {
                if (DateUtil.isOpen()) {
                    if (DateUtil.isTen()) {
                        zhBean.time = nextTime + 10 * 60 * 1000 * (i - 1);
                    } else if (DateUtil.isFive()) {
                        zhBean.time = nextTime + 5 * 60 * 1000 * (i - 1);
                    }
                } else {
//                    nextQ.setText("02:00-10:00之间不开奖");
                    zhBean.time = 0;
                }
            } else if (type == YS.TYPE_TXFFC) {
                zhBean.time = nextTime + 1 * 60 * 1000 * (i - 1);
            }
            zhBean.price = 2;
            list.add(zhBean);
        }
        return list;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_createOrder:
                zhBeanList.clear();
                zhBeanList.addAll(createList());
                zhAdapter.refresh(zhBeanList);
                setTip(zhAdapter.getResult(), "" + yue);
                break;
            case R.id.btn_zh:
                List<ZhBean> list = zhAdapter.getResult();
                if (list != null && list.size() > 0) {
                    zh();
                } else {
                    show("请至少选择一期进行追号！");
                }
                break;
            case R.id.iv_beiType:
                if (beiType == TYPE_CHENG) {
                    beiType = TYPE_ADD;
                    beiTypeIV.setImageResource(R.mipmap.ic_jia);
                } else {
                    beiType = TYPE_CHENG;
                    beiTypeIV.setImageResource(R.mipmap.ic_cf2);
                }
                break;
        }
    }

    private void setTip(List<ZhBean> list, String money) {
        double sum = 0;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                sum += list.get(i).bs * list.get(i).zhushu * list.get(i).price;
            }
        }
        String text = String.format("已选<font color=\"#fc6a44\">%s</font>期,共<font " +
                        "color=\"#fc6a44\">%s</font>" + YS.UNIT + "\t\t\t可用余额：<font color=\"#fc6a44\">%s</font>" + YS
                        .UNIT,
                list.size(), StringUtil.StringToDoubleStr(sum), StringUtil.StringToDoubleStr(money));
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tipTV.setText(Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY));
        } else {
            tipTV.setText(Html.fromHtml(text));
        }
    }

    private void getUserInfo() {
        HttpUtil.getUserInfo(mContext, userId, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                UserInfo userInfo = new Gson().fromJson(response.get(), UserInfo.class);
                if (userInfo != null && YS.SUCCESE.equals(userInfo.code) && userInfo.data != null) {
                    yue = StringUtil.StringToDouble(userInfo.data.balance);
                    setTip(zhAdapter.getResult(), "" + yue);
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {
            }
        });
    }

    /**
     * 追号
     */
    private void zh() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("gameCode", type);//游戏类型
        map.put("lotteryTypeCode", smallType);
        map.put("complantTypeCode", 1001);//追号下注
        List<ZhBean> zhList = zhAdapter.getResult();
        List<Map<String, Object>> betList = new ArrayList<>();
        for (ZhBean zhBean : zhList) {
            for (String str : data) {
                Map<String, Object> map2 = new HashMap<>();
                map2.put("betsNum", str);
                map2.put("payMoney", zhBean.price);
                map2.put("times", zhBean.bs);
                map2.put("periodsNum", zhBean.qs);
                betList.add(map2);
            }
        }
        map.put("trackDetail", betList);
        String json = new Gson().toJson(map);
        HttpUtil.zh(mContext, json, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                BaseBean baseBean = new Gson().fromJson(response.get(), BaseBean.class);
                if (baseBean != null && YS.SUCCESE.equals(baseBean.code)) {
                    show("追号成功！");
                } else {
                    show("追号失败！");
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }
}
