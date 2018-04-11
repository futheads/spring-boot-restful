package com.futhead.restful.api;

import com.futhead.restful.mq.sender.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2018/4/9.
 */
@RestController
@RequestMapping("/mq")
public class MQController {

    @Autowired
    private Sender1 sender1;

    @Autowired
    private Sender1 sender2;

    @Autowired
    private UserSender userSender;

    @Autowired
    private TopicSender topicSender;

    @Autowired
    private FanoutSender fanoutSender;

    @Autowired
    private CallBackSender callBackSender;

    @GetMapping("hello")
    public String hello(@RequestParam String name) {
        sender1.send("hello");
        return "Hello：" + name;
    }

    /**
     * 单生产者-多消费者
     */
    @PostMapping("oneToMany")
    public void oneToMany() {
        for(int i=0; i<10; i++){
            sender1.send("Hello:" + i);
        }

    }

    /**
     * 多生产者-多消费者
     */
    @PostMapping("/manyToMany")
    public void manyToMany() {
        for(int i=0;i<10;i++){
            sender1.send("Hello:" + i);
            sender2.send("Hello:" + i);
        }
    }

    /**
     * 实体类传输测试
     */
    @PostMapping("/userTest")
    public void userTest() {
        userSender.send();
    }

    /**
     * topic exchange类型rabbitmq测试
     */
    @PostMapping("/topicTest")
    public void topicTest() {
        topicSender.send();
    }

    /**
     * fanout exchange类型rabbitmq测试
     */
    @PostMapping("/fanoutTest")
    public void fanoutTest() {
        fanoutSender.send();
    }

    @PostMapping("/callback")
    public void callbak() {
        callBackSender.send();
    }

}
