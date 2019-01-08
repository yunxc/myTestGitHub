package com.yun.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Reqv {
	//private final static String QUEUE_NAME = "hello";
	
	private final static String QUEUE_NAME = "DL0017_reply";

	private final static String exchangeName = "myexc.test3";
    private final static String routingKey = "hello-routingKey";

    public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername("admin");
		factory.setPassword("123456");
		factory.setHost("127.0.0.1");
		factory.setVirtualHost("/test");
		factory.setPort(5672);
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
        //channel.exchangeDeclare(exchangeName,"direct",true);
		//String queueName  = channel.queueDeclare().getQueue();
		//System.out.println("queueName:" + queueName);
		//channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		//channel.queueBind(QUEUE_NAME ,exchangeName, routingKey);
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(QUEUE_NAME, false, consumer);
		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println(" Reqv [x] Received '" + message + "'");
			Thread.sleep(5 * 1000);
			channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
		}
	}
}