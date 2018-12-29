package com.ys.game.bean;

import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename WinnerTZJL
 * @description -------------------------------------------------------
 * @date 2018/12/29 17:22
 */
public class WinnerTZJL {

    /**
     * code : SUCCESS
     * msg : 查询成功
     * data : {"pageNum":null,"length":null,"start":null,"search":null,"order":null,"column":null,"recordsTotal":5,
     * "recordsFiltered":5,"draw":null,"data":[{"bets_time":"2018-12-29T09:14:20.000+0000","bets_money":"12.0",
     * "create_time":"2018-12-29T09:14:20.000+0000","SN_money":"12.0","is_win_code":"1001","is_win_name":"未中奖",
     * "periods_num":"20181228001","complant_type_name":"手动下注","consumer_id":"be6cca47335946b9827ca5e09bea5f7c",
     * "game_name":"最后的胜利者","wallet_id":"87b95df82d064d61bd18fcf73b8df75b","times":null,"consumer_name":"李白不白",
     * "lottery_type_code":null,"bets_num":"SN012","win_money":null,"lottery_type_name":null,
     * "complant_type_code":"1000","reamrk":"SN012","game_code":"1002"},{"bets_time":"2018-12-29T09:14:13.000+0000",
     * "bets_money":"11.0","create_time":"2018-12-29T09:14:13.000+0000","SN_money":"11.0","is_win_code":"1001",
     * "is_win_name":"未中奖","periods_num":"20181228001","complant_type_name":"手动下注",
     * "consumer_id":"be6cca47335946b9827ca5e09bea5f7c","game_name":"最后的胜利者",
     * "wallet_id":"be8b0d5b0d2c489aba43f19c2f390457","times":null,"consumer_name":"李白不白","lottery_type_code":null,
     * "bets_num":"SN011","win_money":null,"lottery_type_name":null,"complant_type_code":"1000","reamrk":"SN011",
     * "game_code":"1002"},{"bets_time":"2018-12-28T09:53:09.000+0000","bets_money":"15.0",
     * "create_time":"2018-12-28T09:53:09.000+0000","SN_money":"15.0","is_win_code":"1001","is_win_name":"未中奖",
     * "periods_num":"20181228001","complant_type_name":"手动下注","consumer_id":"be6cca47335946b9827ca5e09bea5f7c",
     * "game_name":"最后的胜利者","wallet_id":"d454695099db4b67b2bf603713aea48d","times":null,"consumer_name":"李白不白",
     * "lottery_type_code":null,"bets_num":"SN015","win_money":null,"lottery_type_name":null,
     * "complant_type_code":"1000","reamrk":"SN015","game_code":"1002"},{"bets_time":"2018-12-28T09:53:03.000+0000",
     * "bets_money":"13.0","create_time":"2018-12-28T09:53:03.000+0000","SN_money":"13.0","is_win_code":"1001",
     * "is_win_name":"未中奖","periods_num":"20181228001","complant_type_name":"手动下注",
     * "consumer_id":"be6cca47335946b9827ca5e09bea5f7c","game_name":"最后的胜利者",
     * "wallet_id":"ea12496fe2e84fc1b37a6d6c9fe01249","times":null,"consumer_name":"李白不白","lottery_type_code":null,
     * "bets_num":"SN013","win_money":null,"lottery_type_name":null,"complant_type_code":"1000","reamrk":"SN013",
     * "game_code":"1002"},{"bets_time":"2018-12-28T09:52:56.000+0000","bets_money":"11.0",
     * "create_time":"2018-12-28T09:52:56.000+0000","SN_money":"11.0","is_win_code":"1000","is_win_name":"中奖",
     * "periods_num":"20181228001","complant_type_name":"手动下注","consumer_id":"be6cca47335946b9827ca5e09bea5f7c",
     * "game_name":"最后的胜利者","wallet_id":"26a10046406446cfae7978889b21659f","times":null,"consumer_name":"李白不白",
     * "lottery_type_code":null,"bets_num":"SN011","win_money":"18.599999999999998","lottery_type_name":null,
     * "complant_type_code":"1000","reamrk":"SN011","game_code":"1002"}],"subSQL":null,"systemTime":0}
     * systemDate : 1546075202191
     */

    public String code;
    public String msg;
    public DataBeanX data;
    public long systemDate;

    public static class DataBeanX {
        /**
         * pageNum : null
         * length : null
         * start : null
         * search : null
         * order : null
         * column : null
         * recordsTotal : 5
         * recordsFiltered : 5
         * draw : null
         * data : [{"bets_time":"2018-12-29T09:14:20.000+0000","bets_money":"12.0",
         * "create_time":"2018-12-29T09:14:20.000+0000","SN_money":"12.0","is_win_code":"1001","is_win_name":"未中奖",
         * "periods_num":"20181228001","complant_type_name":"手动下注","consumer_id":"be6cca47335946b9827ca5e09bea5f7c",
         * "game_name":"最后的胜利者","wallet_id":"87b95df82d064d61bd18fcf73b8df75b","times":null,"consumer_name":"李白不白",
         * "lottery_type_code":null,"bets_num":"SN012","win_money":null,"lottery_type_name":null,
         * "complant_type_code":"1000","reamrk":"SN012","game_code":"1002"},
         * {"bets_time":"2018-12-29T09:14:13.000+0000","bets_money":"11.0",
         * "create_time":"2018-12-29T09:14:13.000+0000","SN_money":"11.0","is_win_code":"1001","is_win_name":"未中奖",
         * "periods_num":"20181228001","complant_type_name":"手动下注","consumer_id":"be6cca47335946b9827ca5e09bea5f7c",
         * "game_name":"最后的胜利者","wallet_id":"be8b0d5b0d2c489aba43f19c2f390457","times":null,"consumer_name":"李白不白",
         * "lottery_type_code":null,"bets_num":"SN011","win_money":null,"lottery_type_name":null,
         * "complant_type_code":"1000","reamrk":"SN011","game_code":"1002"},
         * {"bets_time":"2018-12-28T09:53:09.000+0000","bets_money":"15.0",
         * "create_time":"2018-12-28T09:53:09.000+0000","SN_money":"15.0","is_win_code":"1001","is_win_name":"未中奖",
         * "periods_num":"20181228001","complant_type_name":"手动下注","consumer_id":"be6cca47335946b9827ca5e09bea5f7c",
         * "game_name":"最后的胜利者","wallet_id":"d454695099db4b67b2bf603713aea48d","times":null,"consumer_name":"李白不白",
         * "lottery_type_code":null,"bets_num":"SN015","win_money":null,"lottery_type_name":null,
         * "complant_type_code":"1000","reamrk":"SN015","game_code":"1002"},
         * {"bets_time":"2018-12-28T09:53:03.000+0000","bets_money":"13.0",
         * "create_time":"2018-12-28T09:53:03.000+0000","SN_money":"13.0","is_win_code":"1001","is_win_name":"未中奖",
         * "periods_num":"20181228001","complant_type_name":"手动下注","consumer_id":"be6cca47335946b9827ca5e09bea5f7c",
         * "game_name":"最后的胜利者","wallet_id":"ea12496fe2e84fc1b37a6d6c9fe01249","times":null,"consumer_name":"李白不白",
         * "lottery_type_code":null,"bets_num":"SN013","win_money":null,"lottery_type_name":null,
         * "complant_type_code":"1000","reamrk":"SN013","game_code":"1002"},
         * {"bets_time":"2018-12-28T09:52:56.000+0000","bets_money":"11.0",
         * "create_time":"2018-12-28T09:52:56.000+0000","SN_money":"11.0","is_win_code":"1000","is_win_name":"中奖",
         * "periods_num":"20181228001","complant_type_name":"手动下注","consumer_id":"be6cca47335946b9827ca5e09bea5f7c",
         * "game_name":"最后的胜利者","wallet_id":"26a10046406446cfae7978889b21659f","times":null,"consumer_name":"李白不白",
         * "lottery_type_code":null,"bets_num":"SN011","win_money":"18.599999999999998","lottery_type_name":null,
         * "complant_type_code":"1000","reamrk":"SN011","game_code":"1002"}]
         * subSQL : null
         * systemTime : 0
         */

        public String pageNum;
        public String length;
        public String start;
        public String search;
        public String order;
        public String column;
        public String recordsTotal;
        public String recordsFiltered;
        public String draw;
        public String subSQL;
        public String systemTime;
        public List<DataBean> data;

        public static class DataBean {
            /**
             * bets_time : 2018-12-29T09:14:20.000+0000
             * bets_money : 12.0
             * create_time : 2018-12-29T09:14:20.000+0000
             * SN_money : 12.0
             * is_win_code : 1001
             * is_win_name : 未中奖
             * periods_num : 20181228001
             * complant_type_name : 手动下注
             * consumer_id : be6cca47335946b9827ca5e09bea5f7c
             * game_name : 最后的胜利者
             * wallet_id : 87b95df82d064d61bd18fcf73b8df75b
             * times : null
             * consumer_name : 李白不白
             * lottery_type_code : null
             * bets_num : SN012
             * win_money : null
             * lottery_type_name : null
             * complant_type_code : 1000
             * reamrk : SN012
             * game_code : 1002
             */

            public String bets_time;
            public String bets_money;
            public String create_time;
            public String SN_money;
            public String is_win_code;
            public String is_win_name;
            public String periods_num;
            public String complant_type_name;
            public String consumer_id;
            public String game_name;
            public String wallet_id;
            public String times;
            public String consumer_name;
            public String lottery_type_code;
            public String bets_num;
            public String win_money;
            public String lottery_type_name;
            public String complant_type_code;
            public String reamrk;
            public String game_code;
        }
    }
}
