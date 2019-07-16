package org.xiaoyao.bigdata.cluster.service;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xiaoyao.bigdata.cluster.entity.DataXCluster;
import org.xiaoyao.bigdata.cluster.entity.DataXNode;
import org.yaml.snakeyaml.Yaml;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DataXClusterService implements ClusterService {

    @NacosInjected
    private NamingService namingService;

    @Qualifier("dataXJobConfigService")
    private ConfigService configService;

    @Value("datax.cluster.config.dataId")
    private String dataId;

    @Value("datax.cluster.config.group")
    private String group;

    @Value("spring.application.name")
    String serviceName;

    /**
     * 集群信息
     * @return
     */
    @Override
    public DataXCluster getClusterInfo() {
        try {
            String content=configService.getConfig(dataId,group,5000);
            if(StringUtils.isNotEmpty(content)){
                 Yaml yaml=new Yaml();
                DataXCluster dataXCluster=yaml.loadAs(content,DataXCluster.class);
                return dataXCluster;
            }
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DataXNode> getNodes() {
        try {
            List<Instance> list=namingService.getAllInstances(serviceName);
            List<DataXNode> nodes=list.stream().map(x->{
                DataXNode temp=new DataXNode();
                temp.setHost(x.getIp());
                temp.setPort(x.getPort());
                return temp;
            }).collect(Collectors.toList());

            return  nodes;
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return null;
    }
}
