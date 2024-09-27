package org.turkcell.training.spring.order.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.turkcell.training.spring.order.integration.models.MyMessage;

@Service
@RequiredArgsConstructor
public class NotifyIntegration {
    private final RabbitTemplate rabbitTemplate;

    public boolean notify(String dest,
                          String msg) {
        MyMessage myMessageLoc = new MyMessage();
        myMessageLoc.setDest(dest);
        myMessageLoc.setMsg(msg);
        rabbitTemplate.convertAndSend("notify-topic-exchange",
                                      "eu.tr.message.sms.notify.p1",
                                      myMessageLoc);
        return true;
    }


}
