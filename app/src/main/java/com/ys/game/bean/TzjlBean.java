package com.ys.game.bean;

import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename TzjlBean
 * @description -------------------------------------------------------
 * @date 2018/12/5 14:36
 */
public class TzjlBean {

    /**
     * code : SUCCESS
     * msg : 查询成功
     * data : {"pageNum":null,"length":null,"start":null,"search":null,"order":null,"column":null,"recordsTotal":1,
     * "recordsFiltered":1,"draw":null,"data":[{"bets_time":"2018-12-05T03:36:51.000+0000","bets_money":"10",
     * "create_time":"2018-12-05T03:36:51.000+0000","SN_money":"10","is_win_code":null,"is_win_name":null,
     * "periods_num":"20181205003","complant_type_name":"手动下注","consumer_id":"be6cca47335946b9827ca5e09bea5f7c",
     * "game_name":"重庆时时彩","wallet_id":"8b000e60df8b4568b09f5c2eae959fbf","times":"1","lottery_type_code":"1000",
     * "bets_num":"1,2,3,4,5","win_money":null,"lottery_type_name":"五星直选复式","complant_type_code":"1000",
     * "reamrk":null,"game_code":"1000"}],"subSQL":null,"systemTime":0}
     * systemDate : 1543990499738
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
         * recordsTotal : 1
         * recordsFiltered : 1
         * draw : null
         * data : [{"bets_time":"2018-12-05T03:36:51.000+0000","bets_money":"10",
         * "create_time":"2018-12-05T03:36:51.000+0000","SN_money":"10","is_win_code":null,"is_win_name":null,
         * "periods_num":"20181205003","complant_type_name":"手动下注","consumer_id":"be6cca47335946b9827ca5e09bea5f7c",
         * "game_name":"重庆时时彩","wallet_id":"8b000e60df8b4568b09f5c2eae959fbf","times":"1","lottery_type_code":"1000",
         * "bets_num":"1,2,3,4,5","win_money":null,"lottery_type_name":"五星直选复式","complant_type_code":"1000",
         * "reamrk":null,"game_code":"1000"}]
         * subSQL : null
         * systemTime : 0
         */

        public Object pageNum;
        public Object length;
        public Object start;
        public Object search;
        public Object order;
        public Object column;
        public int recordsTotal;
        public int recordsFiltered;
        public Object draw;
        public Object subSQL;
        public int systemTime;
        public List<DataBean> data;

        public static class DataBean {
            /**
             * bets_time : 2018-12-05T03:36:51.000+0000
             * bets_money : 10
             * create_time : 2018-12-05T03:36:51.000+0000
             * SN_money : 10
             * is_win_code : null
             * is_win_name : null
             * periods_num : 20181205003
             * complant_type_name : 手动下注
             * consumer_id : be6cca47335946b9827ca5e09bea5f7c
             * game_name : 重庆时时彩
             * wallet_id : 8b000e60df8b4568b09f5c2eae959fbf
             * times : 1
             * lottery_type_code : 1000
             * bets_num : 1,2,3,4,5
             * win_money : null
             * lottery_type_name : 五星直选复式
             * complant_type_code : 1000
             * reamrk : null
             * game_code : 1000
             */

            public String bets_time;
            public String bets_money;
            public String create_time;
            public String SN_money;
            public Object is_win_code;
            public Object is_win_name;
            public String periods_num;
            public String complant_type_name;
            public String consumer_id;
            public String game_name;
            public String wallet_id;
            public String times;
            public String lottery_type_code;
            public String bets_num;
            public Object win_money;
            public String lottery_type_name;
            public String complant_type_code;
            public Object reamrk;
            public String game_code;
        }
    }
}
