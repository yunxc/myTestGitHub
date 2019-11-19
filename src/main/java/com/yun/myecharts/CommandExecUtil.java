package com.yun.myecharts;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

import java.util.Map;

/**
 * @author Administrator
 * @date 2019/9/18 16:41
 */
public class CommandExecUtil {

    public static int phantomjsExec(String[] args) {
        try {
            return CommandExecUtil.exec("scripts/phantomjs", args, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public static int exec(String command, String[] args, Map<String, ?> substitutionMap) throws Exception {
        CommandLine cmdLine = new CommandLine(command);
        if (args != null && args.length > 0) {
            for (String arg : args) {
                cmdLine.addArgument(arg);
            }
        }
        if (substitutionMap != null) {
            cmdLine.setSubstitutionMap(substitutionMap);
        }
        DefaultExecutor executor = new DefaultExecutor();
        // executor.setExitValue(1);
        // ExecuteWatchdog watchdog = new ExecuteWatchdog(60000);
        // executor.setWatchdog(watchdog);
        int exitValue = executor.execute(cmdLine);
        return exitValue;
    }

}
