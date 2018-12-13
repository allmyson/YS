package com.ys.game.bean;

import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename TeamBean
 * @description -------------------------------------------------------
 * @date 2018/12/13 18:20
 */
public class TeamBean {

    /**
     * code : SUCCESS
     * msg : 查询成功
     * data : {"manager":{"consumerId":"be6cca47335946b9827ca5e09bea5f7c","backNum":"0.1","balance":97251,
     * "consumerImg":"userapp/2018/12/13///1544671793703.jpg","consumerName":"哈哈哈","createId":null,
     * "createTime":"2018-12-03T02:44:01.000+0000","onlineTime":"2018-12-13T10:00:46.000+0000","levelCode":"1001",
     * "levelName":"代理会员","loginName":"cy","parentConsumerId":null,"pwd":"e10adc3949ba59abbe56e057f20f883e",
     * "remark":null,"status":"1001"},"listMember":[{"consumerId":"0ee372caa08b4f12b4610a4e74a8b4cf",
     * "backNum":"0.05","balance":0,"consumerImg":"","consumerName":"曹勇会员1",
     * "createId":"be6cca47335946b9827ca5e09bea5f7c","createTime":"2018-12-13T10:05:02.000+0000","onlineTime":null,
     * "levelCode":"1000","levelName":"普通用户","loginName":"cy1","parentConsumerId":"be6cca47335946b9827ca5e09bea5f7c",
     * "pwd":"ff92a240d11b05ebd392348c35f781b2","remark":null,"status":"1001"},
     * {"consumerId":"8571fe9301aa41f1a5bbc113d6300157","backNum":"0.05","balance":0,"consumerImg":"",
     * "consumerName":"aaaa","createId":"be6cca47335946b9827ca5e09bea5f7c",
     * "createTime":"2018-12-13T08:34:24.000+0000","onlineTime":null,"levelCode":"1000","levelName":"普通用户",
     * "loginName":"zy1","parentConsumerId":"be6cca47335946b9827ca5e09bea5f7c",
     * "pwd":"670b14728ad9902aecba32e22fa4f6bd","remark":null,"status":"1001"}]}
     * systemDate : 1544695515134
     */

    public String code;
    public String msg;
    public DataBean data;
    public long systemDate;

    public static class DataBean {
        /**
         * manager : {"consumerId":"be6cca47335946b9827ca5e09bea5f7c","backNum":"0.1","balance":97251,
         * "consumerImg":"userapp/2018/12/13///1544671793703.jpg","consumerName":"哈哈哈","createId":null,
         * "createTime":"2018-12-03T02:44:01.000+0000","onlineTime":"2018-12-13T10:00:46.000+0000",
         * "levelCode":"1001","levelName":"代理会员","loginName":"cy","parentConsumerId":null,
         * "pwd":"e10adc3949ba59abbe56e057f20f883e","remark":null,"status":"1001"}
         * listMember : [{"consumerId":"0ee372caa08b4f12b4610a4e74a8b4cf","backNum":"0.05","balance":0,
         * "consumerImg":"","consumerName":"曹勇会员1","createId":"be6cca47335946b9827ca5e09bea5f7c",
         * "createTime":"2018-12-13T10:05:02.000+0000","onlineTime":null,"levelCode":"1000","levelName":"普通用户",
         * "loginName":"cy1","parentConsumerId":"be6cca47335946b9827ca5e09bea5f7c",
         * "pwd":"ff92a240d11b05ebd392348c35f781b2","remark":null,"status":"1001"},
         * {"consumerId":"8571fe9301aa41f1a5bbc113d6300157","backNum":"0.05","balance":0,"consumerImg":"",
         * "consumerName":"aaaa","createId":"be6cca47335946b9827ca5e09bea5f7c",
         * "createTime":"2018-12-13T08:34:24.000+0000","onlineTime":null,"levelCode":"1000","levelName":"普通用户",
         * "loginName":"zy1","parentConsumerId":"be6cca47335946b9827ca5e09bea5f7c",
         * "pwd":"670b14728ad9902aecba32e22fa4f6bd","remark":null,"status":"1001"}]
         */

        public ManagerBean manager;
        public List<ListMemberBean> listMember;

        public static class ManagerBean {
            /**
             * consumerId : be6cca47335946b9827ca5e09bea5f7c
             * backNum : 0.1
             * balance : 97251
             * consumerImg : userapp/2018/12/13///1544671793703.jpg
             * consumerName : 哈哈哈
             * createId : null
             * createTime : 2018-12-03T02:44:01.000+0000
             * onlineTime : 2018-12-13T10:00:46.000+0000
             * levelCode : 1001
             * levelName : 代理会员
             * loginName : cy
             * parentConsumerId : null
             * pwd : e10adc3949ba59abbe56e057f20f883e
             * remark : null
             * status : 1001
             */

            public String consumerId;
            public String backNum;
            public String balance;
            public String consumerImg;
            public String consumerName;
            public String createId;
            public String createTime;
            public String onlineTime;
            public String levelCode;
            public String levelName;
            public String loginName;
            public String parentConsumerId;
            public String pwd;
            public String remark;
            public String status;
        }

        public static class ListMemberBean {
            /**
             * consumerId : 0ee372caa08b4f12b4610a4e74a8b4cf
             * backNum : 0.05
             * balance : 0
             * consumerImg :
             * consumerName : 曹勇会员1
             * createId : be6cca47335946b9827ca5e09bea5f7c
             * createTime : 2018-12-13T10:05:02.000+0000
             * onlineTime : null
             * levelCode : 1000
             * levelName : 普通用户
             * loginName : cy1
             * parentConsumerId : be6cca47335946b9827ca5e09bea5f7c
             * pwd : ff92a240d11b05ebd392348c35f781b2
             * remark : null
             * status : 1001
             */

            public String consumerId;
            public String backNum;
            public String balance;
            public String consumerImg;
            public String consumerName;
            public String createId;
            public String createTime;
            public String onlineTime;
            public String levelCode;
            public String levelName;
            public String loginName;
            public String parentConsumerId;
            public String pwd;
            public String remark;
            public String status;
        }
    }
}
