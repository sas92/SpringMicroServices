package com.sas.msdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.PushBuilder;

/**
 * @author Saswata_Adhya
 * @apiNote https://www.linkedin.com/learning/spring-5-0-and-spring-boot-2-0-new-features/servlets?u=2113185
 */

@Controller
@RequestMapping("push")
public class PushController {
    @GetMapping("with")
    public String demoWithPush(PushBuilder pushBuilder) {
        if (null != pushBuilder) {
            pushBuilder.path("/images/spring.png").push();
        }
        return "push";
    }

    @GetMapping("without")
    public String demoWithoutPush() {
        return "push";
    }
}
