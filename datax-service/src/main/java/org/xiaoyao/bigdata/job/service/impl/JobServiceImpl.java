package org.xiaoyao.bigdata.job.service.impl;

import com.alibaba.datax.common.element.DataXJob;
import com.alibaba.datax.common.element.DataXReport;
import com.alibaba.datax.common.util.Configuration;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xiaoyao.bigdata.job.dto.DataXJobDTO;
import org.xiaoyao.bigdata.job.handler.AbstractJobHandler;
import org.xiaoyao.bigdata.job.service.JobService;
import java.util.*;

/**
 * @author ChengJie
 * @desciption
 * @date 2019/7/10 10:14
 **/
@Service
@Slf4j
public class JobServiceImpl implements JobService {

    @Autowired
    private AbstractJobHandler jobHandler;

    @Override
    public void startJob(DataXJobDTO dataXJobDTO) throws Exception {

//        ScheduledExecutorService executorService=new ScheduledThreadPoolExecutor(10,
//                new BasicThreadFactory.Builder().namingPattern("syncdata-schedule-pool-%d").daemon(true).build());
//
//        ScheduledFuture<?> t =executorService.scheduleAtFixedRate(new Thread(),0,2,
//                TimeUnit.SECONDS);
//        t.cancel(true);

        //先校验任务执行的情况,防止重复执行,如果该任务未执行则返回任务执行需要的配置信息
        Configuration configuration=jobHandler.beforeStartJob(dataXJobDTO);
        Pair<DataXJob,DataXReport> jobInfo=jobHandler.startJob(configuration);
        //todo 这里还需要采用线程池方式获取further对象来回调.同时遇到异常后杀死线程释放资源,以防止内存溢出
        jobHandler.beforeCompleteJob(jobInfo);
        jobHandler.afterCompleteJob(jobInfo);
    }

    @Override
    public void stopJob(Long jobId) throws Exception {
        jobHandler.beforeStopJob(jobId);
        Pair<DataXJob,DataXReport> jobInfo=jobHandler.stopJob(jobId);
        jobHandler.afterStopJob(jobInfo);
        jobHandler.beforeCompleteJob(jobInfo);
        jobHandler.afterCompleteJob(jobInfo);
    }

    @Override
    public void suspendJob(Long id) {
        jobHandler.suspendJob(id);
    }

    @Override
    public void continueJob(Long id) {
        jobHandler.continueJob(id);
    }

    @Override
    public List<DataXJob> list(Map params) {
        return null;
    }

    @Override
    public Long count(Map params) {
        return null;
    }

    @Override
    public DataXJob get(Long jobId) {
        return null;
    }

    @Override
    public void saveJobSnapshot(DataXJob dataXJob) {

    }
}
