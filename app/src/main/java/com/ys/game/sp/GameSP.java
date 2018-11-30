package com.ys.game.sp;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ys.game.bean.GameBean;
import com.ys.game.bean.GzGameBean;
import com.ys.game.util.L;
import com.ys.game.util.SPUtil;
import com.ys.game.util.StringUtil;
import com.ys.game.util.YS;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lh
 * @version 1.0.0
 * @filename GameSP
 * @description -------------------------------------------------------
 * @date 2018/11/27 9:48
 */
public class GameSP {
    public static void add(Context context, GameBean gameBean) {
        List<GameBean> list = getAllList(context);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(gameBean);
        String json = new Gson().toJson(list);
        L.e("json=" + json);
        SPUtil.put(context, "game", json);
    }

    public static String getGameStr(Context context) {
        return (String) SPUtil.get(context, "game", "");
    }

    public static List<GameBean> getAllList(Context context) {
        List<GameBean> list = null;
        String json = getGameStr(context);
        if (!StringUtil.isBlank(json)) {
            list = new Gson().fromJson(json, new TypeToken<List<GameBean>>() {
            }.getType());
        } else {
            list = new ArrayList<>();
        }
        return list;
    }

    public static List<GzGameBean> getGzGameList(Context context) {
        List<GzGameBean> resultList = new ArrayList<>();
        List<GameBean> list = getAllList(context);
        if (list != null && list.size() > 0) {
            Map<Integer, List<GameBean>> map = new HashMap<>();
            List<GameBean> tempList;
            for (int i = 0; i < list.size(); i++) {
                tempList = map.get(list.get(i).type);
                if (tempList == null) {
                    tempList = new ArrayList<>();
                    map.put(list.get(i).type, tempList);
                }
                //取一个月以内的数据
                if (list.get(i).time > getMonthAgo()) {
                    tempList.add(list.get(i));
                }
            }
            for (int key : map.keySet()) {
                GzGameBean gzGameBean = new GzGameBean();
                gzGameBean.type = key;
                if (gzGameBean.type == YS.TYPE_CQSSC) {
                    gzGameBean.name = "重庆时时彩";
                } else if (gzGameBean.type == YS.TYPE_TXFFC) {
                    gzGameBean.name = "腾讯分分彩";
                } else if (gzGameBean.type == YS.TYPE_ZHDSLZ) {
                    gzGameBean.name = "最后的胜利者";
                }
                List<GameBean> data = map.get(key);
                gzGameBean.count = data.size();
                List<Long> time = new ArrayList<>();
                for (GameBean gameBean : data) {
                    time.add(gameBean.time);
                }
                gzGameBean.endTime = Collections.max(time);
                resultList.add(gzGameBean);
            }
            Collections.sort(resultList);
        }
        return resultList;
    }


    //获取一个月前的时间
    public static long getMonthAgo() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTimeInMillis();
    }

}
