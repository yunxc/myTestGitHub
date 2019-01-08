package com.yun.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Date;
import java.util.UUID;

public class Send {
	private final static String QUEUE_NAME = "hello-queue";
	/*private final static String routingKey = "hello-routingKey"; 
	private final static String exchangeName = "myex.hello-exchangeName";*/

    private final static String exchangeName = "JHQ0005";
	private final static String routingKey = "route1105";


	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("127.0.0.1");
		factory.setUsername("admin");
		factory.setPassword("123456");
		factory.setVirtualHost("/test");
		factory.setPort(5672);
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		//channel.exchangeDeclare(exchangeName,"direct",true);
		//String queueName  = channel.queueDeclare().getQueue();
		//System.out.println("queueName:" + queueName);
		
		//channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		//channel.queueBind(QUEUE_NAME ,exchangeName, routingKey);
		String message = "Hello World!  -----Time: " + new Date() ;
		message = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<message>\n" +
                "    <header>\n" +
                "        <realmCode>CN</realmCode>\n" +
                "        <languageCode>zh-CN</languageCode>\n" +
                "        <encoding>utf-8</encoding>\n" +
                "        <msgType>1001</msgType>\n" +
                "        <key>e5108e29aaad</key>\n" +
                "        <id>d0761</id>\n" +
				"		 <title>TTTTT</title>" +
				"		 <msgId>" + UUID.randomUUID() + "</msgId>" +
                "        <currentDate>2018-11-06 15:15:57</currentDate>\n" +
                "    </header>\n" +
                "    <body>\n" +
                "   </body>\n" +
                "</message>";
		channel.basicPublish(exchangeName, routingKey , null, message.getBytes("UTF-8"));
		System.out.println(" [x] Sent '" + message + "'");
		channel.close();
		connection.close();
	}
}