package com.wuhulala.ui.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * 功能说明: o_o<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: wuhulala<br>
 * 开发时间: 2018/7/10<br>
 */
@RestController
public class FluxController {


    ///////////////////////////// 方法区 ////////////////////////////////////

    @GetMapping("/hello_world")
    public Mono<String> sayHelloWorld() {
        return Mono.just("Hello World");
    }

    public static void main(String[] args) {
        Mono.justOrEmpty(Optional.of("justOrEmpty2")).subscribe(System.out::println);

    }
}
