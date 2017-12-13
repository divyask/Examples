package com.example.producer;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableBinding(ProducerInterface.class)
@RestController
public class ProducerController {

    private MessageChannel messageChannel;

    private Logger logger = Logger.getLogger(ProducerController.class);

    public ProducerController(ProducerInterface producerInterface) {
        this.messageChannel = producerInterface.producerOutput();
    }

    @PostMapping("/greet")
    public String publish(@RequestParam(value = "name") String name){
        Message message = MessageBuilder.withPayload("Hello: " + name).build();
        this.messageChannel.send(message);
        return message.toString();
    }
}
