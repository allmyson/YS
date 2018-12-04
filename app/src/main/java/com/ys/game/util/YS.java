package com.ys.game.util;

/**
 * @author lh
 * @version 1.0.0
 * @filename YS
 * @description -------------------------------------------------------
 * @date 2018/11/26 17:53
 */
public class YS {
    public static final int TYPE_CQSSC = 1000;//重庆时时彩
    public static final int TYPE_TXFFC = 1001;//腾讯分分彩
    public static final int TYPE_ZHDSLZ = 1002;//最后的胜利者


    public static final String KF_QQ = "8789234124";
    public static final String KF_WX = "123456";

    public static final String SUCCESE ="SUCCESS";
    public static final String FAIL ="FAIL";



    public static final String IP = "http://148.163.169.41:8090";
    //登录
    public static final String LOGIN = IP + "/appService/loginForApp";
    //系统公告
    public static final String MSG = IP + "/appService/appNews";
    //投注
    public static final String TZ = IP + "/appService/bet";
    //开奖结果
    public static final String RESULT = IP + "/appService/getGameResultList";
}
