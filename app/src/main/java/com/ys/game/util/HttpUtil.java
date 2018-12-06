package com.ys.game.util;

import android.content.Context;

import com.ys.game.http.BaseHttp;
import com.ys.game.http.HttpListener;

/**
 * @author lh
 * @version 1.0.0
 * @filename HttpUtil
 * @description -------------------------------------------------------
 * @date 2018/12/3 11:00
 */
public class HttpUtil {
    //登录
    public static void login(Context context, String username, String pwd, HttpListener<String> httpListener) {
        String url = YS.LOGIN + "?userName=" + username + "&pwd=" + pwd;
        BaseHttp.getInstance().postSimpleJson(context, url, "", httpListener);
    }

    //查询消息
    public static void selectMsg(Context context, int start, int length, HttpListener<String>
            httpListener) {
        String url = YS.MSG + "?start=" + start + "&length=" + length;
        BaseHttp.getInstance().postSimpleJson(context, url, "", httpListener);
    }

    //投注
    public static void tz(Context context, String json, HttpListener<String> httpListener) {
        String url = YS.TZ;
        BaseHttp.getInstance().postSimpleJson(context, url, json, httpListener);
    }

    /**
     * 查询开奖结果
     *
     * @param context
     * @param typeCode 1000  时时彩    1001   分分彩
     * @param num      查询数量
     */
    public static void getKJResult(Context context, int typeCode, int num, HttpListener<String> httpListener) {
        String url = YS.RESULT + "?gameTypeCode=" + typeCode + "&gameNum=" + num;
        BaseHttp.getInstance().postSimpleJson(context, url, "", httpListener);
    }


    /**
     * @param context
     * @param userId
     * @param start
     * @param length
     * @param httpListener
     */
    public static void getTZJL(Context context, String userId, int start, int length, HttpListener<String>
            httpListener) {
        String url = YS.TZJL + "?userId=" + userId + "&start=" + start + "&length=" + length;
        BaseHttp.getInstance().postSimpleJson(context, url, "", httpListener);
    }
}
