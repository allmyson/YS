package com.ys.game.bean;

import android.support.annotation.NonNull;

/**
 * @author lh
 * @version 1.0.0
 * @filename GzGameBean
 * @description -------------------------------------------------------
 * @date 2018/11/27 10:23
 */
public class GzGameBean implements Comparable<GzGameBean> {
    public int type;
    public String name;
    public int count;
    public long endTime;//最后一次的查看时间

    @Override
    public int compareTo(@NonNull GzGameBean o) {
        return (int) (o.endTime - this.endTime);//降序
    }
}
