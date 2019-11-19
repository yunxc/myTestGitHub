package com.yun.echarts;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @author Administrator
 * @date 2019/9/17 17:39
 */
public class EChartsConvertTest {

    public static void main(String[] args) {
        String url = "http://127.0.0.1:10096";
        // 不必要的空格最好删除，字符串请求过程中会将空格转码成+号
        String optJson = "{title:{text:'ECharts 示例'},tooltip:{},legend:{data:['销量']},"
                + "xAxis:{data:['衬衫','羊毛衫','雪纺衫','裤子','高跟鞋','袜子']},yAxis:{},"
                + "series:[{name:'销量',type:'bar',data:[5,20,36,10,10,20]}]}";

        optJson = "{\n" +
                "    tooltip: {\n" +
                "        trigger: 'item',\n" +
                "        formatter: \"{a} <br/>{b}: {c} ({d}%)\"\n" +
                "    },\n" +
                "    legend: {\n" +
                "        orient: 'vertical',\n" +
                "        x: 'left',\n" +
                "        data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']\n" +
                "    },\n" +
                "    series: [\n" +
                "        {\n" +
                "            name:'访问来源',\n" +
                "            type:'pie',\n" +
                "            radius: ['50%', '70%'],\n" +
                "            avoidLabelOverlap: false,\n" +
                "            label: {\n" +
                "                normal: {\n" +
                "                    show: false,\n" +
                "                    position: 'center'\n" +
                "                },\n" +
                "                emphasis: {\n" +
                "                    show: true,\n" +
                "                    textStyle: {\n" +
                "                        fontSize: '30',\n" +
                "                        fontWeight: 'bold'\n" +
                "                    }\n" +
                "                }\n" +
                "            },\n" +
                "            labelLine: {\n" +
                "                normal: {\n" +
                "                    show: false\n" +
                "                }\n" +
                "            },\n" +
                "            data:[\n" +
                "                {value:335, name:'直接访问'},\n" +
                "                {value:310, name:'邮件营销'},\n" +
                "                {value:234, name:'联盟广告'},\n" +
                "                {value:135, name:'视频广告'},\n" +
                "                {value:1548, name:'搜索引擎'}\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        /*optJson = optJson.replaceAll("\t","")
                .replaceAll("\n","")
                .replaceAll(" ","");*/
        optJson = optJson.replaceAll("\\s+", "").replaceAll("\"", "'");


        Map<String, String> map = new HashMap<>();
        map.put("opt", optJson);
        try {
            String post1 = post(url, map, "utf-8");
            JSONObject jsonObject = JSONObject.parseObject(post1);
            if("success".equals(jsonObject.getString("msg"))){
                String data = jsonObject.getString("data");
                System.out.println();
                System.out.println("data:image/jpeg;base64," + data);
            } else {
                System.out.println(post1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // post请求
    public static String post(String url, Map<String, String> map, String encoding) throws ParseException, IOException {
        String body = "";

        // 创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);

        // 装填参数
        List<NameValuePair> nvps = new ArrayList<>();
        if (map != null) {
            for (Entry<String, String> entry : map.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        // 设置参数到请求对象中
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));

        // 执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpPost);
        // 获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // 按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, encoding);
        }
        EntityUtils.consume(entity);
        // 释放链接
        response.close();
        return body;
    }
}
