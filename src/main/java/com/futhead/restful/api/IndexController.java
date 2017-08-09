package com.futhead.restful.api;

import com.futhead.restful.exception.MyException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by futhead on 2017-8-9.
 */
@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping("json")
    public String json() throws MyException {
        throw new MyException("发生错误2");
    }

    @GetMapping("hello")
    public String hello(@RequestParam String name) {
        return "Hello" + name;
    }

}
