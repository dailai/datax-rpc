package org.xiaoyao.bigdata.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author ChengJie
 * @desciption
 * @date 2019/7/16 9:55
 **/
@Configuration
public class DataXClusterConfig {

    @Value("${spring.cloud.nacos.config.server-addr}")
    private  String serverAddr;

    @Value("spring.cloud.nacos.config.namespace")
    private String namespace;

    @Bean(name = "dataXJobConfigService")
    public ConfigService dataXJobConfigService() throws NacosException {
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        properties.put("namespace",namespace);
        ConfigService configService = NacosFactory.createConfigService(properties);
        return configService;
    }

}
