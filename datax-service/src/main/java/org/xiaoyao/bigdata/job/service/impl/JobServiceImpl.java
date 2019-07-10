package org.xiaoyao.bigdata.job.service.impl;

import com.alibaba.datax.common.element.DataXJob;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xiaoyao.bigdata.job.dto.DataXJobDTO;
import org.xiaoyao.bigdata.job.handler.AbstractJobHandler;
import org.xiaoyao.bigdata.job.service.JobService;
import org.xiaoyao.bigdata.report.entity.DataXReport;

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
    AbstractJobHandler jobHandler;

    @Override
    public void startJob(DataXJobDTO dataXJobDTO){
        jobHandler.beforeStartJob(dataXJobDTO.getJobId());
        Pair<DataXJob,DataXReport> jobInfo=jobHandler.startJob(dataXJobDTO);
        jobHandler.beforeCompleteJob(jobInfo);
        jobHandler.afterCompleteJob(jobInfo);
    }

    @Override
    public void stopJob(Long jobId){
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
}
