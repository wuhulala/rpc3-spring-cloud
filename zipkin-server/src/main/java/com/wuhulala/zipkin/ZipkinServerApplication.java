package com.wuhulala.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.internal.EnableZipkinServer;

/**
 * 功能说明: o_o<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: wuhulala<br>
 * 开发时间: 2018/7/10<br>
 */
@SpringBootApplication
@EnableZipkinServer
public class ZipkinServerApplication {


    ///////////////////////////// 方法区 ////////////////////////////////////

    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerApplication.class, args);
    }
}
