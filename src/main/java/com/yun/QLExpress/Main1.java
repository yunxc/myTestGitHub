package com.yun.QLExpress;

/**
 * @author Administrator
 * @date 2021/1/3 14:37
 */
public class Main1 {
    public static void main(String[] args) {
        String content = "5@*@#地区drg均费#@*@12@*@#医疗总费用#";
        content = "#地区drg均费##地区drg均费A#@*@12@*@#医疗总费用#";
        String separator = "#";
        Object[] objects = splitString(content, separator);
        for(Object o:objects){
            System.out.println(o);
        }
    }

    /**
     * 取出指定分隔符中间的所有值
     * @param content 要分割的字符串
     * @param separator 分割符
     * @return Object[]
     */
    private static Object[] splitString(String content, String separator) {
        if(content == null || content.length() == 0 ||
            separator == null ||  separator.length() == 0){
            return new Object[0];
        }
        String[] split = content.split(separator);
        int length = split.length;
        if(length < 2){
            return new Object[0];
        }
        Object[] o = new Object[length%2 == 0 ? length/2:length/2 - 1];
        for (int i = 1; i < split.length; i = i + 2){
            o[(i-1) / 2] = split[i];
        }

        return o;
    }
}
