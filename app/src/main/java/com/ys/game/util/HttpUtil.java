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
}
