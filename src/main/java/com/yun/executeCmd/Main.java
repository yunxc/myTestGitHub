package com.yun.executeCmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * @author Administrator
 * @date 2019/9/18 20:01
 */
public class Main {
    public static void main(String[] args) throws Exception {
        String source = "{\"opt\":{\"tooltip\":{\"trigger\":\"item\",\"formatter\":\"{a} <br/>{b}: {c} ({d}%)\"},\"legend\":{\"orient\":\"vertical\",\"x\":\"left\",\"data\":[\"直接访问\",\"邮件营销\",\"联盟广告\",\"视频广告\",\"搜索引擎\"]},\"series\":[{\"name\":\"访问来源\",\"type\":\"pie\",\"radius\":[\"50%\",\"70%\"],\"avoidLabelOverlap\":false,\"label\":{\"normal\":{\"show\":false,\"position\":\"center\"},\"emphasis\":{\"show\":true,\"textStyle\":{\"fontSize\":\"30\",\"fontWeight\":\"bold\"}}},\"labelLine\":{\"normal\":{\"show\":false}},\"data\":[{\"value\":335,\"name\":\"直接访问\"},{\"value\":310,\"name\":\"邮件营销\"},{\"value\":234,\"name\":\"联盟广告\"},{\"value\":135,\"name\":\"视频广告\"},{\"value\":1548,\"name\":\"搜索引擎\"}]}]},\"width\":500,\"height\":500,\"type\":\"base64\"}";
        String s = Base64.getEncoder().encodeToString(source.getBytes());
        String cmd = "G:\\app1\\phantomjs\\phantomjs-2.1.1-windows\\bin\\phantomjs " +
                "G:\\app1\\phantomjs\\myecharts\\echart-convert\\echarts-convert.js " + s;
        Process process = Runtime.getRuntime().exec(cmd);
        String inputMsg = getMsg(process, false);
        String imgstart = "imgbase64code^&*%^&*#$#@:start:";
        String imgend = ":imgbase64code^&*%^&*#$#@:end";
        String imgBase64 = subString(inputMsg, imgstart, imgend);
        Integer exitValue = process.waitFor();
        System.out.println(imgBase64);

    }

    /**
     * 返回不会为null
     *
     * @param process
     * @param isError
     * @return
     */
    public static String getMsg(Process process, boolean isError) {
        String wrap = getSystem();
        InputStream in = null;
        if (isError) {
            in = process.getErrorStream();
        } else {
            in = process.getInputStream();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
        String result = "";
        String read = null;
        try {
            while ((read = br.readLine()) != null) {
                result += read + wrap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String getSystem() {
        String wrap = "";
        String system = System.getProperty("os.name");
        if (system.contains("Windows")) {
            wrap = "\r\n";
        } else if (system.contains("Linux")) {
            wrap = "\n";
        } else {
            wrap = "\r";
        }
        return wrap;
    }

    /**
     * 截取字符串str中指定字符 strStart、strEnd之间的字符串
     *
     * @param string
     * @param str1
     * @param str2
     * @return
     */
    public static String subString(String str, String strStart, String strEnd) {

        /* 找出指定的2个字符在 该字符串里面的 位置 */
        int strStartIndex = str.indexOf(strStart);
        int strEndIndex = str.indexOf(strEnd);

        /* index 为负数 即表示该字符串中 没有该字符 */
        if (strStartIndex < 0) {
            return "";
        }
        if (strEndIndex < 0) {
            return "";
        }
        /* 开始截取 */
        String result = str.substring(strStartIndex, strEndIndex).substring(strStart.length());
        return result;
    }

}
