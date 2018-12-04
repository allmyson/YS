package com.ys.game.bean;

import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename ResultBean
 * @description -------------------------------------------------------
 * @date 2018/12/4 14:34
 */
public class ResultBean {

    /**
     * code : SUCCESS
     * msg : 查询成功
     * data : [{"recordId":"95f7784574ff40418ba5a4cd26d29b8a","gameCode":"1000","gameName":"重庆时时彩","lotteryNum":"7,5,
     * 5,5,3","lotteryTime":"2018-12-04T06:01:14.000+0000","periodsNum":"20181204048"},
     * {"recordId":"e82884c7aebb41348ef4bd4aed141f11","gameCode":"1000","gameName":"重庆时时彩","lotteryNum":"4,7,5,1,2",
     * "lotteryTime":"2018-12-04T05:41:20.000+0000","periodsNum":"20181204046"},
     * {"recordId":"468b883ae5d64b00a0c634d01c48c27d","gameCode":"1000","gameName":"重庆时时彩","lotteryNum":"4,7,4,1,7",
     * "lotteryTime":"2018-12-04T05:21:50.000+0000","periodsNum":"20181204044"},
     * {"recordId":"70e2bc429126499dad5c73279fbdf7ea","gameCode":"1000","gameName":"重庆时时彩","lotteryNum":"3,9,5,9,8",
     * "lotteryTime":"2018-12-04T04:32:08.000+0000","periodsNum":"20181204039"},
     * {"recordId":"d47e06125b8a4dcf8c3b43b5f75b336c","gameCode":"1000","gameName":"重庆时时彩","lotteryNum":"6,5,2,5,1",
     * "lotteryTime":"2018-12-04T04:01:26.000+0000","periodsNum":"20181204036"}]
     * systemDate : 1543905239505
     */

    public String code;
    public String msg;
    public long systemDate;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * recordId : 95f7784574ff40418ba5a4cd26d29b8a
         * gameCode : 1000
         * gameName : 重庆时时彩
         * lotteryNum : 7,5,5,5,3
         * lotteryTime : 2018-12-04T06:01:14.000+0000
         * periodsNum : 20181204048
         */

        public String recordId;
        public String gameCode;
        public String gameName;
        public String lotteryNum;
        public String lotteryTime;
        public String periodsNum;
    }
}
