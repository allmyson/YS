package com.ys.game.bean;

public class AppInfo {

    /**
     * code : SUCCESS
     * msg : 查询成功
     * data : {"appId":"1","apkTypeCode":"1000","apkTypeName":"安卓","downloadUrl":"http://103.65.180.104:8090/downloadFile","versionName":"app-release.apk","versionNum":"1.0","versionRemark":"app-release.apk","versionTime":1548606777999}
     * systemDate : 1548662065516
     */

    public String code;
    public String msg;
    public DataBean data;
    public long systemDate;

    public static class DataBean {
        /**
         * appId : 1
         * apkTypeCode : 1000
         * apkTypeName : 安卓
         * downloadUrl : http://103.65.180.104:8090/downloadFile
         * versionName : app-release.apk
         * versionNum : 1.0
         * versionRemark : app-release.apk
         * versionTime : 1548606777999
         */

        public String appId;
        public String apkTypeCode;
        public String apkTypeName;
        public String downloadUrl;
        public String versionName;
        public String versionNum;
        public String versionRemark;
        public long versionTime;
    }
}
