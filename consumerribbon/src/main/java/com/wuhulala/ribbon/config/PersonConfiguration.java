package com.wuhulala.ribbon.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.context.annotation.Bean;

/**
 * @author xueaohui
 * @version 1.0
 * @date 2017/4/22
 * @link https://github.com/wuhulala
 */
//@Configuration
public class PersonConfiguration {
    @Bean
    public IPing ribbonPing(IClientConfig config){
        return new PingUrl();
    }
}
