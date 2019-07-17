package org.xiaoyao.bigdata.config;

import com.alibaba.datax.common.constant.CommonConstant;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xiaoyao.bigdata.cluster.listeners.DataXJobListener;
import org.xiaoyao.bigdata.cluster.listeners.DataXNodeListener;
import java.util.Properties;

/**
 * @author ChengJie
 * @desciption
 * @date 2019/7/16 9:55
 **/
@Configuration
public class DataXConfig {

    @Value("${spring.cloud.nacos.config.server-addr}")
    private  String serverAddr;

    @Value("${spring.cloud.nacos.config.namespace}")
    private String namespace;

    @Autowired
    private DataXJobListener dataXJobListener;

    @Autowired
    private DataXNodeListener dataXNodeListener;

    @Bean(name = "dataXJobConfigService")
    public ConfigService dataXJobConfigService() throws NacosException {
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        properties.put("namespace",namespace);
        ConfigService configService = NacosFactory.createConfigService(properties);
        //添加配置改动监听器
        configService.addListener(CommonConstant.JOB_DATAID, CommonConstant.DEFAULT_GROUP, dataXJobListener);
        configService.addListener(CommonConstant.NODES_DATAID, CommonConstant.DEFAULT_GROUP, dataXNodeListener);
        return configService;
    }

}
