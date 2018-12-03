package com.ys.game.sp;

import android.content.Context;

import com.google.gson.Gson;
import com.ys.game.bean.LoginBean;
import com.ys.game.util.SPUtil;
import com.ys.game.util.StringUtil;

/**
 * @author lh
 * @version 1.0.0
 * @filename UserSP
 * @description -------------------------------------------------------
 * @date 2018/12/3 11:15
 */
public class UserSP {
    public static final String KEY_INFO = "upsoft_userinfo";
    public static final String KEY_USERNAME = "upsoft_username";
    public static final String KEY_PASSWORD = "upsoft_password";

    //是否登录
    public static boolean isLogin(Context context) {
        String json = getUserInfo(context);
        if (StringUtil.isBlank(json)) {
            return false;
        } else {
            return true;
        }
    }

    public static void saveUsername(Context context, String username) {
        SPUtil.put(context, KEY_USERNAME, username);
    }

    public static void savePassword(Context context, String password) {
        //正常需加密
        SPUtil.put(context, KEY_PASSWORD, password);
    }


    public static String getUsername(Context context) {
        return (String) SPUtil.get(context, KEY_USERNAME, "");
    }

    public static String getPassword(Context context) {
        //正常取出来需解密
        return (String) SPUtil.get(context, KEY_PASSWORD, "");
    }


    public static void saveInfo(Context context, String infoJson) {
        SPUtil.put(context, KEY_INFO, infoJson);
    }

    public static String getUserInfo(Context context) {
        return (String) SPUtil.get(context, KEY_INFO, "");
    }


    public static LoginBean getInfo(Context context) {
        LoginBean infoBean = null;
        String json = getUserInfo(context);
        if (!StringUtil.isBlank(json)) {
            infoBean = new Gson().fromJson(json, LoginBean.class);
        }
        return infoBean;
    }

    //获取userId
    public static String getUserId(Context context) {
        String result = "";
        LoginBean loginBean = getInfo(context);
        if (loginBean != null && loginBean.data != null) {
            result = loginBean.data.consumerId;
        }
        return result;
    }

    public static void clear(Context context) {
        SPUtil.remove(context, KEY_INFO);
        SPUtil.remove(context, KEY_USERNAME);
        SPUtil.remove(context, KEY_PASSWORD);
    }
}
