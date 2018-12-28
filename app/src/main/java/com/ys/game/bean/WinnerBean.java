package com.ys.game.bean;

import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename WinnerBean
 * @description -------------------------------------------------------
 * @date 2018/12/28 13:55
 */
public class WinnerBean {

    /**
     * code : SUCCESS
     * msg : 查询成功
     * data : {"totleMoney":"15.0","earnMoney":"0.00","listSn":["15.0"],"listRecord":["李白不白于00:05:53成功购买了SN,
     * 购买编号为15.0"],"freeMoney":"9.0993571E7","payMoney":null,"endTime":"2018-12-28","periodNum":"20181228001",
     * "snprice":"15"}
     * systemDate : 1545966266224
     */

    public String code;
    public String msg;
    public DataBean data;
    public long systemDate;

    public static class DataBean {
        /**
         * totleMoney : 15.0
         * earnMoney : 0.00
         * listSn : ["15.0"]
         * listRecord : ["李白不白于00:05:53成功购买了SN,购买编号为15.0"]
         * freeMoney : 9.0993571E7
         * payMoney : null
         * endTime : 2018-12-28
         * periodNum : 20181228001
         * snprice : 15
         */

        public String totleMoney;
        public String earnMoney;
        public String freeMoney;
        public String payMoney;
        public String endTime;
        public String periodNum;
        public String snprice;
        public List<String> listSn;
        public List<String> listRecord;
    }
}
