package com.futhead.restful.mq.sender;

import com.futhead.restful.model.vo.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/4/11.
 */
@Component
public class UserSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        User user = new User();
        user.setName("futhead");

        user.setPass("123456789");
        System.out.println("user send : " + user.getName() + "/" + user.getPass());
        this.rabbitTemplate.convertAndSend("userQueue", user);
    }
}
