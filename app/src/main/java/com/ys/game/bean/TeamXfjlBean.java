package com.ys.game.bean;

import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename TeamXfjlBean
 * @description -------------------------------------------------------
 * @date 2018/12/19 16:02
 */
public class TeamXfjlBean {

    /**
     * code : SUCCESS
     * msg : 查询成功
     * data : {"pageNum":null,"length":null,"start":null,"search":null,"order":null,"column":null,"recordsTotal":15,
     * "recordsFiltered":15,"draw":null,"data":[{"bets_time":"2018-12-18T14:59:41.000+0000","bets_money":"2.0",
     * "create_time":"2018-12-18T14:59:41.000+0000","SN_money":null,"is_win_code":"1002","is_win_name":"待开奖",
     * "periods_num":"201812181384","complant_type_name":"追号投注","consumer_id":"be6cca47335946b9827ca5e09bea5f7c",
     * "game_name":"腾讯分分彩","wallet_id":"4ffab839ef774966b495ffe8326de8db","times":"100","consumer_name":"李白不白",
     * "lottery_type_code":"1000","bets_num":"2,2,2,1,9","win_money":null,"lottery_type_name":"五星直选复式",
     * "complant_type_code":"1001","reamrk":null,"game_code":"1001"},{"bets_time":"2018-12-18T14:59:41.000+0000",
     * "bets_money":"2.0","create_time":"2018-12-18T14:59:41.000+0000","SN_money":null,"is_win_code":"1002",
     * "is_win_name":"待开奖","periods_num":"201812181380","complant_type_name":"追号投注",
     * "consumer_id":"be6cca47335946b9827ca5e09bea5f7c","game_name":"腾讯分分彩",
     * "wallet_id":"50d77beeda9d49d88a8db54082d7e6cb","times":"1","consumer_name":"李白不白","lottery_type_code":"1000",
     * "bets_num":"2,2,2,1,9","win_money":null,"lottery_type_name":"五星直选复式","complant_type_code":"1001",
     * "reamrk":null,"game_code":"1001"},{"bets_time":"2018-12-18T14:59:41.000+0000","bets_money":"2.0",
     * "create_time":"2018-12-18T14:59:41.000+0000","SN_money":null,"is_win_code":"1002","is_win_name":"待开奖",
     * "periods_num":"201812181380","complant_type_name":"追号投注","consumer_id":"be6cca47335946b9827ca5e09bea5f7c",
     * "game_name":"腾讯分分彩","wallet_id":"62bcd32f6aed4529a5d073b5ba3a3ca7","times":"1","consumer_name":"李白不白",
     * "lottery_type_code":"1000","bets_num":"2,2,2,1,2","win_money":null,"lottery_type_name":"五星直选复式",
     * "complant_type_code":"1001","reamrk":null,"game_code":"1001"},{"bets_time":"2018-12-18T14:59:41.000+0000",
     * "bets_money":"2.0","create_time":"2018-12-18T14:59:41.000+0000","SN_money":null,"is_win_code":"1002",
     * "is_win_name":"待开奖","periods_num":"201812181384","complant_type_name":"追号投注",
     * "consumer_id":"be6cca47335946b9827ca5e09bea5f7c","game_name":"腾讯分分彩",
     * "wallet_id":"7dfb5a11a99345d3a82842a67a34674a","times":"100","consumer_name":"李白不白",
     * "lottery_type_code":"1000","bets_num":"2,2,2,1,2","win_money":null,"lottery_type_name":"五星直选复式",
     * "complant_type_code":"1001","reamrk":null,"game_code":"1001"},{"bets_time":"2018-12-18T14:59:41.000+0000",
     * "bets_money":"2.0","create_time":"2018-12-18T14:59:41.000+0000","SN_money":null,"is_win_code":"1002",
     * "is_win_name":"待开奖","periods_num":"201812181381","complant_type_name":"追号投注",
     * "consumer_id":"be6cca47335946b9827ca5e09bea5f7c","game_name":"腾讯分分彩",
     * "wallet_id":"93871a4b11d64e7ebd3e35a26998f951","times":"1","consumer_name":"李白不白","lottery_type_code":"1000",
     * "bets_num":"2,2,2,1,9","win_money":null,"lottery_type_name":"五星直选复式","complant_type_code":"1001",
     * "reamrk":null,"game_code":"1001"},{"bets_time":"2018-12-18T14:59:41.000+0000","bets_money":"2.0",
     * "create_time":"2018-12-18T14:59:41.000+0000","SN_money":null,"is_win_code":"1002","is_win_name":"待开奖",
     * "periods_num":"201812181382","complant_type_name":"追号投注","consumer_id":"be6cca47335946b9827ca5e09bea5f7c",
     * "game_name":"腾讯分分彩","wallet_id":"aba20ea536b545c2a26e8022b12aca52","times":"10","consumer_name":"李白不白",
     * "lottery_type_code":"1000","bets_num":"2,2,2,1,9","win_money":null,"lottery_type_name":"五星直选复式",
     * "complant_type_code":"1001","reamrk":null,"game_code":"1001"},{"bets_time":"2018-12-18T14:59:41.000+0000",
     * "bets_money":"2.0","create_time":"2018-12-18T14:59:41.000+0000","SN_money":null,"is_win_code":"1002",
     * "is_win_name":"待开奖","periods_num":"201812181383","complant_type_name":"追号投注",
     * "consumer_id":"be6cca47335946b9827ca5e09bea5f7c","game_name":"腾讯分分彩",
     * "wallet_id":"be86ccf4c6ea4d76b3409c02d19e6e20","times":"10","consumer_name":"李白不白","lottery_type_code":"1000",
     * "bets_num":"2,2,2,1,2","win_money":null,"lottery_type_name":"五星直选复式","complant_type_code":"1001",
     * "reamrk":null,"game_code":"1001"},{"bets_time":"2018-12-18T14:59:41.000+0000","bets_money":"2.0",
     * "create_time":"2018-12-18T14:59:41.000+0000","SN_money":null,"is_win_code":"1002","is_win_name":"待开奖",
     * "periods_num":"201812181383","complant_type_name":"追号投注","consumer_id":"be6cca47335946b9827ca5e09bea5f7c",
     * "game_name":"腾讯分分彩","wallet_id":"c739ed2c31fe4ebc973f93930e55ed31","times":"10","consumer_name":"李白不白",
     * "lottery_type_code":"1000","bets_num":"2,2,2,1,9","win_money":null,"lottery_type_name":"五星直选复式",
     * "complant_type_code":"1001","reamrk":null,"game_code":"1001"},{"bets_time":"2018-12-18T14:59:41.000+0000",
     * "bets_money":"2.0","create_time":"2018-12-18T14:59:41.000+0000","SN_money":null,"is_win_code":"1002",
     * "is_win_name":"待开奖","periods_num":"201812181382","complant_type_name":"追号投注",
     * "consumer_id":"be6cca47335946b9827ca5e09bea5f7c","game_name":"腾讯分分彩",
     * "wallet_id":"c91e639ecb054b9fb579f1b27bcf4c42","times":"10","consumer_name":"李白不白","lottery_type_code":"1000",
     * "bets_num":"2,2,2,1,2","win_money":null,"lottery_type_name":"五星直选复式","complant_type_code":"1001",
     * "reamrk":null,"game_code":"1001"},{"bets_time":"2018-12-18T14:59:41.000+0000","bets_money":"2.0",
     * "create_time":"2018-12-18T14:59:41.000+0000","SN_money":null,"is_win_code":"1002","is_win_name":"待开奖",
     * "periods_num":"201812181381","complant_type_name":"追号投注","consumer_id":"be6cca47335946b9827ca5e09bea5f7c",
     * "game_name":"腾讯分分彩","wallet_id":"dc3204f4fed84e96b3c1919a68ba3e64","times":"1","consumer_name":"李白不白",
     * "lottery_type_code":"1000","bets_num":"2,2,2,1,2","win_money":null,"lottery_type_name":"五星直选复式",
     * "complant_type_code":"1001","reamrk":null,"game_code":"1001"},{"bets_time":"2018-12-18T14:57:33.000+0000",
     * "bets_money":"2.0","create_time":"2018-12-18T14:57:33.000+0000","SN_money":null,"is_win_code":"1002",
     * "is_win_name":"待开奖","periods_num":"20181218109","complant_type_name":"追号投注",
     * "consumer_id":"be6cca47335946b9827ca5e09bea5f7c","game_name":"重庆时时彩",
     * "wallet_id":"0105a52da5004b59a95b873e913179bb","times":"10","consumer_name":"李白不白","lottery_type_code":"1003",
     * "bets_num":"2,3","win_money":null,"lottery_type_name":"后二星组选复","complant_type_code":"1001","reamrk":null,
     * "game_code":"1000"},{"bets_time":"2018-12-18T14:57:33.000+0000","bets_money":"2.0",
     * "create_time":"2018-12-18T14:57:33.000+0000","SN_money":null,"is_win_code":"1002","is_win_name":"待开奖",
     * "periods_num":"20181218108","complant_type_name":"追号投注","consumer_id":"be6cca47335946b9827ca5e09bea5f7c",
     * "game_name":"重庆时时彩","wallet_id":"2651c9f7fb8a4b279289c63f7f39b7ba","times":"1","consumer_name":"李白不白",
     * "lottery_type_code":"1003","bets_num":"2,3","win_money":null,"lottery_type_name":"后二星组选复",
     * "complant_type_code":"1001","reamrk":null,"game_code":"1000"},{"bets_time":"2018-12-18T14:57:33.000+0000",
     * "bets_money":"2.0","create_time":"2018-12-18T14:57:33.000+0000","SN_money":null,"is_win_code":"1002",
     * "is_win_name":"待开奖","periods_num":"20181218111","complant_type_name":"追号投注",
     * "consumer_id":"be6cca47335946b9827ca5e09bea5f7c","game_name":"重庆时时彩",
     * "wallet_id":"5cf72752d6fd40fd9fde09f0b111037e","times":"100","consumer_name":"李白不白",
     * "lottery_type_code":"1003","bets_num":"2,3","win_money":null,"lottery_type_name":"后二星组选复",
     * "complant_type_code":"1001","reamrk":null,"game_code":"1000"},{"bets_time":"2018-12-18T14:57:33.000+0000",
     * "bets_money":"2.0","create_time":"2018-12-18T14:57:33.000+0000","SN_money":null,"is_win_code":"1002",
     * "is_win_name":"待开奖","periods_num":"20181218107","complant_type_name":"追号投注",
     * "consumer_id":"be6cca47335946b9827ca5e09bea5f7c","game_name":"重庆时时彩",
     * "wallet_id":"9609c150eddb4c07b05da61d19cf3e17","times":"100","consumer_name":"李白不白",
     * "lottery_type_code":"1003","bets_num":"2,3","win_money":null,"lottery_type_name":"后二星组选复",
     * "complant_type_code":"1001","reamrk":null,"game_code":"1000"},{"bets_time":"2018-12-18T14:57:33.000+0000",
     * "bets_money":"2.0","create_time":"2018-12-18T14:57:33.000+0000","SN_money":null,"is_win_code":"1002",
     * "is_win_name":"待开奖","periods_num":"20181218110","complant_type_name":"追号投注",
     * "consumer_id":"be6cca47335946b9827ca5e09bea5f7c","game_name":"重庆时时彩",
     * "wallet_id":"be299105afd549f99c41ad1f173bf17a","times":"100","consumer_name":"李白不白",
     * "lottery_type_code":"1003","bets_num":"2,3","win_money":null,"lottery_type_name":"后二星组选复",
     * "complant_type_code":"1001","reamrk":null,"game_code":"1000"}],"subSQL":null,"systemTime":0}
     * systemDate : 1545206531845
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
         * recordsTotal : 15
         * recordsFiltered : 15
         * draw : null
         * data : [{"bets_time":"2018-12-18T14:59:41.000+0000","bets_money":"2.0",
         * "create_time":"2018-12-18T14:59:41.000+0000","SN_money":null,"is_win_code":"1002","is_win_name":"待开奖",
         * "periods_num":"201812181384","complant_type_name":"追号投注","consumer_id":"be6cca47335946b9827ca5e09bea5f7c",
         * "game_name":"腾讯分分彩","wallet_id":"4ffab839ef774966b495ffe8326de8db","times":"100","consumer_name":"李白不白",
         * "lottery_type_code":"1000","bets_num":"2,2,2,1,9","win_money":null,"lottery_type_name":"五星直选复式",
         * "complant_type_code":"1001","reamrk":null,"game_code":"1001"},{"bets_time":"2018-12-18T14:59:41.000+0000",
         * "bets_money":"2.0","create_time":"2018-12-18T14:59:41.000+0000","SN_money":null,"is_win_code":"1002",
         * "is_win_name":"待开奖","periods_num":"201812181380","complant_type_name":"追号投注",
         * "consumer_id":"be6cca47335946b9827ca5e09bea5f7c","game_name":"腾讯分分彩",
         * "wallet_id":"50d77beeda9d49d88a8db54082d7e6cb","times":"1","consumer_name":"李白不白",
         * "lottery_type_code":"1000","bets_num":"2,2,2,1,9","win_money":null,"lottery_type_name":"五星直选复式",
         * "complant_type_code":"1001","reamrk":null,"game_code":"1001"},{"bets_time":"2018-12-18T14:59:41.000+0000",
         * "bets_money":"2.0","create_time":"2018-12-18T14:59:41.000+0000","SN_money":null,"is_win_code":"1002",
         * "is_win_name":"待开奖","periods_num":"201812181380","complant_type_name":"追号投注",
         * "consumer_id":"be6cca47335946b9827ca5e09bea5f7c","game_name":"腾讯分分彩",
         * "wallet_id":"62bcd32f6aed4529a5d073b5ba3a3ca7","times":"1","consumer_name":"李白不白",
         * "lottery_type_code":"1000","bets_num":"2,2,2,1,2","win_money":null,"lottery_type_name":"五星直选复式",
         * "complant_type_code":"1001","reamrk":null,"game_code":"1001"},{"bets_time":"2018-12-18T14:59:41.000+0000",
         * "bets_money":"2.0","create_time":"2018-12-18T14:59:41.000+0000","SN_money":null,"is_win_code":"1002",
         * "is_win_name":"待开奖","periods_num":"201812181384","complant_type_name":"追号投注",
         * "consumer_id":"be6cca47335946b9827ca5e09bea5f7c","game_name":"腾讯分分彩",
         * "wallet_id":"7dfb5a11a99345d3a82842a67a34674a","times":"100","consumer_name":"李白不白",
         * "lottery_type_code":"1000","bets_num":"2,2,2,1,2","win_money":null,"lottery_type_name":"五星直选复式",
         * "complant_type_code":"1001","reamrk":null,"game_code":"1001"},{"bets_time":"2018-12-18T14:59:41.000+0000",
         * "bets_money":"2.0","create_time":"2018-12-18T14:59:41.000+0000","SN_money":null,"is_win_code":"1002",
         * "is_win_name":"待开奖","periods_num":"201812181381","complant_type_name":"追号投注",
         * "consumer_id":"be6cca47335946b9827ca5e09bea5f7c","game_name":"腾讯分分彩",
         * "wallet_id":"93871a4b11d64e7ebd3e35a26998f951","times":"1","consumer_name":"李白不白",
         * "lottery_type_code":"1000","bets_num":"2,2,2,1,9","win_money":null,"lottery_type_name":"五星直选复式",
         * "complant_type_code":"1001","reamrk":null,"game_code":"1001"},{"bets_time":"2018-12-18T14:59:41.000+0000",
         * "bets_money":"2.0","create_time":"2018-12-18T14:59:41.000+0000","SN_money":null,"is_win_code":"1002",
         * "is_win_name":"待开奖","periods_num":"201812181382","complant_type_name":"追号投注",
         * "consumer_id":"be6cca47335946b9827ca5e09bea5f7c","game_name":"腾讯分分彩",
         * "wallet_id":"aba20ea536b545c2a26e8022b12aca52","times":"10","consumer_name":"李白不白",
         * "lottery_type_code":"1000","bets_num":"2,2,2,1,9","win_money":null,"lottery_type_name":"五星直选复式",
         * "complant_type_code":"1001","reamrk":null,"game_code":"1001"},{"bets_time":"2018-12-18T14:59:41.000+0000",
         * "bets_money":"2.0","create_time":"2018-12-18T14:59:41.000+0000","SN_money":null,"is_win_code":"1002",
         * "is_win_name":"待开奖","periods_num":"201812181383","complant_type_name":"追号投注",
         * "consumer_id":"be6cca47335946b9827ca5e09bea5f7c","game_name":"腾讯分分彩",
         * "wallet_id":"be86ccf4c6ea4d76b3409c02d19e6e20","times":"10","consumer_name":"李白不白",
         * "lottery_type_code":"1000","bets_num":"2,2,2,1,2","win_money":null,"lottery_type_name":"五星直选复式",
         * "complant_type_code":"1001","reamrk":null,"game_code":"1001"},{"bets_time":"2018-12-18T14:59:41.000+0000",
         * "bets_money":"2.0","create_time":"2018-12-18T14:59:41.000+0000","SN_money":null,"is_win_code":"1002",
         * "is_win_name":"待开奖","periods_num":"201812181383","complant_type_name":"追号投注",
         * "consumer_id":"be6cca47335946b9827ca5e09bea5f7c","game_name":"腾讯分分彩",
         * "wallet_id":"c739ed2c31fe4ebc973f93930e55ed31","times":"10","consumer_name":"李白不白",
         * "lottery_type_code":"1000","bets_num":"2,2,2,1,9","win_money":null,"lottery_type_name":"五星直选复式",
         * "complant_type_code":"1001","reamrk":null,"game_code":"1001"},{"bets_time":"2018-12-18T14:59:41.000+0000",
         * "bets_money":"2.0","create_time":"2018-12-18T14:59:41.000+0000","SN_money":null,"is_win_code":"1002",
         * "is_win_name":"待开奖","periods_num":"201812181382","complant_type_name":"追号投注",
         * "consumer_id":"be6cca47335946b9827ca5e09bea5f7c","game_name":"腾讯分分彩",
         * "wallet_id":"c91e639ecb054b9fb579f1b27bcf4c42","times":"10","consumer_name":"李白不白",
         * "lottery_type_code":"1000","bets_num":"2,2,2,1,2","win_money":null,"lottery_type_name":"五星直选复式",
         * "complant_type_code":"1001","reamrk":null,"game_code":"1001"},{"bets_time":"2018-12-18T14:59:41.000+0000",
         * "bets_money":"2.0","create_time":"2018-12-18T14:59:41.000+0000","SN_money":null,"is_win_code":"1002",
         * "is_win_name":"待开奖","periods_num":"201812181381","complant_type_name":"追号投注",
         * "consumer_id":"be6cca47335946b9827ca5e09bea5f7c","game_name":"腾讯分分彩",
         * "wallet_id":"dc3204f4fed84e96b3c1919a68ba3e64","times":"1","consumer_name":"李白不白",
         * "lottery_type_code":"1000","bets_num":"2,2,2,1,2","win_money":null,"lottery_type_name":"五星直选复式",
         * "complant_type_code":"1001","reamrk":null,"game_code":"1001"},{"bets_time":"2018-12-18T14:57:33.000+0000",
         * "bets_money":"2.0","create_time":"2018-12-18T14:57:33.000+0000","SN_money":null,"is_win_code":"1002",
         * "is_win_name":"待开奖","periods_num":"20181218109","complant_type_name":"追号投注",
         * "consumer_id":"be6cca47335946b9827ca5e09bea5f7c","game_name":"重庆时时彩",
         * "wallet_id":"0105a52da5004b59a95b873e913179bb","times":"10","consumer_name":"李白不白",
         * "lottery_type_code":"1003","bets_num":"2,3","win_money":null,"lottery_type_name":"后二星组选复",
         * "complant_type_code":"1001","reamrk":null,"game_code":"1000"},{"bets_time":"2018-12-18T14:57:33.000+0000",
         * "bets_money":"2.0","create_time":"2018-12-18T14:57:33.000+0000","SN_money":null,"is_win_code":"1002",
         * "is_win_name":"待开奖","periods_num":"20181218108","complant_type_name":"追号投注",
         * "consumer_id":"be6cca47335946b9827ca5e09bea5f7c","game_name":"重庆时时彩",
         * "wallet_id":"2651c9f7fb8a4b279289c63f7f39b7ba","times":"1","consumer_name":"李白不白",
         * "lottery_type_code":"1003","bets_num":"2,3","win_money":null,"lottery_type_name":"后二星组选复",
         * "complant_type_code":"1001","reamrk":null,"game_code":"1000"},{"bets_time":"2018-12-18T14:57:33.000+0000",
         * "bets_money":"2.0","create_time":"2018-12-18T14:57:33.000+0000","SN_money":null,"is_win_code":"1002",
         * "is_win_name":"待开奖","periods_num":"20181218111","complant_type_name":"追号投注",
         * "consumer_id":"be6cca47335946b9827ca5e09bea5f7c","game_name":"重庆时时彩",
         * "wallet_id":"5cf72752d6fd40fd9fde09f0b111037e","times":"100","consumer_name":"李白不白",
         * "lottery_type_code":"1003","bets_num":"2,3","win_money":null,"lottery_type_name":"后二星组选复",
         * "complant_type_code":"1001","reamrk":null,"game_code":"1000"},{"bets_time":"2018-12-18T14:57:33.000+0000",
         * "bets_money":"2.0","create_time":"2018-12-18T14:57:33.000+0000","SN_money":null,"is_win_code":"1002",
         * "is_win_name":"待开奖","periods_num":"20181218107","complant_type_name":"追号投注",
         * "consumer_id":"be6cca47335946b9827ca5e09bea5f7c","game_name":"重庆时时彩",
         * "wallet_id":"9609c150eddb4c07b05da61d19cf3e17","times":"100","consumer_name":"李白不白",
         * "lottery_type_code":"1003","bets_num":"2,3","win_money":null,"lottery_type_name":"后二星组选复",
         * "complant_type_code":"1001","reamrk":null,"game_code":"1000"},{"bets_time":"2018-12-18T14:57:33.000+0000",
         * "bets_money":"2.0","create_time":"2018-12-18T14:57:33.000+0000","SN_money":null,"is_win_code":"1002",
         * "is_win_name":"待开奖","periods_num":"20181218110","complant_type_name":"追号投注",
         * "consumer_id":"be6cca47335946b9827ca5e09bea5f7c","game_name":"重庆时时彩",
         * "wallet_id":"be299105afd549f99c41ad1f173bf17a","times":"100","consumer_name":"李白不白",
         * "lottery_type_code":"1003","bets_num":"2,3","win_money":null,"lottery_type_name":"后二星组选复",
         * "complant_type_code":"1001","reamrk":null,"game_code":"1000"}]
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
             * bets_time : 2018-12-18T14:59:41.000+0000
             * bets_money : 2.0
             * create_time : 2018-12-18T14:59:41.000+0000
             * SN_money : null
             * is_win_code : 1002
             * is_win_name : 待开奖
             * periods_num : 201812181384
             * complant_type_name : 追号投注
             * consumer_id : be6cca47335946b9827ca5e09bea5f7c
             * game_name : 腾讯分分彩
             * wallet_id : 4ffab839ef774966b495ffe8326de8db
             * times : 100
             * consumer_name : 李白不白
             * lottery_type_code : 1000
             * bets_num : 2,2,2,1,9
             * win_money : null
             * lottery_type_name : 五星直选复式
             * complant_type_code : 1001
             * reamrk : null
             * game_code : 1001
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
