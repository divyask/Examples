package com.example.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;


public interface ProducerInterface {
    @Output
    public MessageChannel producerOutput();
}
