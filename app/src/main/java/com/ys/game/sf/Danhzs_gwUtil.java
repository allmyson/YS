package com.ys.game.sf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author lh
 * @version 1.0.0
 * @filename Danhzs_gwUtil
 * @description -------------------------------------------------------
 * @date 2018/11/7 14:27
 */
public class Danhzs_gwUtil {
    public static List<List<Danhzs_gwBean>> getData(List<Integer> list,List<String> nameList) {
        List<List<Danhzs_gwBean>> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            List<Danhzs_gwBean> beanList = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                Danhzs_gwBean bean = new Danhzs_gwBean();
                if (nameList == null) {
                    bean.name = "第" + (i + 1) + "期";
                } else {
                    if (nameList.get(i).length() > 8) {
                        bean.name = nameList.get(i).substring(8);
                    } else {
                        bean.name = nameList.get(i);
                    }
                }
                if (j == list.get(i)) {
                    bean.value = j;
                    bean.isChoose = true;
                }
                beanList.add(bean);
            }
            result.add(beanList);
        }

        // 出现次数
        List<Danhzs_gwBean> countList = new ArrayList<>();
        // 最大连出
        List<Danhzs_gwBean> lcList = new ArrayList<>();
        // 最大遗漏
        List<Danhzs_gwBean> zdylList = new ArrayList<>();
        // 平均遗漏
        List<Danhzs_gwBean> pjylList = new ArrayList<>();
        for (int j = 0; j < result.get(0).size(); j++) {
            Danhzs_gwBean countBean = new Danhzs_gwBean();
            countBean.name = "出现次数";
            countBean.value = 0;
            Danhzs_gwBean lcBean = new Danhzs_gwBean();
            lcBean.name = "最大连出";
            StringBuilder lcSB = new StringBuilder();

            Danhzs_gwBean zdylBean = new Danhzs_gwBean();
            zdylBean.name = "最大遗漏";
            List<Integer> ylList = new ArrayList<>();

            Danhzs_gwBean pjylBean = new Danhzs_gwBean();
            pjylBean.name = "平均遗漏";
            StringBuilder pjylSB = new StringBuilder();
            int yl = 0;
            for (int i = 0; i < result.size(); i++) {
                if (result.get(i).get(j).value == -1) {
                    yl++;
                    result.get(i).get(j).value = yl;
                } else {
                    yl = 0;
                }
                if (result.get(i).get(j).isChoose) {
                    countBean.value++;
                }

                if (result.get(i).get(j).isChoose) {
                    lcSB.append("-");
                } else {
                    lcSB.append(result.get(i).get(j).value);
                }
                if (!result.get(i).get(j).isChoose) {
                    ylList.add(result.get(i).get(j).value);
                }

                if (result.get(i).get(j).isChoose) {
                    pjylSB.append("-");
                } else {
                    pjylSB.append("x");
                }
            }
            countList.add(countBean);
            lcBean.value = max(lcSB.toString());
            lcList.add(lcBean);
            zdylBean.value = ylList.size() > 0 ? Collections.max(ylList) : 0;
            zdylList.add(zdylBean);
            pjylBean.value = getPJYL(pjylSB.toString());
            pjylList.add(pjylBean);
        }
        result.add(countList);
        result.add(lcList);
        result.add(zdylList);
        result.add(pjylList);
        return result;
    }

    public static int max(String s) {
        if (isDigit(s) || s == null || "".equals(s) || s.length() == 0) {
            return 0;
        }
        int max = 0, tmp_m = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1) && '*' != s.charAt(i)) {
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

    // 获取平均遗漏
    public static int getPJYL(String ss) {
        String[] bigArray = ss.split("-");
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
    public static List<Integer> list(int size) {
        List<Integer> allList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            allList.add(random.nextInt(10));
        }
        return allList;
    }
}
