package com.ys.game.sf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author lh
 * @version 1.0.0
 * @filename Wxhz_dxdsUtil
 * @description -------------------------------------------------------
 * @date 2018/11/5 16:44
 */
public class Wxhz_dxdsUtil {
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


    public static List<Object> getListData(List<List<Integer>> list) {
        List<Wxhz_dxdsBean> rrList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Wxhz_dxdsBean bean = new Wxhz_dxdsBean();
            bean.name = "第" + (i + 1) + "期";
            bean.ge = list.get(i).get(0);
            bean.shi = list.get(i).get(1);
            bean.bai = list.get(i).get(2);
            bean.qian = list.get(i).get(3);
            bean.wan = list.get(i).get(4);
            int sum = 0;
            for (int j = 0; j < list.get(i).size(); j++) {
                sum += list.get(i).get(j);
            }
            bean.sum = sum;
            if (sum >= 23) {
                bean.big = "大";
            } else {
                bean.small = "小";
            }
            if (sum % 2 == 0) {
                bean.two = "双";
            } else {
                bean.single = "单";
            }
            rrList.add(bean);
        }
        List<String> bigString = new ArrayList<>();
        List<String> smallString = new ArrayList<>();
        List<String> singleString = new ArrayList<>();
        List<String> twoString = new ArrayList<>();
        int big = 0;
        int small = 0;
        int single = 0;
        int two = 0;
        for (int i = 0; i < rrList.size(); i++) {
            if (rrList.get(i).big == null || "".equals(rrList.get(i).big)) {
                big++;
                rrList.get(i).big = String.valueOf(big);
            } else {
                big = 0;
            }
            bigString.add(rrList.get(i).big);
            if (rrList.get(i).small == null || "".equals(rrList.get(i).small)) {
                small++;
                rrList.get(i).small = String.valueOf(small);
            } else {
                small = 0;
            }
            smallString.add(rrList.get(i).small);
            if (rrList.get(i).single == null || "".equals(rrList.get(i).single)) {
                single++;
                rrList.get(i).single = String.valueOf(single);
            } else {
                single = 0;
            }
            singleString.add(rrList.get(i).single);

            if (rrList.get(i).two == null || "".equals(rrList.get(i).two)) {
                two++;
                rrList.get(i).two = String.valueOf(two);
            } else {
                two = 0;
            }
            twoString.add(rrList.get(i).two);
        }
        List<Integer> countList = new ArrayList<>();
        int bigCount = 0;
        for (int i = 0; i < bigString.size(); i++) {
            if ("大".equals(bigString.get(i))) {
                bigCount++;
            }
        }
        int singleCount = 0;
        for (int i = 0; i < singleString.size(); i++) {
            if ("单".equals(singleString.get(i))) {
                singleCount++;
            }
        }
        countList.add(bigCount);
        countList.add(list.size() - bigCount);
        countList.add(singleCount);
        countList.add(list.size() - singleCount);
        StringBuilder bigSB = new StringBuilder();
        StringBuilder smallSB = new StringBuilder();
        StringBuilder singleSB = new StringBuilder();
        StringBuilder twoSB = new StringBuilder();
        for (int i = 0; i < bigString.size(); i++) {
            bigSB.append(bigString.get(i));
            smallSB.append(smallString.get(i));
            singleSB.append(singleString.get(i));
            twoSB.append(twoString.get(i));
        }
        List<Integer> maxLcList = new ArrayList<>();
        maxLcList.add(max(bigSB.toString()));
        maxLcList.add(max(smallSB.toString()));
        maxLcList.add(max(singleSB.toString()));
        maxLcList.add(max(twoSB.toString()));

        List<Integer> maxYlList = new ArrayList<>();
        List<Integer> bigNumber = new ArrayList<>();
        for (int i = 0; i < bigString.size(); i++) {
            if (!"大".equals(bigString.get(i))) {
                bigNumber.add(Integer.valueOf(bigString.get(i)));
            }
        }
        List<Integer> smallNumber = new ArrayList<>();
        for (int i = 0; i < smallString.size(); i++) {
            if (!"小".equals(smallString.get(i))) {
                smallNumber.add(Integer.valueOf(smallString.get(i)));
            }
        }

        List<Integer> singleNumber = new ArrayList<>();
        for (int i = 0; i < singleString.size(); i++) {
            if (!"单".equals(singleString.get(i))) {
                singleNumber.add(Integer.valueOf(singleString.get(i)));
            }
        }

        List<Integer> twoNumber = new ArrayList<>();
        for (int i = 0; i < twoString.size(); i++) {
            if (!"双".equals(twoString.get(i))) {
                twoNumber.add(Integer.valueOf(twoString.get(i)));
            }
        }
        maxYlList.add(bigNumber.size() > 0 ? Collections.max(bigNumber) : 0);
        maxYlList.add(smallNumber.size() > 0 ? Collections.max(smallNumber) : 0);
        maxYlList.add(singleNumber.size() > 0 ? Collections.max(singleNumber) : 0);
        maxYlList.add(twoNumber.size() > 0 ? Collections.max(twoNumber) : 0);
        List<Integer> pjylList = new ArrayList<>();
        String[] bigArray = bigSB.toString().split("大");
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

        String[] smallArray = smallSB.toString().split("小");
        List<String> smallYlList = new ArrayList<>();
        for (String s : smallArray) {
            if (s != null && !"".equals(s)) {
                smallYlList.add(s);
            }
        }

        int smallPJYL = 0;
        if (smallYlList.size() > 0) {
            int sum = 0;
            for (String s : smallYlList) {
                sum += s.length();
            }
            smallPJYL = sum / smallYlList.size();
        }

        String[] singleArray = singleSB.toString().split("单");
        List<String> singleYlList = new ArrayList<>();
        for (String s : singleArray) {
            if (s != null && !"".equals(s)) {
                singleYlList.add(s);
            }
        }

        int singlePJYL = 0;
        if (singleYlList.size() > 0) {
            int sum = 0;
            for (String s : singleYlList) {
                sum += s.length();
            }
            singlePJYL = sum / singleYlList.size();
        }

        String[] twoArray = twoSB.toString().split("双");
        List<String> twoYlList = new ArrayList<>();
        for (String s : twoArray) {
            if (s != null && !"".equals(s)) {
                twoYlList.add(s);
            }
        }

        int twoPJYL = 0;
        if (twoYlList.size() > 0) {
            int sum = 0;
            for (String s : twoYlList) {
                sum += s.length();
            }
            twoPJYL = sum / twoYlList.size();
        }
        pjylList.add(bigPJYL);
        pjylList.add(smallPJYL);
        pjylList.add(singlePJYL);
        pjylList.add(twoPJYL);
        List<Object> result = new ArrayList<>();
        result.add(rrList);//列表数据
        result.add(countList);//总出现次数
        result.add(maxLcList);//最大连出
        result.add(maxYlList);//最大遗漏
        result.add(pjylList);//平均遗漏
        return result;
    }
}
