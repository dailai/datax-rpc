package org.xiaoyao.bigdata.cluster.service;

import com.alibaba.datax.common.constant.CommonConstant;
import com.alibaba.datax.common.element.DataXJob;
import com.alibaba.datax.common.element.DataXReport;
import com.alibaba.datax.common.exception.CommonErrorCode;
import com.alibaba.datax.common.exception.DataXException;
import com.alibaba.datax.common.job.DataXJobManager;
import com.alibaba.datax.core.job.meta.State;
import com.alibaba.datax.core.util.FrameworkErrorCode;
import com.alibaba.datax.core.util.container.CoreConstant;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import javafx.util.Pair;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xiaoyao.bigdata.cluster.entity.DataXNode;
import org.xiaoyao.bigdata.common.enums.ReportStatus;
import org.xiaoyao.bigdata.job.dto.DataXJobDTO;
import org.yaml.snakeyaml.Yaml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DataXServiceImpl implements AbstractDataXService {

//    @Autowired
//    @NacosInjected
//    NamingService namingService;

    @Autowired
    @Qualifier("dataXJobConfigService")
    ConfigService configService;

    @Value("spring.application.name")
    String serviceName;

    @Override
    public List<DataXNode> getNodes() {
//        try {
//            List<Instance> list = namingService.getAllInstances(serviceName);
//            List<DataXNode> nodes = list.stream().map(x -> {
//                DataXNode temp = new DataXNode();
//                temp.setHost(x.getIp());
//                temp.setPort(x.getPort());
//                return temp;
//            }).collect(Collectors.toList());
//
//            return nodes;
//        } catch (NacosException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    @Override
    public DataXJob getJob(Long jobId) throws Exception {
        return getAllJob().get(jobId);
    }

    @Override
    public void registJob(DataXJobDTO dataXJobDTO) throws Exception {

        //校验任务格式以及是否已经在执行了
        Map<Long, DataXJob> allJob=doValidate(dataXJobDTO.getJobId());

        //dataXJob初始化
        Pair<DataXJob,DataXReport> dataXJob=DataXJobManager.INSTANCE.getJob(dataXJobDTO.getJobId());
        if(dataXJob==null){
            dataXJob=new Pair<>(new DataXJob(dataXJobDTO.getJobId()),new DataXReport(dataXJobDTO.getJobId()));
            //校验完毕后在缓存中注册任务信息
            dataXJob.getKey().setJobState(State.SUBMITTING.value());
        }
        BeanUtils.copyProperties(dataXJobDTO,dataXJob.getKey());

        //本地完成注册
        DataXJobManager.INSTANCE.registJob(dataXJob);

        //更新远程服务器job信息
        allJob.put(dataXJob.getKey().getJobId(), dataXJob.getKey());
        try {
            //注册到远程服务器
            configService.publishConfig(CommonConstant.JOB_DATAID, CommonConstant.DEFAULT_GROUP,JSONObject.toJSONString(allJob));
        }catch (Exception e){
            e.printStackTrace();
            throw  e;
        }

    }

    @Override
    public void completeJob(Long jobId) throws Exception {
        Map<Long, DataXJob> allJob = getAllJob();
        //如果任务不存在则不需要去变动nacos上的配置
        if (allJob == null || allJob.get(jobId) == null) {
            return;
        }
        allJob.remove(jobId);
        Yaml yaml = new Yaml();
        configService.publishConfig(CommonConstant.JOB_DATAID, CommonConstant.DEFAULT_GROUP, yaml.dump(allJob));
    }

    private Map<Long, DataXJob> doValidate(Long jobId) throws Exception {
        //先检查本地执行情况
        Pair<DataXJob,DataXReport> dataXJob=DataXJobManager.INSTANCE.getJob(jobId);
        if(dataXJob!=null&&State.RUNNING.value()==dataXJob.getKey().getJobState()){
            throw DataXException.asDataXException(
                    FrameworkErrorCode.RUNTIME_ERROR, new RuntimeException("任务已经在执行了，请稍后再试！"));
        }

        //然后再检查集群中是否存在该任务在执行
        Map<Long, DataXJob> jobMap=getAllJob();
        DataXJob job= jobMap.get(jobId);
        if(job!=null&&State.RUNNING.value()==job.getJobState()){
            throw new DataXException(CommonErrorCode.CONFIG_ERROR,"当前任务在正在["+job.getRemark()+"]执行");
        }
        return jobMap;
    }

    private Map<Long, DataXJob> getAllJob() throws NacosException {
        String content = configService.getConfig(CommonConstant.JOB_DATAID, CommonConstant.DEFAULT_GROUP, 5000);
        if(StringUtils.isEmpty(content)){
            return new HashMap<>();
        }
        Yaml yaml = new Yaml();
        Map<Long, DataXJob> jobMap = yaml.load(content);
        if (jobMap == null) {
            return new HashMap<>();
        }
        return jobMap;
    }
}
