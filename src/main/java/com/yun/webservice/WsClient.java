package com.yun.webservice;


import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import server1.HelloWorld;

import javax.xml.namespace.QName;

/**
 * @author yunlong.zhang
 * @date 2019/4/18 11:52
 */
public class WsClient {

    public static void main(String[] args) {
        WsClient2();
    }

    public static void WsClient1(){
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setAddress("http://127.0.0.1:8080/webserver/ws/hello?wsdl");
        factoryBean.setServiceClass(HelloWorld.class);//通过接口指定请求方法名称/返回类型/参数 此方法要求 HelloWorld 类与webservice服务有相同的类路径
        HelloWorld ex =(HelloWorld)factoryBean.create();
        Object object = ex.sayHello("123");//请求完毕后、类型接收
        System.out.println(object);
    }

    public static void WsClient2(){
        //采用动态工厂方式 不需要指定服务接口
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://127.0.0.1:8080/webserver/ws/hello?wsdl");
        QName qName = new QName("http://server1/", "sayHello");
        Object[] result = new Object[0];
        try {
            result = client.invoke(qName,
                    new Object[] { "admin"});
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result[0]);
    }

}
