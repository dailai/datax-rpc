package org.xiaoyao.bigdata.job.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xiaoyao.bigdata.common.entity.ResponeResult;
import org.xiaoyao.bigdata.job.dto.DataXJobDTO;
import org.xiaoyao.bigdata.job.service.JobService;

import java.util.Map;

/**
 * datax任务业务类
 * @author ChengJie
 * @desciption
 * @date 2019/7/10 10:01
 **/
@RestController
@RequestMapping("/datax/job")
public class JobController {

    @Autowired
    JobService jobService;

    @PostMapping("/start")
    public ResponeResult startJob(DataXJobDTO dataXJobDTO){
        jobService.startJob(dataXJobDTO);
        return ResponeResult.ok();
    }

    @PostMapping("/stop")
    public ResponeResult stopJob(Long jobId){
        jobService.stopJob(jobId);
        return ResponeResult.ok();
    }

    @PostMapping("/suspend")
    public ResponeResult suspendJob(Long jobId){
        jobService.suspendJob(jobId);
        return ResponeResult.ok();
    }

    @PostMapping("/continue")
    public ResponeResult continueJob(Long jobId){
        jobService.continueJob(jobId);
        return ResponeResult.ok();
    }

    @PostMapping("/list")
    public ResponeResult list(Map params){
        return ResponeResult.ok().put("list",jobService.list(params)).put("count",jobService.count(params));
    }

}
