package com.wuhulala.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xueaohui
 * @version 1.0
 * @date 2017/4/22
 * @link https://github.com/wuhulala
 */
@Configuration
public class PersonConfiguration {

    @Bean
    public IRule ribbonRule(){
        return new RandomRule();
    }
}
