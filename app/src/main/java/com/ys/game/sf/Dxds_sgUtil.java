package com.ys.game.sf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename Dxds_sgUtil
 * @description -------------------------------------------------------
 * @date 2018/11/6 17:06
 */
public class Dxds_sgUtil {
    //获取最终数据
    public static List<Object> getData(List<List<Integer>> list,List<String> nameList) {
        List<Dxds_sgBean> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Dxds_sgBean bean = new Dxds_sgBean();
            if (nameList == null) {
                bean.name = "第" + (i + 1) + "期";
            } else {
                if (nameList.get(i).length() > 8) {
                    bean.name = nameList.get(i).substring(8);
                } else {
                    bean.name = nameList.get(i);
                }
            }
            bean.ge = list.get(i).get(0);
            bean.shi = list.get(i).get(1);
            if (bean.shi >= 5) {
                bean.shiBig = "大";
            } else {
                bean.shiSmall = "小";
            }
            if (bean.shi % 2 != 0) {
                bean.shiSingle = "单";
            } else {
                bean.shiTwo = "双";
            }

            if (bean.ge >= 5) {
                bean.geBig = "大";
            } else {
                bean.geSmall = "小";
            }

            if (bean.ge % 2 != 0) {
                bean.geSingle = "单";
            } else {
                bean.geTwo = "双";
            }
            result.add(bean);
        }
        List<String> shiBigList = new ArrayList<>();
        List<String> shiSmallList = new ArrayList<>();
        List<String> shiSingleList = new ArrayList<>();
        List<String> shiTwoList = new ArrayList<>();
        List<String> geBigList = new ArrayList<>();
        List<String> geSmallList = new ArrayList<>();
        List<String> geSingleList = new ArrayList<>();
        List<String> geTwoList = new ArrayList<>();
        int shiBig = 0, shiSmall = 0, shiSingle = 0, shiTwo = 0, geBig = 0, geSmall = 0, geSingle = 0, geTwo = 0;
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).shiBig == null || "".equals(result.get(i).shiBig)) {
                shiBig++;
                result.get(i).shiBig = String.valueOf(shiBig);
            } else {
                shiBig = 0;
            }
            if (result.get(i).shiSmall == null || "".equals(result.get(i).shiSmall)) {
                shiSmall++;
                result.get(i).shiSmall = String.valueOf(shiSmall);
            } else {
                shiSmall = 0;
            }

            if (result.get(i).shiSingle == null || "".equals(result.get(i).shiSingle)) {
                shiSingle++;
                result.get(i).shiSingle = String.valueOf(shiSingle);
            } else {
                shiSingle = 0;
            }

            if (result.get(i).shiTwo == null || "".equals(result.get(i).shiTwo)) {
                shiTwo++;
                result.get(i).shiTwo = String.valueOf(shiTwo);
            } else {
                shiTwo = 0;
            }

            if (result.get(i).geBig == null || "".equals(result.get(i).geBig)) {
                geBig++;
                result.get(i).geBig = String.valueOf(geBig);
            } else {
                geBig = 0;
            }

            if (result.get(i).geSmall == null || "".equals(result.get(i).geSmall)) {
                geSmall++;
                result.get(i).geSmall = String.valueOf(geSmall);
            } else {
                geSmall = 0;
            }

            if (result.get(i).geSingle == null || "".equals(result.get(i).geSingle)) {
                geSingle++;
                result.get(i).geSingle = String.valueOf(geSingle);
            } else {
                geSingle = 0;
            }
            if (result.get(i).geTwo == null || "".equals(result.get(i).geTwo)) {
                geTwo++;
                result.get(i).geTwo = String.valueOf(geTwo);
            } else {
                geTwo = 0;
            }
            shiBigList.add(result.get(i).shiBig);
            shiSmallList.add(result.get(i).shiSmall);
            shiSingleList.add(result.get(i).shiSingle);
            shiTwoList.add(result.get(i).shiTwo);
            geBigList.add(result.get(i).geBig);
            geSmallList.add(result.get(i).geSmall);
            geSingleList.add(result.get(i).geSingle);
            geTwoList.add(result.get(i).geTwo);
        }
        //出现次数
        List<Integer> countList = new ArrayList<>();
        countList.add(getCount(shiBigList, "大"));
        countList.add(getCount(shiSmallList, "小"));
        countList.add(getCount(shiSingleList, "单"));
        countList.add(getCount(shiTwoList, "双"));
        countList.add(getCount(geBigList, "大"));
        countList.add(getCount(geSmallList, "小"));
        countList.add(getCount(geSingleList, "单"));
        countList.add(getCount(geTwoList, "双"));
        // 连出
        List<Integer> lcList = new ArrayList<>();
        lcList.add(max(listToString(shiBigList)));
        lcList.add(max(listToString(shiSmallList)));
        lcList.add(max(listToString(shiSingleList)));
        lcList.add(max(listToString(shiTwoList)));
        lcList.add(max(listToString(geBigList)));
        lcList.add(max(listToString(geSmallList)));
        lcList.add(max(listToString(geSingleList)));
        lcList.add(max(listToString(geTwoList)));

        // 最大遗漏
        List<Integer> zdylList = new ArrayList<>();
        zdylList.add(getZDYL(shiBigList));
        zdylList.add(getZDYL(shiSmallList));
        zdylList.add(getZDYL(shiSingleList));
        zdylList.add(getZDYL(shiTwoList));
        zdylList.add(getZDYL(geBigList));
        zdylList.add(getZDYL(geSmallList));
        zdylList.add(getZDYL(geSingleList));
        zdylList.add(getZDYL(geTwoList));

        // 平均遗漏
        List<Integer> pjylList = new ArrayList<>();
        pjylList.add(getPJYL(shiBigList, "大"));
        pjylList.add(getPJYL(shiSmallList, "小"));
        pjylList.add(getPJYL(shiSingleList, "单"));
        pjylList.add(getPJYL(shiTwoList, "双"));
        pjylList.add(getPJYL(geBigList, "大"));
        pjylList.add(getPJYL(geSmallList, "小"));
        pjylList.add(getPJYL(geSingleList, "单"));
        pjylList.add(getPJYL(geTwoList, "双"));

        List<Object> data = new ArrayList<>();
        data.add(result);
        data.add(countList);
        data.add(lcList);
        data.add(zdylList);
        data.add(pjylList);
        return data;
    }

    public static int getCount(List<String> list, String name) {
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (name.equals(list.get(i))) {
                count++;
            }
        }
        return count;
    }

    public static String listToString(List<String> list) {
        if (list != null && list.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (String s : list) {
                sb.append(s);
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    // 获取最大遗漏数
    public static int getZDYL(List<String> list) {
        List<Integer> shiBigYlList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (isDigit(list.get(i))) {
                shiBigYlList.add(Integer.valueOf(list.get(i)));
            }
        }
        return shiBigYlList.size() > 0 ? Collections.max(shiBigYlList) : 0;
    }

    // 获取平均遗漏
    public static int getPJYL(List<String> list, String name) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String[] bigArray = sb.toString().split(name);
        List<String> bigYlList = new ArrayList<>();
        for (String s : bigArray) {
            if (s != null && !"".equals(s)) {
                bigYlList.add(s);
            }
        }
        int bigPJYL = 0;
        if (bigYlList.size() > 0) {
            int sum = 0;
            for (String s : bigYlList) {
                sum += s.length();
            }
            bigPJYL = sum / bigYlList.size();
        }
        return bigPJYL;
    }

    // 统计相同字符连续出现的最大子序列的长度
    public static int max(String s) {
        if (isDigit(s) || s == null || "".equals(s) || s.length() == 0) {
            return 0;
        }
        int max = 0, tmp_m = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                tmp_m++;
            } else {
                max = max > tmp_m ? max : tmp_m;
                tmp_m = 1;
            }
        }
        max = max > tmp_m ? max : tmp_m;// 最后的连续数与最大连续的比较
        return max;
    }

    // 判断一个字符是否都为数字
    public static boolean isDigit(String strNum) {
        return strNum.matches("[0-9]{1,}");
    }
}
