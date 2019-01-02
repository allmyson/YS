package com.ys.game.bean;

/**
 * @author lh
 * @version 1.0.0
 * @filename WinnerInfo
 * @description -------------------------------------------------------
 * @date 2019/1/2 17:29
 */
public class WinnerInfo {

    /**
     * code : SUCCESS
     * msg : 查询成功
     * data : {"lastGame":{"recordId":"2896dd280dea4bfe8ed0dcfc761ff52d","expectEndTime":"2019-01-03",
     * "gameEndTime":"2019-01-03","gameStartTime":"2019-01-02T05:00:04.000+0000","gameStatusCode":"1000",
     * "gameStatusName":"进行中","gameTypeCode":"1002","gameTypeName":"最后的胜利者","incentive_money":null,
     * "incentive_status":null,"incentive_user_id":null,"lastMoney":null,"lastMoneyStatus":"1000","lastUserId":null,
     * "periodNum":"20190102001"},"nextGame":null}
     * systemDate : 1546417398247
     */

    public String code;
    public String msg;
    public DataBean data;
    public long systemDate;

    public static class DataBean {
        /**
         * lastGame : {"recordId":"2896dd280dea4bfe8ed0dcfc761ff52d","expectEndTime":"2019-01-03",
         * "gameEndTime":"2019-01-03","gameStartTime":"2019-01-02T05:00:04.000+0000","gameStatusCode":"1000",
         * "gameStatusName":"进行中","gameTypeCode":"1002","gameTypeName":"最后的胜利者","incentive_money":null,
         * "incentive_status":null,"incentive_user_id":null,"lastMoney":null,"lastMoneyStatus":"1000",
         * "lastUserId":null,"periodNum":"20190102001"}
         * nextGame : null
         */

        public LastGameBean lastGame;
        public LastGameBean nextGame;

        public static class LastGameBean {
            /**
             * recordId : 2896dd280dea4bfe8ed0dcfc761ff52d
             * expectEndTime : 2019-01-03
             * gameEndTime : 2019-01-03
             * gameStartTime : 2019-01-02T05:00:04.000+0000
             * gameStatusCode : 1000
             * gameStatusName : 进行中
             * gameTypeCode : 1002
             * gameTypeName : 最后的胜利者
             * incentive_money : null
             * incentive_status : null
             * incentive_user_id : null
             * lastMoney : null
             * lastMoneyStatus : 1000
             * lastUserId : null
             * periodNum : 20190102001
             */

            public String recordId;
            public String expectEndTime;
            public String gameEndTime;
            public String gameStartTime;
            public String gameStatusCode;
            public String gameStatusName;
            public String gameTypeCode;
            public String gameTypeName;
            public Object incentive_money;
            public Object incentive_status;
            public Object incentive_user_id;
            public Object lastMoney;
            public String lastMoneyStatus;
            public Object lastUserId;
            public String periodNum;
        }
    }
}
