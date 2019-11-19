package com.yun.js;

import java.util.logging.Logger;

/**
 * @author Administrator
 * @date 2019/9/18 19:53
 */
public class Test {

    public static void main(String[] args) throws Exception {
        RunScript rs = new RunScript("D:\\hello.js");
        //rs.setVar("Logger", Logger.getLogger(ConsoleListener.class));
        rs.start();
    }

}
