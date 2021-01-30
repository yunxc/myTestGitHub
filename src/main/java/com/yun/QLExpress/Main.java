package com.yun.QLExpress;

import com.google.common.collect.ImmutableMap;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;
import com.ql.util.express.InstructionSet;
import com.ql.util.express.instruction.IOperateDataCache;
import com.ql.util.express.instruction.op.OperatorBase;
import com.ql.util.express.instruction.op.OperatorFactory;
import org.apache.commons.lang.ArrayUtils;
import org.apache.poi.ss.formula.functions.T;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @date 2020/12/29 20:37
 */
public class Main {

   public static ExpressRunner runner = new ExpressRunner(false,false);

    public static void main(String[] args) throws Exception {
        t5();
        String s = "(住院费用<2000)and(药占比>85)";
        Map<String, String> m = new HashMap<>();
        m.put("药占比","(医疗总费用/药费)");
        for(String k:m.keySet()){
            s = s.replaceAll(k, m.get(k));
        }
        System.out.println(s);
        Date monthFirstDay = getFirstDayOfMonth(new Date());
        Date monthLastDay = getLastDayOfMonth(new Date());
        System.out.println("" + monthFirstDay + monthLastDay);
        DecimalFormat df = new DecimalFormat("0.##########");
        BigDecimal b = new BigDecimal(125.026);
        Integer i = new Integer("10");
        double d = new Double("12510.00000001");
        System.out.println(i);
        System.out.println(df.format(i));
        System.out.println(d);
        System.out.println(df.format(d));
        System.out.println(df.format(b));
        String format = df.format(b);
        double de = 1.001265001;
        fg(de);
    }

    private static void fg(Object o){
        if(o instanceof Double){
            DecimalFormat df = new DecimalFormat("0.##########");
            System.out.println(df.format(o));
        }
    }

    public static Date getFirstDayOfMonth(Date date){
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());;
        LocalDateTime endOfDay = localDateTime.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }
    /**
     * java8(别的版本获取2月有bug) 获取某月最后一天的23:59:59
     * @return
     */
    public static Date getLastDayOfMonth(Date date){
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());;
        LocalDateTime endOfDay = localDateTime.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static void t5() throws Exception {
        String str = "if(((基准点数<=100 and 医疗总费用 >= 3 * 地区DRG均费)) ) \n" +
                "then  {return \"A\";} else if(((基准点数>100 and 基准点数<=100 \n" +
                "and 医疗总费用 >= 2 * 地区DRG均费)) ) then  {return \"A\";} \n" +
                "else if(((基准点数>300 and 医疗总费用 >= 1.5 * 地区DRG均费)) ) \n" +
                "then  {return \"A\";} else if(((医疗总费用 < 0.4 * 地区DRG均费)) ) \n" +
                "then  {return \"B\";} else if(((1 == 1)) ) then  {return \"C\";}\n";
        IExpressContext<String, Object> context =new DefaultContext<String, Object>();
        context.put("基准点数", 2);
        context.put("医疗总费用", 3);

        context.put("地区DRG均费", 10);
        context.put("f", 5);

        Object result1 = runner.execute(str, context, null, false, false);
        System.out.println("result:" + result1);
    }

    public static void t3() throws Exception {
        String str = "if((d/f)==1) then {return (b);} " +
                "else if(b>2) then {return (min(a,b));}" +
                "else {return (c)}";
        IExpressContext<String, Object> context =new DefaultContext<String, Object>();
        context.put("a", 5);
        context.put("b", 6);
        context.put("c", 5);

        context.put("d", 5);
        context.put("f", 5);
       // runner.addOperatorWithAlias("M", "a",null);
        OperatorFactory operatorFactory = runner.getOperatorFactory();
        OperatorBase dddds = operatorFactory.getOperator("若");
        IOperateDataCache operateDataCache = runner.getOperateDataCache();

        IExpressContext<String, Object> context2 =new DefaultContext<String, Object>();

        Map<String, Object> m1 = new HashMap<>();
        m1.put("A", "A1");
        m1.put("B", "B1");
        m1.put("C", "C1");

        Map<String, String> m2 = new HashMap<>();
        m2.put("A", "a");
        m2.put("B", "b");

        Map<String, Object> m3 = new HashMap<>();

        m2.forEach(m3::put);
        System.out.println(m3);

        m1.forEach((s, o) ->
                m3.put(m2.getOrDefault(s, s), o)
        );

        System.out.println(m3);

        Object result1 = runner.execute(str, context, null, false, false);
        System.out.println("result:" + result1);
    }

    public static void testBool() throws Exception {
        String str = "(术后天数 > 5)and(医疗总费用 < 病组低倍界值)";
        IExpressContext<String, Object> context =new DefaultContext<String, Object>();
        context.put("医疗总费用", 50);
        context.put("术后天数", 10);
        context.put("病组低倍界值", 100);
        Object result1 = runner.execute(str, context, null, false, false);
        System.out.println("result:" + result1);
    }

    public static void testMacro() throws Exception {
        runner.addMacro("病例类型",
                "if(A>10 and B==5) " +
                        "then {return \"高倍病例\";} " +
                        "else if (A<10 and B==5) " +
                        "then {return \"低倍病例\";} " +
                        "else {return \"正常病例\";}");
        runner.addMacro("病例点数",
                "if(病例类型 == \"高倍病例\") " +
                        "then {return A*100;}" +
                        "else if(病例类型 == \"低倍病例\") " +
                        "then {return A*(-100);} " +
                        "else {return 0;}");
        IExpressContext<String, Object> context =new DefaultContext<String, Object>();
        context.put("A", 100);
        context.put("B", 5);
        Object result1 = runner.execute("病例类型", context, null, false, false);
        Object result2 = runner.execute("病例点数", context, null, false, false);
        System.out.println("result =" + result1 + ":" + result2);
        //返回结果true
    }

    public static void testList() throws Exception {
        ExpressRunner runner = new ExpressRunner(false,false);
        List<String> li = new ArrayList<>(3);
        li.add("A");
        li.add("B");
        li.add("C");
        runner.addFunctionOfClassMethod("包含"
                , com.yun.QLExpress.ListOperation.class.getName()
                , "contain", new Class[]{List.class, String.class}
                , "dddddddddddddddd");
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("主诊断", "B");
        context.put("li", li);
        String express = "if (包含(li,主诊断)) then {return 1;} else {return 0;}";
        //String express = "包含(NewList(\"A\",\"B\"),主诊断)";
        //String express = "包含(li,主诊断)";
        String[] outVarNames = runner.getOutVarNames(express);
        Object o = runner.execute(express, context, null, false, false);
        System.out.println(o);
    }


    public static void testSet() throws Exception {
        ExpressRunner runner = new ExpressRunner(false,false);
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        String express = "abc = NewMap(1:1,2:2); return abc.get(1) + abc.get(2);";
        Object r = runner.execute(express, context, null, false, false);
        System.out.println(r);
        express = "abc = NewList(1,2,3); return abc.get(1)+abc.get(2)";
        r = runner.execute(express, context, null, false, false);
        System.out.println(r);
        express = "abc = [1,2,3]; return abc[1]+abc[2];";
        r = runner.execute(express, context, null, false, false);
        System.out.println(r);
    }


    private static void ifReturn() throws Exception {
        String t1 = "if ( (A>0 and A<10) or ( 7 * B > 1)) then { return \"faf\";} " +
                "else if (A >= 10 and A<=20) then {return 2;} " +
                "else if (A >20) then {return 3;} " +
                "else if (A>0 and A <5) then {return 5;}";
        t1 = "if ( (A<=100 and B >= 3 * M) or (A > 100 and A <= 300 and B >= 2 * M) or (A > 300 and B >= 1.5 * M) ) " +
                "then { return \"高倍率\";} " +
                "else if ( (B < 0.4 * M) ) then { return \"低倍率\";}";
        ExpressRunner runner = new ExpressRunner(false,false);
        IExpressContext<String, Object> context =new DefaultContext<String, Object>();
        context.put("A", -1);
        context.put("B", -1);
        context.put("M", -1);
        Object result = runner.execute(t1, context, null, false, false);
        System.out.println("result:" + result);
    }


    /**
     * 病例类型判断
     */
    private static void bllx() {
        String t1 = "如果 (" +
                        "(基准点数<=100 and 医疗总费用 >= 3*地区DRG均费) " +
                        "or (医疗总费用 > 90)" +
                    ") " +
                "则{return \"高倍率病例\";} 否则 return \"正常倍率病例\";";
        ExpressRunner runner = new ExpressRunner(false,false);

        try {
            runner.addOperatorWithAlias("若", "if",null);
            runner.addOperatorWithAlias("如果", "if",null);
            runner.addOperatorWithAlias("则", "then",null);
            runner.addOperatorWithAlias("否则", "else",null);


            InstructionSet instructionSet = runner.parseInstructionSet(t1);

        } catch (Exception e) {
            System.out.println("=====语法错误");
            e.printStackTrace();
        }

        InstructionSet instructionSet1 = null;
        try {
            instructionSet1 = runner.getInstructionSetFromLocalCache(t1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] names = new String[0];
        try {
            names = runner.getOutVarNames(t1);
            for(String s:names){
                System.out.println("需要的参数 : " + s);
            }

            IExpressContext<String, Object> context =new DefaultContext<String, Object>();
            context.put("医疗总费用", 88);
            context.put("地区DRG均费", 99);
            context.put("基准点数", 5);

            
            Object result = null;
            for(int j =0; j < 10 ; j++){
                long start = System.currentTimeMillis();
                for(int i = 0; i < 30000 ; i++){
                    result = runner.execute(instructionSet1,context, null, false, false, null);
                    //result = runner.execute(t1, context, null, false, false);
                }
                long end = System.currentTimeMillis();
                long t = (end - start);
                System.out.println("花费时间：" + t);
            }
            System.out.println("result:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    /**
     * //优先从本地指令集缓存获取指令集，没有的话生成并且缓存在本地
     * 	InstructionSet getInstructionSetFromLocalCache(String expressString);
     * 	//清除缓存
     * 	void clearExpressCache();
     */



    /**
     * 脚本语法是否正确，可以通过ExpressRunner编译指令集的接口来完成。
     * @throws Exception
     */
    private static void testPLE() throws Exception {
        ExpressRunner runner = new ExpressRunner(false,false);
        String expressString = "for(i=0;i<10;i++){sum=i+1}return sum;";
        InstructionSet instructionSet = runner.parseInstructionSet(expressString);
        //如果调用过程不出现异常，指令集instructionSet就是可以被加载运行（execute）了！
        System.out.println(instructionSet);
    }


    private static void t2() throws Exception {
        String express = "int 平均分 = (语文+数学+英语+综合考试.科目2)/4.0;return 平均分";
        ExpressRunner runner = new ExpressRunner(true,true);
        String[] names = runner.getOutVarNames(express);
        for(String s:names){
            System.out.println("var : " + s);
        }
    }


    private static void t1() throws Exception {
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("a",1);
        context.put("b",2);
        context.put("c",3);
        String express = "a+b*c";
        Object r = runner.execute(express, context, null, true, false);
        System.out.println(r);
    }

    private static void macro() throws Exception {
        ExpressRunner runner = new ExpressRunner();
        runner.addMacro("计算平均成绩", "(语文+数学+英语)/3.0");
        runner.addMacro("是否优秀", "计算平均成绩>90");
        IExpressContext<String, Object> context =new DefaultContext<String, Object>();
        context.put("语文", 88);
        context.put("数学", 99);
        context.put("英语", 5);
        Object result = runner.execute("是否优秀", context, null, false, false);
        System.out.println(result);
//返回结果true
    }

}
