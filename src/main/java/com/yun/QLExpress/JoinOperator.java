package com.yun.QLExpress;

import com.ql.util.express.Operator;

/**
 * @author Administrator
 * @date 2021/1/4 10:07
 */
public class JoinOperator extends Operator {
    @Override
    public Object executeInner(Object[] list) throws Exception {
        java.util.List result = new java.util.ArrayList();
        Object opdata1 = list[0];
        if(opdata1 instanceof java.util.List){
            result.addAll((java.util.List)opdata1);
        }else{
            result.add(opdata1);
        }
        for(int i=1;i<list.length;i++){
            result.add(list[i]);
        }
        return result;
    }
}
