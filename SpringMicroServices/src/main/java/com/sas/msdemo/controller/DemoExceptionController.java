package com.sas.msdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Saswata_Adhya
 * @apiNote https://www.linkedin.com/learning/spring-5-0-and-spring-boot-2-0-new-features/exception-and-response-status?u=2113185
 */

@RestController
@RequestMapping("error")
public class DemoExceptionController {
    @GetMapping("400")
    public String get400() {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Demo error 400");
    }

    @GetMapping("409")
    public String get409() {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Demo error 409");
    }
}
