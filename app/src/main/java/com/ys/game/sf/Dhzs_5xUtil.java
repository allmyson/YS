package com.ys.game.sf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author lh
 * @version 1.0.0
 * @filename Dhzs_5xUtil
 * @description -------------------------------------------------------
 * @date 2018/11/1 18:42
 */
public class Dhzs_5xUtil {
    public static List<List<Dhzs_5xBean>> getShowData(List<List<Integer>> list) {
        return getShowData(list, null);
    }

    /**
     * 得到需要展示的最终数据
     *
     * @param list
     * @return
     */
    public static List<List<Dhzs_5xBean>> getShowData(List<List<Integer>> list, List<String> nameList) {
        List<List<Dhzs_5xBean>> showData = new ArrayList<>();
        // 最终数据
        List<List<Dhzs_5xBean>> result = getFinalData(list, nameList);
        // 出现总次数
        List<Integer> countList = getTotalCountList(list);
        List<Dhzs_5xBean> countBean = new ArrayList<>();
        for (int i : countList) {
            Dhzs_5xBean bean = new Dhzs_5xBean();
            bean.name = "出现次数";
            bean.value = i;
            countBean.add(bean);
        }
        // 最大连出
        List<Integer> lcList = getMaxLcList(list);
        List<Dhzs_5xBean> lcBean = new ArrayList<>();
        for (int i : lcList) {
            Dhzs_5xBean bean = new Dhzs_5xBean();
            bean.name = "最大连出";
            bean.value = i;
            lcBean.add(bean);
        }
        // 最大遗漏
        List<Integer> maxylList = getMaxLyList(list);
        List<Dhzs_5xBean> maxylBean = new ArrayList<>();
        for (int i : maxylList) {
            Dhzs_5xBean bean = new Dhzs_5xBean();
            bean.name = "最大遗漏";
            bean.value = i;
            maxylBean.add(bean);
        }
        // 平均遗漏
        List<Integer> pjylList = getPJYlData(list);
        List<Dhzs_5xBean> pjylBean = new ArrayList<>();
        for (int i : pjylList) {
            Dhzs_5xBean bean = new Dhzs_5xBean();
            bean.name = "平均遗漏";
            bean.value = i;
            pjylBean.add(bean);
        }
        showData.addAll(result);
        showData.add(countBean);
        showData.add(lcBean);
        showData.add(maxylBean);
        showData.add(pjylBean);
        return showData;
    }

    /**
     * 获取平均遗漏
     *
     * @param list
     * @return
     */
    public static List<Integer> getPJYlData(List<List<Integer>> list) {
        List<Integer> resultList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            List<List<Dhzs_5xBean>> result = new ArrayList<>();
            for (int k = 0; k < list.size(); k++) {
                List<Dhzs_5xBean> temp = new ArrayList<>();
                List<Integer> ll = list.get(k);
                for (int i = 0; i < 10; i++) {
                    Dhzs_5xBean bean = new Dhzs_5xBean();
                    for (int m : ll) {
                        if (m == i) {
                            bean.value = m;
                            bean.count++;
                        }
                    }
                    temp.add(bean);
                }
                result.add(temp);
            }
            List<List<String>> lh = new ArrayList<>();
            for (int i = 0; i < result.get(0).size(); i++) {
                int m = 0;
                List<String> temp = new ArrayList<>();
                for (int j = 0; j < result.size(); j++) {
                    int value = result.get(j).get(i).value;// arr[j][i];
                    if (value == -1) {
                        m++;
                        temp.add(String.valueOf(m));
                    } else {
                        m = 0;
                        temp.add("x");
                    }
                }
                lh.add(temp);
            }
            List<List<String>> ylList = new ArrayList<>();
            for (int i = 0; i < lh.size(); i++) {
                StringBuilder sb = new StringBuilder();
                List<String> xx = new ArrayList<>();
                for (int j = 0; j < lh.get(i).size(); j++) {
                    if ("x".equals(lh.get(i).get(j))) {
                        sb.append(lh.get(i).get(j));
                    } else {
                        sb.append("-");
                    }
                }
                String[] arg = sb.toString().split("x");
                for (String s : arg) {
                    if (s != null && !"".equals(s)) {
                        xx.add(s);
                    }
                }
                ylList.add(xx);
            }
            for (int i = 0; i < ylList.size(); i++) {
                int total = 0;
                for (int j = 0; j < ylList.get(i).size(); j++) {
                    total += ylList.get(i).get(j).length();
                }
                int x;
                if(ylList.get(i).size()==0){
                    x=0;
                }else {
                    x = total / ylList.get(i).size();
                }
                resultList.add(x);
            }
        } else {
            for (int i = 0; i < 10; i++) {
                resultList.add(0);
            }
        }
        return resultList;
    }

    /**
     * 最大连出
     *
     * @param list
     * @return
     */
    public static List<Integer> getMaxLcList(List<List<Integer>> list) {
        List<Integer> bbb = new ArrayList<>();
        if (list != null && list.size() > 0) {
            String[][] arr = new String[list.size()][10];
            for (int i = 0; i < list.size(); i++) {
                List<Integer> ll0 = list.get(i);
                for (int j = 0; j < 10; j++) {
                    arr[i][j] = "*";
                    for (int m : ll0) {
                        if (m == j) {
                            arr[i][j] = String.valueOf(m);
                        }
                    }
                }
            }
            for (int i = 0; i < arr[0].length; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < arr.length; j++) {
                    sb.append(arr[j][i]);
                }
                bbb.add(max(sb.toString()));
            }
        } else {
            for (int i = 0; i < 10; i++) {
                bbb.add(0);
            }
        }
        return bbb;
    }

    /**
     * @param list 元数据
     * @return 最大遗漏
     */
    public static List<Integer> getMaxLyList(List<List<Integer>> list) {
        List<Integer> maxLyList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            List<List<Dhzs_5xBean>> result = new ArrayList<>();
            for (int k = 0; k < list.size(); k++) {
                List<Dhzs_5xBean> temp = new ArrayList<>();
                List<Integer> ll = list.get(k);
                for (int i = 0; i < 10; i++) {
                    Dhzs_5xBean bean = new Dhzs_5xBean();
                    for (int m : ll) {
                        if (m == i) {
                            bean.value = m;
                            bean.count++;
                        }
                    }
                    temp.add(bean);
                }
                result.add(temp);
            }
            List<List<Integer>> lh5 = new ArrayList<>();
            for (int i = 0; i < result.get(0).size(); i++) {
                int m = 0;
                List<Integer> temp = new ArrayList<>();
                for (int j = 0; j < result.size(); j++) {
                    int value = result.get(j).get(i).value;
                    if (value == -1) {
                        m++;
                        temp.add(m);
                    } else {
                        m = 0;
                        temp.add(m);
                    }
                }
                lh5.add(temp);
            }
            for (int i = 0; i < lh5.size(); i++) {
                maxLyList.add(Collections.max(lh5.get(i)));
            }
        } else {
            for (int i = 0; i < 10; i++) {
                maxLyList.add(0);
            }
        }
        return maxLyList;
    }

    /**
     * 计算出现总次数
     *
     * @return
     */
    public static List<Integer> getTotalCountList(List<List<Integer>> list) {
        List<Integer> totalCountList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            List<Integer> allList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.get(i).size(); j++) {
                    allList.add(list.get(i).get(j));
                }
            }
            for (int i = 0; i < 10; i++) {
                totalCountList.add(Collections.frequency(allList, i));
            }
        } else {
            for (int i = 0; i < 10; i++) {
                totalCountList.add(0);
            }
        }
        return totalCountList;
    }

    public static List<List<Dhzs_5xBean>> getFinalData(List<List<Integer>> list, List<String> nameList) {
        List<List<Dhzs_5xBean>> result = new ArrayList<>();
        for (int k = 0; k < list.size(); k++) {
            List<Dhzs_5xBean> temp = new ArrayList<>();
            List<Integer> ll = list.get(k);
            for (int i = 0; i < 10; i++) {
                Dhzs_5xBean bean = new Dhzs_5xBean();
                if (nameList == null) {
                    bean.name = "第" + (k + 1) + "期";
                } else {
                    if (nameList.get(k).length() > 8) {
                        bean.name = nameList.get(k).substring(8);
                    } else {
                        bean.name = nameList.get(k);
                    }
                }
                for (int m : ll) {
                    if (m == i) {
                        bean.value = m;
                        bean.count++;
                    }
                }
                temp.add(bean);
            }
            result.add(temp);
        }
        List<List<Integer>> lh = new ArrayList<>();
        for (int i = 0; i < result.get(0).size(); i++) {
            int m = 0;
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < result.size(); j++) {
                int value = result.get(j).get(i).value;// arr[j][i];
                if (value == -1) {
                    m++;
                    temp.add(m);
                } else {
                    m = 0;
                    temp.add(value);
                }
            }
            lh.add(temp);
        }
        List<List<Integer>> lh1 = new ArrayList<>();
        for (int i = 0; i < lh.get(0).size(); i++) {
            List<Integer> aa = new ArrayList();
            for (int j = 0; j < lh.size(); j++) {
                aa.add(lh.get(j).get(i));
            }
            lh1.add(aa);
        }
        List<List<Dhzs_5xBean>> finalResult = new ArrayList<>();
        finalResult.addAll(result);
        for (int i = 0; i < finalResult.size(); i++) {
            List<Dhzs_5xBean> beanList = finalResult.get(i);
            List<Integer> intList = lh1.get(i);
            for (int j = 0; j < beanList.size(); j++) {
                beanList.get(j).value = intList.get(j);
            }
        }
        return finalResult;
    }


    /**
     * 获取最终彩票展示数据
     *
     * @param list
     * @return
     */
    public static List<List<Dhzs_5xBean>> getFinalData(List<List<Integer>> list) {
        return getFinalData(list, null);
    }

    // 统计相同字符连续出现的最大子序列的长度
    public static int max(String s) {
        boolean isAllx = true;
        for (int i = 0; i < s.length(); i++) {
            if ('*' != s.charAt(i)) {
                isAllx = false;
                break;
            }
        }
        if (isAllx) {
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

    /**
     * 随机生成五位时时彩数据
     *
     * @param size
     * @return
     */
    public static List<List<Integer>> list(int size) {
        List<List<Integer>> allList = new ArrayList<>();
        List<Integer> list = null;
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            list = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                list.add(random.nextInt(10));
            }
            allList.add(list);
        }
        return allList;
    }


    public static List<List<Integer>> getHEList(int size) {
        List<List<Integer>> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            List<Integer> l = new ArrayList<>();
            int a = random.nextInt(10);
            l.add(a);
            List<Integer> data = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                if (j != a) {
                    data.add(j);
                }
            }
            int b = data.get(random.nextInt(9));
            l.add(b);
            list.add(l);
        }
        return list;
    }
}
