package com.futhead.restful.mq.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Administrator on 2018/4/9.
 */
@Component
public class Sender1 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String msg) {
        String context = msg + new Date();
        System.out.println("Sender1 : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }

}