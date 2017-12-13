package com.example.jpmcamqp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpmcAmqpApplication {

	@Value("${queueName}")
	public String queueName;

	@Value("${exchangeName}")
	public String exchangeName;

	@Value("${routingKey}")
	public String routingKey;

	public static void main(String[] args) {
		SpringApplication.run(JpmcAmqpApplication.class, args);
	}

	@Bean
	public Queue queue() {
		return new Queue(this.queueName);
	}

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(this.exchangeName);
	}

	@Bean
	public Binding binding(Queue queue, TopicExchange exchange){
		return BindingBuilder.bind(queue).to(exchange).with(this.routingKey);
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setExchange(this.exchangeName);
		rabbitTemplate.setQueue(this.queueName);
		return rabbitTemplate;
	}


}
