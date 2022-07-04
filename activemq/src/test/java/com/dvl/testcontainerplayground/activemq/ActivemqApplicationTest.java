package com.dvl.testcontainerplayground.activemq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@SpringBootTest
class ActivemqApplicationTest extends AbstractIntegrationTest {

    @Autowired
    ActiveMQService activeMQService;

    @Test
    void sendAndReceiveMessage() {
        String message = UUID.randomUUID().toString();
        activeMQService.publishMessage(message);
        await()
                .atMost(5, TimeUnit.SECONDS)
                .until(() -> message.equals(activeMQService.getLastMessage()));
    }

}
