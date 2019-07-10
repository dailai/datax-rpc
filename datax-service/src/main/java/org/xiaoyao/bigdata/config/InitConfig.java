package org.xiaoyao.bigdata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xiaoyao.bigdata.job.handler.AbstractJobHandler;
import org.xiaoyao.bigdata.job.handler.DataXCommonHandler;

/**
 * @author ChengJie
 * @desciption
 * @date 2019/7/10 17:32
 **/
@Configuration
public class InitConfig {

    @Bean
    AbstractJobHandler jobHandler(){
        return new DataXCommonHandler();
    }


}
