package org.turkcell.training.spring.msnotify.integration;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotifyListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "sms-queue", autoDelete = "false", durable = "true"),
            exchange = @Exchange(value = "notify-exchange",
                    autoDelete = "false",
                    durable = "true",
                    type = ExchangeTypes.DIRECT),
            key = "sms-notification")
    )
    public void smsListener(String str) {
        System.out.println("Gelen SMS : " + str);
    }
}
