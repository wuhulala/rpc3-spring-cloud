package com.wuhulala.ribbon.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.wuhulala.ribbon.annotation.ExcludeFromComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xueaohui
 * @version 1.0
 * @date 2017/4/22
 * @link https://github.com/wuhulala
 */
@Configuration
@ExcludeFromComponentScan
public class SomeConfiguration {

    @Bean
    public IRule ribbonRule(){
        return new RoundRobinRule();
    }
}
