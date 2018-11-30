package com.ys.game.util;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 * <p/>
 * 文件名称：android_framwork<br>
 * 摘要：简要描述本文件的内容<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：彭粟<br>
 * 完成日期：2017/1/3<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：彭粟<br>
 * 完成日期：2017/1/3<br>
 */

public class DateUtil {
    /**
     * @return void
     * @author 彭粟
     * @version 1.0
     * @Description: 传入时间戳
     * @time： 2016/12/28
     */

    public static String getLongDate(String date) {
        String resultDate = "";
        if (!TextUtils.isEmpty(date)) {
            if (!"0".equals(date)) {
                try {
                    long datel = Long.valueOf(date);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    resultDate = sdf.format(new Date(datel));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        //Thu Jan 01 08:00:00 GMT+08:00 1970
        return resultDate;
    }

    /**
     * @return void
     * @author 彭粟
     * @version 1.0
     * @Description: 传入时间戳
     * @time： 2016/12/28
     */
    public static String getDateAndMinute(String date) {
        String resultDate = "";
        if (!TextUtils.isEmpty(date)) {
            if (!"0".equals(date)) {
                try {
                    long datel = Long.valueOf(date);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    resultDate = sdf.format(new Date(datel));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        //Thu Jan 01 08:00:00 GMT+08:00 1970
        return resultDate;
    }

    /**
     * 根据毫秒时间得到日期
     *
     * @param time
     * @return String
     * @创建时间：2017年2月16日 下午7:54:31
     * @作者： 董杰科
     * @描述: TODO
     */
    public static String getLongDate(long time) {
        if (time == 0) {
            return "";
        } else {
            Date dat = new Date(time);
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(dat);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String sb = format.format(gc.getTime());
            return sb;
        }
    }

    /**
     * 获取时间戳
     *
     * @param
     * @return String
     * @创建时间：2017年2月27日
     * @作者： 李杰
     * @描述: TODO
     */
    public static long getLongTime() {
        return System.currentTimeMillis();
    }


    public static long getTimeYearMString(String time) {

        if (TextUtils.isEmpty(time)) {
            return 0;
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            long resultTime = 0;
            try {
                resultTime = format.parse(time).getTime();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultTime;
        }
    }


    /**
     * 根据毫秒时间得到日期
     *
     * @param time
     * @return String
     * @创建时间：2017年2月16日 下午7:54:31
     * @作者： 董杰科
     * @描述: TODO
     */
    public static String getLongDate2(long time) {
        if (time == 0) {
            return "";
        } else {
            Date dat = new Date(time);
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(dat);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sb = format.format(gc.getTime());
            return sb;
        }
    }
}
