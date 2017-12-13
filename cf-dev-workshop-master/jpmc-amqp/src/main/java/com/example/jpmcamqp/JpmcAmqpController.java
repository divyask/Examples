package com.example.jpmcamqp;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class JpmcAmqpController {

    @Value("${queueName}")
    public String queueName;

    @Value("${exchangeName}")
    public String exchangeName;

    @Value("${routingKey}")
    public String routingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    Logger logger = Logger.getLogger(JpmcAmqpController.class);

    @PostMapping("/greet/{name}")
    public String greet(@PathVariable String name) {
        String str = "Hello: " + name;
        logger.info("Sending: " + str);
        rabbitTemplate.convertAndSend(this.exchangeName, this.routingKey, str);
        logger.info("Sent: " + str);
        return str;
    }

    @GetMapping("/greet")
    public String receive() {
        String str = (String) rabbitTemplate.receiveAndConvert(this.queueName);
        logger.info("Received: " + str);
        return str;
    }
}
