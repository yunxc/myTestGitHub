package com.yun.QLExpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;
import com.ql.util.express.InstructionSet;

import java.util.*;

/**
 * @author Administrator
 * @date 2020/12/31 14:49
 */
public class FireSoonUtil {

    private static final ExpressRunner runner = new ExpressRunner(false,false);

    /**
     * 获取一个表达式需要的外部变量名称列表
     */
    private static String[] getOutVarNames(String expressString) throws Exception {
        if(checkExpressString(expressString)){
            String[] outVarNames = runner.getOutVarNames(expressString);
            return outVarNames;
        }

        return new String[0];
    }


    /**
     * 检查表达式是否正确
     */
    private static boolean checkExpressString(String expressString){
        //ExpressRunner runner = new ExpressRunner(false,false);
        try {
            runner.parseInstructionSet(expressString);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 对表达式结果的真假判断
     * 输入条件，判断真假
     *  A>0.5*B and F>L
     * @return
     */
    private static boolean judgment(String expressString, IExpressContext<String, Object> context) throws Exception {
        //ExpressRunner runner = new ExpressRunner(false,false);
        //Object result = runner.execute(expressString, context, null, false, false);

        InstructionSet instructionSetFromLocalCache = runner.getInstructionSetFromLocalCache(expressString);
        Object result = runner.execute(instructionSetFromLocalCache, context, null, false, false, null);
        return (boolean) result;
    }
    /**
     * 表达式计算
     *  输入计算公式和值，计算结果
     *   A*B+C*(F+5)
     * @return
     */
    private static Object expressionEvaluation(String expressString, IExpressContext<String, Object> context) throws Exception {
        //ExpressRunner runner = new ExpressRunner(false,false);
        Object result = runner.execute(expressString, context, null, false, false);
        return result;
    }
    /**
     * 规则预估
     * 输入条件，判断属于哪一类
     * 类型1：（1）A<B and B>50
     *         (2)  C<5 and F>10
     * 类型2：（1）A<B and B=30
     *         (2)  C<5 and F=10
     * @return
     */
    private static Object ruleCalculation(LinkedHashMap<String, List<String>> ruleMap, IExpressContext<String, Object> context) throws Exception {
        String  ruleStr = createRule(ruleMap);
       // ExpressRunner runner = new ExpressRunner(false,false);
        Object result = runner.execute(ruleStr, context, null, false, false);
        return result;
    }

    /**
     * 创建规则
     * @param ruleMap
     */
    private static String createRule(LinkedHashMap<String, List<String>> ruleMap) {
        StringBuffer sb = new StringBuffer("if ( ");
        int j = 0;
        for(String r:ruleMap.keySet()){
            List<String> ruTmp = ruleMap.get(r);
            for(int i = 0; i < ruTmp.size(); i++){
                sb.append("(");
                sb.append(ruTmp.get(i));
                sb.append(")");
                if(i+1 != ruTmp.size()){
                    sb.append(" or ");
                }

            }
            sb.append(" ) then { return \"");
            sb.append(r);
            sb.append("\";} ");
            if(++j != ruleMap.size()){
                sb.append(" else if ( ");
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        LinkedHashMap<String, List<String>> rule = new LinkedHashMap<>(3);
        List<String> l1 = new ArrayList<>(3);
        /**
         * A 基准点数
         * B 医疗总费用
         * M 地区DRG均费
         */
        l1.add("A<=100 and B >= 3 * M");
        l1.add("A > 100 and A <= 300 and B >= 2 * M");
        l1.add("A > 300 and B >= 1.5 * M");
        rule.put("高倍率", l1);
        List<String> l2 = new ArrayList<>(2);
        l2.add("B < 0.4 * M");
        rule.put("低倍率", l2);
        List<String> l3 = new ArrayList<>(2);
        l3.add(" 1 == 1");
        rule.put("正常", l3);


        IExpressContext<String, Object> context =new DefaultContext<>();
        context.put("A", 500);
        context.put("B", 60);
        context.put("M", 10);
        Object o1 = ruleCalculation(rule, context);
        System.out.println("o1 result:" + o1);

        String expressString1 = "A * B + (A*M)";
        Object o2 = expressionEvaluation(expressString1, context);
        System.out.println("o2 result:" + o2);

        String expressString2 = "A>0.5*B and M>B";

        String[] outVarNames = getOutVarNames(expressString2);
        for(String v:outVarNames){
            System.out.println(v);
        }

        long start = System.currentTimeMillis();
        Object o3 = null;
        for(int i = 0; i < 100000; i++){
            o3 = judgment(expressString2, context);
        }
        long end = System.currentTimeMillis();
        long t = (end - start);
        System.out.println("花费时间：" + t);

        System.out.println("o3 result:" + o3);
    }

}
