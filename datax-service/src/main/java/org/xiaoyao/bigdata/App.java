package org.xiaoyao.bigdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author ChengJie
 * @desciption
 * @date 2019/7/10 9:58
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableAsync
@EnableFeignClients
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
