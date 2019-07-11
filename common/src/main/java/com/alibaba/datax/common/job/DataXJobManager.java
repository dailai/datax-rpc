package com.alibaba.datax.common.job;

import com.alibaba.datax.common.element.DataXJob;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum  DataXJobManager {

    INSTANCE;

    private static  Map<Long ,DataXJob> jobMap=new ConcurrentHashMap<>();

    /**
     * 保存任务
     * @param dataXJob
     */
    public void registJob(DataXJob dataXJob){
        jobMap.put(dataXJob.getJobId(),dataXJob);
    }

    /**
     * 其实和registJob一样,怕引起歧义，所以分成两个方法
     * @param dataXJob
     */
    public void refreshJob(DataXJob dataXJob){
        jobMap.put(dataXJob.getJobId(),dataXJob);
    }

    /**
     * 获取一个任务
     * @param id
     * @return
     */
    public DataXJob getJob(Long id){
        return jobMap.get(id);
    }

    /**
     * 清空所有的任务信息
     */
    public void clear(){
        jobMap.clear();
    }

    /**
     * 删除某一个任务信息
     * @param jobId
     */
    public void removeJob(Long jobId){
        jobMap.remove(jobId);
    }

    /**
     * 上报任务
     * @param dataXJob
     */
    public void reportJob(DataXJob dataXJob){
        refreshJob(dataXJob);
    }

}
