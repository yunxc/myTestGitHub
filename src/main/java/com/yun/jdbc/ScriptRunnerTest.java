package com.yun.jdbc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Administrator
 * @date 2021/4/12 9:54
 */
public class ScriptRunnerTest {

    private static String driver = "oracle.jdbc.OracleDriver";
    private static String url = "jdbc:oracle:thin:@172.16.3.73:1521:hdctest" ;
    private static String username = "puab" ;
    private static String password = "puab" ;

    public static void main(String[] args) throws Exception {
        //加载数据驱动
        Class.forName(driver);
        Connection conn = null;
        BufferedReader reader = null;
        FireScriptRunner runner = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            //======可认为的固定设置===============================
            // 设置不自动提交
            conn.setAutoCommit(false);
            runner = new FireScriptRunner(conn);
            // 设置不自动提交
            runner.setAutoCommit(false);
            //false则按照自定义的分隔符每行执行
            runner.setSendFullScript(false);
            // 定义命令间的分隔符
            runner.setDelimiter(";");
            runner.setFullLineDelimiter(false);
            //=====================================
            runner.setLogWriter(null);
            runner.setStopOnError(true);


            String filePath1 = "E:\\tmp\\testSql\\test.sql";
            InputStreamReader inputStreamReader1 = new InputStreamReader(new FileInputStream(filePath1), "UTF-8");

            String filePath2 = "E:\\tmp\\testSql\\test1.sql";
            InputStreamReader inputStreamReader2 = new InputStreamReader(new FileInputStream(filePath2), "UTF-8");
            //执行文件中的SQL，单个runScriptTransaction(),不提交事务
            runner.runScriptTransaction(inputStreamReader1);
            runner.runScriptTransaction(inputStreamReader2);
            //提交事务
            conn.commit();
        }catch (Exception e){
            if (conn != null) {
                conn.rollback();
            }
            e.printStackTrace();
        } finally {
            if(conn != null){
                conn.close();
            }
        }
    }

}
