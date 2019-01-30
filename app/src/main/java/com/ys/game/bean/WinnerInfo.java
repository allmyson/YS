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
     * data : {"lastGame":{"recordId":"202557621478453b90858da4e83e8979",
     * "expectEndTime":"2019-01-24T03:42:22.000+0000","gameEndTime":"2019-01-24T03:45:00.000+0000",
     * "gameStartTime":"2019-01-23T15:41:52.000+0000","gameStatusCode":"1003","gameStatusName":"已开奖",
     * "gameTypeCode":"1002","gameTypeName":"最后的胜利者","incentive_money":"5.3999999999999995",
     * "incentive_status":"1001","incentive_user_id":"","lastMoney":"10.799999999999999","lastMoneyStatus":"1001",
     * "lastUserId":null,"periodNum":"20190124001","lastUserName":null,"incentiveUserName":null,
     * "incentiveSnNum":null,"snNum":null},"nextGame":{"recordId":"05ddae940b9c4416bae3a3b34e8b06ea",
     * "expectEndTime":"2019-01-24T16:00:00.000+0000","gameEndTime":"2019-01-24T16:00:00.000+0000",
     * "gameStartTime":"2019-01-24T04:00:00.000+0000","gameStatusCode":"1001","gameStatusName":"进行中",
     * "gameTypeCode":"1002","gameTypeName":"最后的胜利者","incentive_money":null,"incentive_status":null,
     * "incentive_user_id":null,"lastMoney":null,"lastMoneyStatus":"1000","lastUserId":null,
     * "periodNum":"20190124002","lastUserName":null,"incentiveUserName":null,"incentiveSnNum":null,"snNum":null}}
     * systemDate : 1548309872075
     */

    public String code;
    public String msg;
    public DataBean data;
    public long systemDate;

    public static class DataBean {
        /**
         * lastGame : {"recordId":"202557621478453b90858da4e83e8979","expectEndTime":"2019-01-24T03:42:22.000+0000",
         * "gameEndTime":"2019-01-24T03:45:00.000+0000","gameStartTime":"2019-01-23T15:41:52.000+0000",
         * "gameStatusCode":"1003","gameStatusName":"已开奖","gameTypeCode":"1002","gameTypeName":"最后的胜利者",
         * "incentive_money":"5.3999999999999995","incentive_status":"1001","incentive_user_id":"",
         * "lastMoney":"10.799999999999999","lastMoneyStatus":"1001","lastUserId":null,"periodNum":"20190124001",
         * "lastUserName":null,"incentiveUserName":null,"incentiveSnNum":null,"snNum":null}
         * nextGame : {"recordId":"05ddae940b9c4416bae3a3b34e8b06ea","expectEndTime":"2019-01-24T16:00:00.000+0000",
         * "gameEndTime":"2019-01-24T16:00:00.000+0000","gameStartTime":"2019-01-24T04:00:00.000+0000",
         * "gameStatusCode":"1001","gameStatusName":"进行中","gameTypeCode":"1002","gameTypeName":"最后的胜利者",
         * "incentive_money":null,"incentive_status":null,"incentive_user_id":null,"lastMoney":null,
         * "lastMoneyStatus":"1000","lastUserId":null,"periodNum":"20190124002","lastUserName":null,
         * "incentiveUserName":null,"incentiveSnNum":null,"snNum":null}
         */

        public LastGameBean beforeGame;
        public LastGameBean lastGame;
        public LastGameBean nextGame;

        public static class LastGameBean {
            /**
             * recordId : 202557621478453b90858da4e83e8979
             * expectEndTime : 2019-01-24T03:42:22.000+0000
             * gameEndTime : 2019-01-24T03:45:00.000+0000
             * gameStartTime : 2019-01-23T15:41:52.000+0000
             * gameStatusCode : 1003
             * gameStatusName : 已开奖
             * gameTypeCode : 1002
             * gameTypeName : 最后的胜利者
             * incentive_money : 5.3999999999999995
             * incentive_status : 1001
             * incentive_user_id :
             * lastMoney : 10.799999999999999
             * lastMoneyStatus : 1001
             * lastUserId : null
             * periodNum : 20190124001
             * lastUserName : null
             * incentiveUserName : null
             * incentiveSnNum : null
             * snNum : null
             */

            public String recordId;
            public String expectEndTime;
            public String gameEndTime;
            public String gameStartTime;
            public String gameStatusCode;
            public String gameStatusName;
            public String gameTypeCode;
            public String gameTypeName;
            public String incentive_money;
            public String incentive_status;
            public String incentive_user_id;
            public String lastMoney;
            public String lastMoneyStatus;
            public String lastUserId;
            public String periodNum;
            public String lastUserName;
            public String incentiveUserName;
            public String incentiveSnNum;
            public String snNum;
            public String totleMoney;
        }
    }
}
