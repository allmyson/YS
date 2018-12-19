package com.ys.game.bean;

import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename TeamCzjlBean
 * @description -------------------------------------------------------
 * @date 2018/12/19 13:53
 */
public class TeamCzjlBean {

    /**
     * code : SUCCESS
     * msg : 查询成功
     * data : {"pageNum":null,"length":null,"start":null,"search":null,"order":null,"column":null,"recordsTotal":1,
     * "recordsFiltered":1,"draw":null,"data":[{"apply_status_name":"提交待审","consumer_name":"李白不白",
     * "apply_user_id":"be6cca47335946b9827ca5e09bea5f7c","apply_money":"100","apply_type_name":"现金",
     * "apply_type_code":"1001","reject_time":null,"apply_status_code":"1000",
     * "apply_id":"53c5439d895c49198ca8dcf90a2a4bbf","apply_time":"2018-12-18","reject_reason":null}],"subSQL":null,
     * "systemTime":0}
     * systemDate : 1545198764052
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
         * data : [{"apply_status_name":"提交待审","consumer_name":"李白不白",
         * "apply_user_id":"be6cca47335946b9827ca5e09bea5f7c","apply_money":"100","apply_type_name":"现金",
         * "apply_type_code":"1001","reject_time":null,"apply_status_code":"1000",
         * "apply_id":"53c5439d895c49198ca8dcf90a2a4bbf","apply_time":"2018-12-18","reject_reason":null}]
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
             * apply_status_name : 提交待审
             * consumer_name : 李白不白
             * apply_user_id : be6cca47335946b9827ca5e09bea5f7c
             * apply_money : 100
             * apply_type_name : 现金
             * apply_type_code : 1001
             * reject_time : null
             * apply_status_code : 1000
             * apply_id : 53c5439d895c49198ca8dcf90a2a4bbf
             * apply_time : 2018-12-18
             * reject_reason : null
             */

            public String apply_status_name;
            public String consumer_name;
            public String apply_user_id;
            public String apply_money;
            public String apply_type_name;
            public String apply_type_code;
            public String reject_time;
            public String apply_status_code;
            public String apply_id;
            public String apply_time;
            public String reject_reason;
        }
    }
}
