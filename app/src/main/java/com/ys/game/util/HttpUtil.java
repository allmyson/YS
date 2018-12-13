package com.ys.game.util;

import android.content.Context;

import com.ys.game.http.BaseHttp;
import com.ys.game.http.HttpListener;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * 修改用户信息
     *
     * @param context
     * @param userId
     * @param nickName
     * @param photoUrl
     * @param httpListener
     */
    public static void updateUserInfo(Context context, String userId, String nickName, String photoUrl,
                                      HttpListener<String> httpListener) {
        String url = YS.UPDATE_USERINFO + "?userId=" + userId;
        if (!StringUtil.isBlank(nickName)) {
            try {
                url += URLEncoder.encode(nickName, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        Map<String, File> map = null;
        if (!StringUtil.isBlank(photoUrl)) {
            map = new HashMap<>();
            map.put("file", new File(photoUrl));
        }
        BaseHttp.getInstance().postFile(context, url, map, httpListener);
    }

    /**
     * 开户
     *
     * @param context
     * @param nickName
     * @param loginName
     * @param psd
     * @param fd
     * @param httpListener
     */
    public static void kh(Context context, String userId, String nickName, String loginName, String psd, String fd,
                          HttpListener<String> httpListener) {
        String consumerName = "";
        try {
            consumerName = URLEncoder.encode(nickName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = YS.KH + "?consumerName=" + consumerName + "&loginName=" + loginName + "&pwd=" + Md5Util
                .getMD5String(psd) + "&backNum=" + fd + "&userId=" + userId;
        BaseHttp.getInstance().postSimpleJson(context,url,"",httpListener);
    }

    /**
     * 获取团队管理
     * @param context
     * @param userId
     * @param httpListener
     */
    public static void getTeamData(Context context,String userId,HttpListener<String> httpListener){
        String url = YS.TEAM_GL+"?userId="+userId;
        BaseHttp.getInstance().postSimpleJson(context,url,"",httpListener);
    }
}
