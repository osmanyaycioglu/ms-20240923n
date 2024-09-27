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

    @RabbitListener(bindings =
    @QueueBinding(
            value = @Queue(value = "email-queue", autoDelete = "false", durable = "true"),
            exchange = @Exchange(value = "notify-exchange",
                    autoDelete = "false",
                    durable = "true",
                    type = ExchangeTypes.DIRECT),
            key = "email-notification")
    )
    public void emailListener(String str) {
        System.out.println("Gelen EMAIL : " + str);
    }

    // eu.tr.message.sms.adv.p1
    @RabbitListener(bindings =
    @QueueBinding(
            value = @Queue(value = "sms-topic-queue", autoDelete = "false", durable = "true"),
            exchange = @Exchange(value = "notify-topic-exchange",
                    autoDelete = "false",
                    durable = "true",
                    type = ExchangeTypes.TOPIC),
            key = "eu.tr.message.sms.#")
    )
    public void smsTopicListener(MyMessage str) {
        System.out.println("Gelen Topic SMS : " + str);
    }

    @RabbitListener(bindings =
    @QueueBinding(
            value = @Queue(value = "sms-adv-topic-queue", autoDelete = "false", durable = "true"),
            exchange = @Exchange(value = "notify-topic-exchange",
                    autoDelete = "false",
                    durable = "true",
                    type = ExchangeTypes.TOPIC),
            key = "eu.tr.message.sms.adv.#")
    )
    public void smsAdvTopicListener(MyMessage str) {
        System.out.println("Gelen Topic ADV SMS : " + str);
    }

    @RabbitListener(bindings =
    @QueueBinding(
            value = @Queue(value = "sms-eu-topic-queue", autoDelete = "false", durable = "true"),
            exchange = @Exchange(value = "notify-topic-exchange",
                    autoDelete = "false",
                    durable = "true",
                    type = ExchangeTypes.TOPIC),
            key = "eu.*.message.sms.#")
    )
    public void smsEUTopicListener(MyMessage str) {
        System.out.println("Gelen EU Topic ADV SMS : " + str);
    }

    @RabbitListener(bindings =
    @QueueBinding(
            value = @Queue(value = "all-topic-queue", autoDelete = "false", durable = "true"),
            exchange = @Exchange(value = "notify-topic-exchange",
                    autoDelete = "false",
                    durable = "true",
                    type = ExchangeTypes.TOPIC),
            key = "#")
    )
    public void allTopicListener(MyMessage str) {
        System.out.println("Bütün mesajlar : " + str);
    }

    @RabbitListener(bindings =
    @QueueBinding(
            value = @Queue(value = "notify-message-topic-queue", autoDelete = "false", durable = "true"),
            exchange = @Exchange(value = "notify-topic-exchange",
                    autoDelete = "false",
                    durable = "true",
                    type = ExchangeTypes.TOPIC),
            key = "*.*.message.*.notify.#")
    )
    public void notificationListener(MyMessage str) {
        System.out.println("Gelen Notification SMS : " + str);
    }
}
