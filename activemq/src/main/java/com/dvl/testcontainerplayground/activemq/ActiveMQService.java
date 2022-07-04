package com.dvl.testcontainerplayground.activemq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class ActiveMQService {

    private final JmsTemplate jmsTemplate;
    private String lastMessage;

    public ActiveMQService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @JmsListener(destination = "testQueue", containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(String message) {
        log.info("Received message " + message);
        lastMessage = message;
    }

    public void publishMessage(String message) {
        jmsTemplate.convertAndSend("testQueue", message);
    }

    public String getLastMessage() {
        return lastMessage;
    }

}
